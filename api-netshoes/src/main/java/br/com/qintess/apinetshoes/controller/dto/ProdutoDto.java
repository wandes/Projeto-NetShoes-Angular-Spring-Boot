package br.com.qintess.apinetshoes.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.qintess.apinetshoes.model.Produto;

public class ProdutoDto {
	
	private Long id;
	private String nome;
	private Double preco;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		
	}
	
	public static Page<ProdutoDto> converter(Page<Produto> produto){
		return  produto.map(ProdutoDto::new);
		//return produto.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Double getPreco() {
		return preco;
	}
	
	
}
