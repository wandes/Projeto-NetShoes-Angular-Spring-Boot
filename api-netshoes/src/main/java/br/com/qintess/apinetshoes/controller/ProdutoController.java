package br.com.qintess.apinetshoes.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.qintess.apinetshoes.controller.dto.ProdutoDto;
import br.com.qintess.apinetshoes.controller.form.AtualizacaoProdutoForm;
import br.com.qintess.apinetshoes.controller.form.ProdutoForm;
import br.com.qintess.apinetshoes.model.Produto;
import br.com.qintess.apinetshoes.repository.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

//	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	@GetMapping
	@Cacheable( value = "listaDeProdutos")
	public Page<ProdutoDto> lista( @RequestParam(required = false) String nomeProduto, @PageableDefault
			(sort= "id" , direction =  Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		
		
		if (nomeProduto == null) {

			Page<Produto> produtos = produtoRepository.findAll(paginacao);
			return ProdutoDto.converter(produtos);

		} else {

			Page<Produto> produtos = produtoRepository.findByNome(nomeProduto, paginacao);
			return ProdutoDto.converter(produtos);

		}

	}

//	@RequestMapping(value = "/produtos", method = RequestMethod.POST)
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeProdutos", allEntries = true )
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {

		Produto produto = form.converter();
		produtoRepository.save(produto);

		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {

		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(produto.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeProdutos", allEntries = true )
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoProdutoForm form) {

		Optional<Produto> optional = produtoRepository.findById(id);

		if (optional.isPresent()) {

			Produto produto = form.atualizar(id, produtoRepository);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeProdutos", allEntries = true )
	public ResponseEntity<?> remover(@PathVariable Long id) {

		Optional<Produto> optional = produtoRepository.findById(id);

		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.ok().build();

	}

}
