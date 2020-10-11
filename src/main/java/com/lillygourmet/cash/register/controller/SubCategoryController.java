/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * SubCategory Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.SaleLine;
import com.lillygourmet.cash.register.model.SubCategory;
import com.lillygourmet.cash.register.service.CategoryService;
import com.lillygourmet.cash.register.service.ProductService;
import com.lillygourmet.cash.register.service.SubCategoryService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SubCategoryController {

	private static Logger _log = LoggerFactory.getLogger(SubCategoryController.class);

	@Autowired
	private SubCategoryService SubCategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("api/SubCategories")
	public ResponseEntity<List<SubCategory>> retrieveAllSubCategories() {
		_log.info("retrieve all SubCategories controller...!");
		List<SubCategory> SubCategories = SubCategoryService.retrieveAllSubCategories();
		return new ResponseEntity<List<SubCategory>>(SubCategories, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/SubCategories/{id}")
	public ResponseEntity<SubCategory> retrieveSubCategoryById(@PathVariable Long id) {

		_log.info("retrieve SubCategory controller...!");
		SubCategory SubCategory = SubCategoryService.getSubCategory(id);
		return new ResponseEntity<SubCategory>(SubCategory, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/SubCategories")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SubCategory> createSubCategory(@RequestBody String SubCategory) {
		JsonParser parser = new BasicJsonParser();
		Map<String, Object> subcategory = parser.parseMap(SubCategory);
		// mapping of subcategory object
		SubCategory sb = new SubCategory(subcategory.get("nom").toString(),subcategory.get("description").toString(),subcategory.get("imagelink").toString(),categoryService.getCategory(Long.parseLong(subcategory.get("category_id").toString())));
		_log.info("create SubCategory controller...!");
		SubCategory savedSubCategory = SubCategoryService.createSubCategory(sb);
		return new ResponseEntity<SubCategory>(savedSubCategory, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/SubCategories/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteSubCategory(@PathVariable Long id) throws Exception {
		_log.info("delete SubCategory controller...!");
		SubCategoryService.deleteSubCategory(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/SubCategories")
	public ResponseEntity<SubCategory> updateSubCategory(@RequestBody SubCategory SubCategory) {
		_log.info("update SubCategory controller...!");
		SubCategory updatedSubCategory= SubCategoryService.updateSubCategory(SubCategory);
		return new ResponseEntity<SubCategory>(updatedSubCategory, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
