package br.com.produtos.controller.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProdutoRequestDTO {

	private Integer id;
	
	@NotBlank(message = "{produto.nome.obrigatorio}")
	private String nome;
	
	@NotBlank(message = "{produto.descricao.obrigatorio}")
	private String descricao;
	
	@Min(value = 1, message = "{produto.preco.maior-que-um}")
	private BigDecimal preco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
