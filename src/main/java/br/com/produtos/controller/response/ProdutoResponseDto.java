package br.com.produtos.controller.response;

import java.math.BigDecimal;

import br.com.produtos.model.Produtos;

public class ProdutoResponseDto {

	private Produtos produtos;

	public ProdutoResponseDto(Produtos produtos) {
		super();
		this.produtos = produtos;
	}
	
	public Integer getIdProduto() {
		return produtos.getId();
	}
	public String getNome() {
		return produtos.getNome();
	}
	public String getDescricao() {
		return produtos.getDescricao();
	}
	public BigDecimal getPreco() {
		return produtos.getPreco();
	}
	
	
	
}
