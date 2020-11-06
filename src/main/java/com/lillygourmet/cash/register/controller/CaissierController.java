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

import java.sql.ResultSet;
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
	@RequestMapping(value="api/Caissiers", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String getAll() {
		List<Object[]> entityList = caissierRepository.findUsersbyRole("ROLE_CASHIER");

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

	@GetMapping("api/Caissierss")
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
