/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * Waiter Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.Waiter;
import com.lillygourmet.cash.register.service.WaiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class WaiterController {

	private static Logger _log = LoggerFactory.getLogger(WaiterController.class);

	@Autowired
	private WaiterService WaiterService;

	@GetMapping("api/Waiters")
	public ResponseEntity<List<Waiter>> retrieveAllWaiters() {
		_log.info("retrieve all Waiters controller...!");
		List<Waiter> Waiters = WaiterService.retrieveAllWaiters();
		return new ResponseEntity<List<Waiter>>(Waiters, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/Waiters/{id}")
	public ResponseEntity<Waiter> retrieveWaiterById(@PathVariable Long id) {

		_log.info("retrieve Waiter controller...!");
		Waiter Waiter = WaiterService.getWaiter(id);
		return new ResponseEntity<Waiter>(Waiter, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/Waiters")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Waiter> createWaiter(@RequestBody Waiter Waiter) {

		_log.info("create Waiter controller...!");
		Waiter savedWaiter = WaiterService.createWaiter(Waiter);
		return new ResponseEntity<Waiter>(savedWaiter, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/Waiters/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteWaiter(@PathVariable Long id) throws Exception {
		_log.info("delete Waiter controller...!");
		WaiterService.deleteWaiter(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/Waiters")
	public ResponseEntity<Waiter> updateWaiter(@RequestBody Waiter Waiter) {

		_log.info("update Waiter controller...!");
		Waiter updatedWaiter= WaiterService.updateWaiter(Waiter);
		return new ResponseEntity<Waiter>(updatedWaiter, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
