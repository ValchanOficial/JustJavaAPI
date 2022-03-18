package br.com.valchan.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.valchan.api.model.entities.Product;
import br.com.valchan.api.model.repositories.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/page/{page}")
	public Iterable<Product> getProductsPerPage(@PathVariable int page) {
		Pageable pageable = PageRequest.of(page, 5);
		return productRepository.findAll(pageable);
	}
	
	@GetMapping("/name/{name}")
	public Iterable<Product> getProductsByName(@PathVariable String name) {
		return productRepository.findByNameContainingIgnoreCase(name);
	}
	
	@GetMapping("/like/{name}")
	public Iterable<Product> getProductsByNameLike(@PathVariable String name) {
		return productRepository.searchByNameLike(name);
	}

	@RequestMapping("/{id}")
	public Optional<Product> getProductById(@PathVariable int id) {
		return productRepository.findById(id);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public @ResponseBody Product saveProduct(@Valid Product product) {
		productRepository.save(product);
		return product;
	}

	public void deleteProductById(@PathVariable int id) {
		productRepository.deleteById(id);
	}
}
