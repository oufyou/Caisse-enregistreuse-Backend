/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * SaleLine Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.SaleLine;
import com.lillygourmet.cash.register.repository.SaleLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SaleLineService {

	@Autowired
	private SaleLineRepository SaleLineRepository;


	public List<SaleLine> retrieveAllSaleLines() {
		return SaleLineRepository.findAll();
	}

	public SaleLine getSaleLine(Long id) {
		return SaleLineRepository.getOne(id);
	}

	public SaleLine createSaleLine(SaleLine SaleLine) {
		return SaleLineRepository.save(SaleLine);
	}
	
	@Transactional
	public void deleteSaleLine(Long id) throws Exception {

		SaleLine SaleLine = SaleLineRepository.getOne(id);
		if (SaleLine != null) {
			SaleLineRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public SaleLine updateSaleLine(SaleLine SaleLine) {
		SaleLine editedSaleLine = SaleLineRepository.getOne(SaleLine.getId());
		editedSaleLine.setProduct(SaleLine.getProduct());
		editedSaleLine.setQuantity(SaleLine.getQuantity());
		return SaleLineRepository.save(editedSaleLine);
	}
}
