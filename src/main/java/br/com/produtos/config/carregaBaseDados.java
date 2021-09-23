package br.com.produtos.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.produtos.model.Produtos;
import br.com.produtos.repositorio.ProdutoRepository;


@Configuration
public class carregaBaseDados {
	
	@Autowired
	private ProdutoRepository repositorio;
	
	@Bean
	CommandLineRunner carregarBanco() {
		return args ->{
			BigDecimal preco1 = new BigDecimal(15.74);
			Produtos produtos = new Produtos("Feijão", "Muito caro", preco1); 
			
			BigDecimal preco2 = new BigDecimal(100.66);
			Produtos produtos1 = new Produtos("Fuzil", "dá para comprar", preco2);
			
			repositorio.save(produtos);
			repositorio.save(produtos1);
		};
	}
}
