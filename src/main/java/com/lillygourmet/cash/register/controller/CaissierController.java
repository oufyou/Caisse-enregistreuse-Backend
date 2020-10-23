/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * Caissier Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.Caissier;
import com.lillygourmet.cash.register.model.User;
import com.lillygourmet.cash.register.repository.CaissierRepository;
import com.lillygourmet.cash.register.service.CaissierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CaissierController {

	private static Logger _log = LoggerFactory.getLogger(CaissierController.class);

	@Autowired
	private CaissierService CaissierService;

	@Autowired
	private CaissierRepository caissierRepository;

	// get users with role cashier using JPA Query Jointure
	@GetMapping("api/Caissiers/{role}")
	public ResponseEntity<List<User>> retrieveCaissierByRole(@PathVariable String role) {
		_log.info("retrieve Users with role cashier controller...!");
		List<User> users = caissierRepository.findUsersbyRole(role);
		return new ResponseEntity<List<User>>(users, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/Caissiers")
	public ResponseEntity<List<Caissier>> retrieveAllCaissiers() {
		_log.info("retrieve all Caissiers controller...!");
		List<Caissier> Caissiers = CaissierService.retrieveAllCaissiers();
		return new ResponseEntity<List<Caissier>>(Caissiers, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/Caissiers/{id}")
	public ResponseEntity<Caissier> retrieveCaissierById(@PathVariable Long id) {

		_log.info("retrieve Caissier controller...!");
		Caissier Caissier = CaissierService.getCaissier(id);
		return new ResponseEntity<Caissier>(Caissier, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/Caissiers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Caissier> createCaissier(@RequestBody Caissier Caissier) {

		_log.info("create Caissier controller...!");
		Caissier savedCaissier = CaissierService.createCaissier(Caissier);
		return new ResponseEntity<Caissier>(savedCaissier, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/Caissiers/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteCaissier(@PathVariable Long id) throws Exception {
		_log.info("delete Caissier controller...!");
		CaissierService.deleteCaissier(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/Caissiers")
	public ResponseEntity<Caissier> updateCaissier(@RequestBody Caissier Caissier) {

		_log.info("update Caissier controller...!");
		Caissier updatedCaissier= CaissierService.updateCaissier(Caissier);
		return new ResponseEntity<Caissier>(updatedCaissier, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
