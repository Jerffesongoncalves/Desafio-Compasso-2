package br.com.produtos.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.produtos.model.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos, Integer> {
	
	@Query(value = 	"select P from Produtos P where (UPPER(P.nome) LIKE UPPER((CONCAT('%',:q,'%'))) "
			+ "OR UPPER(P.descricao) LIKE UPPER((CONCAT('%',:q,'%')))) and  P.preco >= :minPrice and  P.preco <= :maxPrice")
	List<Produtos> findByPrecoBetweenAndNomeAndDescricao(BigDecimal minPrice, BigDecimal maxPrice, String q);
}
