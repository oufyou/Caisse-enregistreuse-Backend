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

import com.lillygourmet.cash.register.Exception.ResourceNotFoundException;
import com.lillygourmet.cash.register.model.Category;
import com.lillygourmet.cash.register.model.SessionPOS;
import com.lillygourmet.cash.register.repository.CategoryRepository;
import com.lillygourmet.cash.register.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CategoryController {

	private static Logger _log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService CategoryService;

	@Autowired
	private CategoryRepository categoryRepository;

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

	// Updating Category
	@PutMapping("api/Categories/{id}")
	public ResponseEntity<Category> updateSessionPOS(@PathVariable Long id, @RequestBody String Category)throws ResourceNotFoundException {
		Category cat = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));
		JsonParser parser = new BasicJsonParser();
		Map<String, Object> CategoryPOSJson = parser.parseMap(Category);
		// Mapping Category object by JSON strategy
		cat.setNom(CategoryPOSJson.get("Nom").toString());
		cat.setDescription(CategoryPOSJson.get("Description").toString());
		Category updatedCategory = CategoryService.updateCategory(cat);
		_log.info("update Category controller...!");
		return new ResponseEntity<Category>(updatedCategory, new HttpHeaders(), HttpStatus.ACCEPTED);
	}

}
