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

import com.lillygourmet.cash.register.model.User;
import com.lillygourmet.cash.register.model.Waiter;
import com.lillygourmet.cash.register.repository.WaiterRepository;
import com.lillygourmet.cash.register.service.WaiterService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class WaiterController {

	private static Logger _log = LoggerFactory.getLogger(WaiterController.class);

	@Autowired
	private WaiterService WaiterService;
	@Autowired
	private WaiterRepository waiterRepository;
	@GetMapping("api/Waiterss")
	public ResponseEntity<List<Waiter>> retrieveAllWaiters() {
		_log.info("retrieve all Waiters controller...!");
		List<Waiter> Waiters = WaiterService.retrieveAllWaiters();
		return new ResponseEntity<List<Waiter>>(Waiters, new HttpHeaders(), HttpStatus.OK);
	}

	// get users with role Waiter using JPA Query Jointure
	@RequestMapping(value="api/Waiters", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String getAll() {
		List<Object[]> entityList = waiterRepository.findUsersbyRole("ROLE_WAITER");
		List<JSONObject> entities = new ArrayList<JSONObject>();
		for(int i=0;i<entityList.size();i++){
			JSONObject entity = new JSONObject();
			entity.put("id", entityList.get(i)[0]);
			entity.put("adress",entityList.get(i)[1]);
			entity.put("bdate",entityList.get(i)[2]);
			entity.put("createdAt",entityList.get(i)[3]);
			entity.put("createdBy",entityList.get(i)[4]);
			entity.put("email",entityList.get(i)[5]);
			entity.put("firstName",entityList.get(i)[6]);
			entity.put("lastName",entityList.get(i)[7]);
			entity.put("password",entityList.get(i)[8]);
			entity.put("phone",entityList.get(i)[9]);
			entity.put("sexe",entityList.get(i)[10]);
			entity.put("updatedAt",entityList.get(i)[11]);
			entity.put("updatedBy",entityList.get(i)[12]);
			entity.put("username",entityList.get(i)[13]);
			entities.add(entity);
		}
		return  entities.toString();
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
