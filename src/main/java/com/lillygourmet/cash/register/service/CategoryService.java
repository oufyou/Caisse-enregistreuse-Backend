/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * Category Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.Category;
import com.lillygourmet.cash.register.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository CategoryRepository;


	public List<Category> retrieveAllCategories() {
		return CategoryRepository.findAll();
	}

	public Category getCategory(Long id) {
		return CategoryRepository.getOne(id);
	}

	public Category createCategory(Category Category) {
		return CategoryRepository.save(Category);
	}
	
	@Transactional
	public void deleteCategory(Long id) throws Exception {

		Category Category = CategoryRepository.getOne(id);
		if (Category != null) {
			CategoryRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public Category updateCategory(Category Category) {
		Category editedCategory = CategoryRepository.getOne(Category.getId());
		editedCategory.setNom(Category.getNom());
		editedCategory.setDescription(Category.getDescription());
		editedCategory.setImagelink(Category.getImagelink());
		editedCategory.setSubcategories(Category.getSubcategories());
		return CategoryRepository.save(editedCategory);
	}
}
