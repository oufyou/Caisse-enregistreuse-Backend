/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * Sale Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.Sale;
import com.lillygourmet.cash.register.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SaleService {

	@Autowired
	private SaleRepository SaleRepository;


	public List<Sale> retrieveAllSales() {
		return SaleRepository.findAll();
	}

	public Sale getSale(Long id) {
		return SaleRepository.getOne(id);
	}

	public Sale createSale(Sale Sale) {
		return SaleRepository.save(Sale);
	}
	
	@Transactional
	public void deleteSale(Long id) throws Exception {

		Sale Sale = SaleRepository.getOne(id);
		if (Sale != null) {
			SaleRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public Sale updateSale(Sale Sale) {
		Sale editedSale = SaleRepository.getOne(Sale.getId());
		editedSale.setCaissier(Sale.getCaissier());
		editedSale.setCustomer(Sale.getCustomer());
		editedSale.setDateheures(Sale.getDateheures());
		editedSale.setFinished(Sale.getFinished());
		editedSale.setSaleLines(Sale.getSaleLines());
		editedSale.setTotal(Sale.getTotal());
		return SaleRepository.save(editedSale);
	}
}
