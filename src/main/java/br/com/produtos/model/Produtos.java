package br.com.produtos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Produtos {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "nome", length = 200, nullable = false)
	@NotBlank(message = "{produto.nome.obrigatorio}")
	private String nome;
	
	@Column(name = "descricao", length = 200, nullable = false)
	@NotBlank(message = "{produto.descricao.obrigatorio}")
	private String descricao;
	
	@Column(name = "preco", precision = 12, scale = 2, nullable = false)
	@Min(value = 1, message = "{produto.preco.maior-que-um}")
	private BigDecimal preco;
	
	@Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
	private LocalDateTime criadoEm;
	
	public Produtos() {
	}
	
	public Produtos(String nome, String descricao, BigDecimal preco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}


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
	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}
}
