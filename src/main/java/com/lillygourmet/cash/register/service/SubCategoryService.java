/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * SubCategory Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.SubCategory;
import com.lillygourmet.cash.register.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository SubCategoryRepository;

	public List<SubCategory> retrieveAllSubCategoriesByCategory(long id) {
		return SubCategoryRepository.findAllByCategory_Id(id);
	}

	public List<SubCategory> retrieveAllSubCategories() {
		return SubCategoryRepository.findAll();
	}

	public SubCategory getSubCategory(Long id) {
		return SubCategoryRepository.getOne(id);
	}

	public SubCategory createSubCategory(SubCategory SubCategory) {
		return SubCategoryRepository.save(SubCategory);
	}
	
	@Transactional
	public void deleteSubCategory(Long id) throws Exception {

		SubCategory SubCategory = SubCategoryRepository.getOne(id);
		if (SubCategory != null) {
			SubCategoryRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public SubCategory updateSubCategory(SubCategory SubCategory) {
		return SubCategoryRepository.save(SubCategory);
	}
}
