/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * Product Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.Product;
import com.lillygourmet.cash.register.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository ProductRepository;


	public List<Product> retrieveAllProducts() {
		return ProductRepository.findAll();
	}

	public Product getProduct(Long id) {
		return ProductRepository.getOne(id);
	}

	public Product createProduct(Product Product) {
		return ProductRepository.save(Product);
	}
	
	@Transactional
	public void deleteProduct(Long id) throws Exception {

		Product Product = ProductRepository.getOne(id);
		if (Product != null) {
			ProductRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public Product updateProduct(Product Product) {
		Product editedProduct = ProductRepository.getOne(Product.getId());
		editedProduct.setNom(Product.getNom());
		editedProduct.setDescription(Product.getDescription());
		editedProduct.setPu(Product.getPu());
		editedProduct.setCodebarre(Product.getCodebarre());
		editedProduct.setCodecolor(Product.getCodecolor());
		editedProduct.setEtatexiste(Product.getEtatexiste());
		return ProductRepository.save(editedProduct);
	}
}
