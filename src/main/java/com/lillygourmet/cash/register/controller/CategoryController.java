/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * Category Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.Category;
import com.lillygourmet.cash.register.service.CategoryService;
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
public class CategoryController {

	private static Logger _log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService CategoryService;

	@GetMapping("api/Categories")
	public ResponseEntity<List<Category>> retrieveAllCategories() {
		_log.info("retrieve all Categories controller...!");
		List<Category> Categories = CategoryService.retrieveAllCategories();
		return new ResponseEntity<List<Category>>(Categories, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/Categories/{id}")
	public ResponseEntity<Category> retrieveCategoryById(@PathVariable Long id) {

		_log.info("retrieve Category controller...!");
		Category Category = CategoryService.getCategory(id);
		return new ResponseEntity<Category>(Category, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/Categories")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Category> createCategory(@RequestBody Category Category) {

		_log.info("create Category controller...!");
		Category savedCategory = CategoryService.createCategory(Category);
		return new ResponseEntity<Category>(savedCategory, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/Categories/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteCategory(@PathVariable Long id) throws Exception {

		_log.info("delete Category controller...!");
		CategoryService.deleteCategory(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/Categories")
	public ResponseEntity<Category> updateCategory(@RequestBody Category Category) {

		_log.info("update Category controller...!");
		Category updatedCategory= CategoryService.updateCategory(Category);
		return new ResponseEntity<Category>(updatedCategory, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
