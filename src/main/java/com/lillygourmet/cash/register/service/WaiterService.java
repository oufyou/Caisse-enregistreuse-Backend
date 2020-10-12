/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * Waiter Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.Waiter;
import com.lillygourmet.cash.register.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WaiterService {

	@Autowired
	private WaiterRepository WaiterRepository;


	public List<Waiter> retrieveAllWaiters() {
		return WaiterRepository.findAll();
	}

	public Waiter getWaiter(Long id) {
		return WaiterRepository.getOne(id);
	}

	public Waiter createWaiter(Waiter Waiter) {
		return WaiterRepository.save(Waiter);
	}
	
	@Transactional
	public void deleteWaiter(Long id) throws Exception {

		Waiter Waiter = WaiterRepository.getOne(id);
		if (Waiter != null) {
			WaiterRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public Waiter updateWaiter(Waiter Waiter) {
		Waiter editedWaiter = WaiterRepository.getOne(Waiter.getId());
		editedWaiter.setFirstName(Waiter.getFirstName());
		editedWaiter.setLastName(Waiter.getLastName());
		editedWaiter.setAdress(Waiter.getAdress());
		editedWaiter.setBdate(Waiter.getBdate());
		editedWaiter.setCin(Waiter.getCin());
		editedWaiter.setCode(Waiter.getCode());
		editedWaiter.setEmail(Waiter.getEmail());
		editedWaiter.setPhone(Waiter.getPhone());
		editedWaiter.setSexe(Waiter.getSexe());
		editedWaiter.setUsername(Waiter.getUsername());
		editedWaiter.setPassword(Waiter.getPassword());
		editedWaiter.setSalaire(Waiter.getSalaire());
		editedWaiter.setRoles(Waiter.getRoles());
		return WaiterRepository.save(editedWaiter);
	}
}
