package br.com.produtos.service;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.produtos.error.ResourceNotFoundException;
import br.com.produtos.model.Produtos;
import br.com.produtos.repositorio.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepositorio;
	
	public ResponseEntity<?> salvar(Produtos produtos) {
		return new ResponseEntity<>(produtoRepositorio.save(produtos), HttpStatus.CREATED);			
	}
	
	public ResponseEntity<?> alterar(Integer id,  Produtos produto) {
		Produtos produtosNaBase = getProdutoById(id);
		produtosNaBase.setNome(produto.getNome());
		produtosNaBase.setDescricao(produto.getDescricao());
		produtosNaBase.setPreco(produto.getPreco());
		return new ResponseEntity<>(produtoRepositorio.save(produtosNaBase), HttpStatus.OK);
	}

	public Produtos getProdutoById(Integer id) {
		return produtoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado" ));
	}

	public ResponseEntity<?> consultarProdutoPeloId(Integer id) {
		ResponseEntity<?> retorno = null;
		retorno = new ResponseEntity<>(getProdutoById(id), HttpStatus.OK);
		if (retorno.getBody().equals(Optional.empty())) {
			retorno = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return retorno;
	}

	public ResponseEntity<?> listarTodosProdutos() {
		return new ResponseEntity<>(produtoRepositorio.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<?> consultarProdutosPorFiltros(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		return new ResponseEntity<>(produtoRepositorio.findByPrecoBetweenAndNomeAndDescricao(minPrice, maxPrice, q), HttpStatus.OK);
	}

	public ResponseEntity<?> delete(Integer id) {
		ResponseEntity<?> retorno = new ResponseEntity<>(HttpStatus.OK);
		try {
			produtoRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			retorno = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return retorno;
	}

}
