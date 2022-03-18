package br.com.valchan.api.model.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.valchan.api.model.entities.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	
	public Iterable<Product> findByNameContainingIgnoreCase(String name);
	
	// findByNameStartsWith
	// findByNameEndsWith
	// findByNameNotContaining
	
	// Query example
	@Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
	public Iterable<Product> searchByNameLike(@Param("name") String name);
}
