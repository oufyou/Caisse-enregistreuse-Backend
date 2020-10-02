/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * Product Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.Product;
import com.lillygourmet.cash.register.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {

	private static Logger _log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService ProductService;

	@GetMapping("api/Products")
	public ResponseEntity<List<Product>> retrieveAllProducts() {
		_log.info("retrieve all Products controller...!");
		List<Product> Products = ProductService.retrieveAllProducts();
		return new ResponseEntity<List<Product>>(Products, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/Products/{id}")
	public ResponseEntity<Product> retrieveProductById(@PathVariable Long id) {
		_log.info("retrieve Product controller...!");
		Product Product = ProductService.getProduct(id);
		return new ResponseEntity<Product>(Product, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/Products")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> createProduct(@RequestBody Product Product) {

		_log.info("create Product controller...!");
		Product savedProduct = ProductService.createProduct(Product);
		return new ResponseEntity<Product>(savedProduct, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/Products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteProduct(@PathVariable Long id) throws Exception {

		_log.info("delete Product controller...!");
		ProductService.deleteProduct(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/Products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product Product) {

		_log.info("update Product controller...!");
		Product updatedProduct= ProductService.updateProduct(Product);
		return new ResponseEntity<Product>(updatedProduct, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
