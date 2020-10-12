/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * Payment Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.Payment;
import com.lillygourmet.cash.register.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository PaymentRepository;


	public List<Payment> retrieveAllPayments() {
		return PaymentRepository.findAll();
	}

	public Payment getPayment(Long id) {
		return PaymentRepository.getOne(id);
	}

	public Payment createPayment(Payment Payment) {
		return PaymentRepository.save(Payment);
	}
	
	@Transactional
	public void deletePayment(Long id) throws Exception {

		Payment Payment = PaymentRepository.getOne(id);
		if (Payment != null) {
			PaymentRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public Payment updatePayment(Payment Payment) {
		Payment editedPayment = PaymentRepository.getOne(Payment.getId());
		editedPayment.setMontant(Payment.getMontant());
		editedPayment.setClosed(Payment.getClosed());
		editedPayment.setComment(Payment.getComment());
		editedPayment.setRendre(Payment.getRendre());
		editedPayment.setType(Payment.getType());
		editedPayment.setSale(Payment.getSale());
		return PaymentRepository.save(editedPayment);
	}
}
