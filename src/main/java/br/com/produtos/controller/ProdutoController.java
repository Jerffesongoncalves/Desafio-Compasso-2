package br.com.produtos.controller;


import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.produtos.controller.request.ProdutoRequestDTO;
import br.com.produtos.model.Produtos;
import br.com.produtos.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/products")
@Validated
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	ModelMapper mapper = new ModelMapper();

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Criação de um produto", response = Produtos.class)
	public ResponseEntity<?> criar(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
		log.info("Salvando um produto");
		Produtos produtos =  mapper.map(produtoRequestDTO, Produtos.class);
		return produtoService.salvar(produtos);
	}
	
	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Atualização de um produto", response = Produtos.class)
	public ResponseEntity<?> editar(@PathVariable("id") Integer id,@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
		log.info("Atualizando um produto pelo id");
		Produtos produtos =  mapper.map(produtoRequestDTO, Produtos.class);
		return produtoService.alterar(id, produtos);
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Busca de um produto por ID", response = Produtos.class)
	public ResponseEntity<?> getProdutoPorId(@PathVariable("id") Integer id) {
		log.info("Buscar um produto pelo id");
		return produtoService.consultarProdutoPeloId(id);
	}
	
	@GetMapping
	@ApiOperation(value = "Lista de produtos", response = Produtos.class)
	public ResponseEntity<?> getProdutos() {
		log.info("Buscar todos os produtos");
		return produtoService.listarTodosProdutos();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleção de um produto", response = Produtos.class)
	public ResponseEntity<?> excluir(@PathVariable("id") Integer id) {
		log.info("Deleção de um produto");
		return produtoService.delete(id);
	}
	
	@GetMapping("/search")
	@ApiOperation(value = "Lista de produtos filtrados", response = Produtos.class)
	public ResponseEntity<?> getProdutosFiltrados(@RequestParam("q") String q,
			@RequestParam("min_price") BigDecimal minPrice, @RequestParam("max_price") BigDecimal maxPrice) {
		log.info("Buscar um produto por filtros");
		return produtoService.consultarProdutosPorFiltros(q, minPrice, maxPrice);
	}

}
