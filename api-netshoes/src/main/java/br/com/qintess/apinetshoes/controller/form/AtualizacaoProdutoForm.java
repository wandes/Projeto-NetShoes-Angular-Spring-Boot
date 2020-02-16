package br.com.qintess.apinetshoes.controller.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.qintess.apinetshoes.model.Produto;
import br.com.qintess.apinetshoes.repository.ProdutoRepository;

public class AtualizacaoProdutoForm {
	
	@NotNull @NotEmpty @Length(max = 50)
	private String nome;
	
	@NotNull @NotEmpty @Length(max = 50)
	private String categoria;
	
	@DecimalMin("0.01")
	private double preco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Produto atualizar(Long id, ProdutoRepository produtoRepository) {

		Produto produto = produtoRepository.getOne(id);
		produto.setNome(this.nome);
		produto.setCategoria(this.categoria);
		produto.setPreco(this.preco);
		
		return produto;
	}
	
	
}
