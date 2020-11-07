/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * Caissier Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.Caissier;
import com.lillygourmet.cash.register.repository.CaissierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CaissierService {

	@Autowired
	private CaissierRepository CaissierRepository;

	public List<Caissier> retrieveAllCaissiers() {
		return CaissierRepository.findAll();
	}

	public Caissier getCaissier(Long id) {
		return CaissierRepository.getOne(id);
	}

	public Caissier createCaissier(Caissier Caissier) {
		return CaissierRepository.save(Caissier);
	}
	
	@Transactional
	public void deleteCaissier(Long id) throws Exception {

		Caissier Caissier = CaissierRepository.getOne(id);
		if (Caissier != null) {
			CaissierRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public Caissier updateCaissier(Caissier Caissier) {
		Caissier editedCaissier = CaissierRepository.getOne(Caissier.getId());
		editedCaissier.setFirstName(Caissier.getFirstName());
		editedCaissier.setLastName(Caissier.getLastName());
		editedCaissier.setAdress(Caissier.getAdress());
		editedCaissier.setBdate(Caissier.getBdate());
		editedCaissier.setCin(Caissier.getCin());
		editedCaissier.setCode(Caissier.getCode());
		editedCaissier.setEmail(Caissier.getEmail());
		editedCaissier.setPhone(Caissier.getPhone());
		editedCaissier.setSexe(Caissier.getSexe());
		editedCaissier.setUsername(Caissier.getUsername());
		editedCaissier.setPassword(Caissier.getPassword());
		editedCaissier.setSalaire(Caissier.getSalaire());
		editedCaissier.setRoles(Caissier.getRoles());
		return CaissierRepository.save(editedCaissier);
	}
}
