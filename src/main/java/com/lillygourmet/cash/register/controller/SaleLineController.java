/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * SaleLine Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.SaleLine;
import com.lillygourmet.cash.register.service.SaleLineService;
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
public class SaleLineController {

	private static Logger _log = LoggerFactory.getLogger(SaleLineController.class);

	@Autowired
	private SaleLineService SaleLineService;

	@GetMapping("api/SaleLines")
	public ResponseEntity<List<SaleLine>> retrieveAllSaleLines() {
		_log.info("retrieve all SaleLines controller...!");
		List<SaleLine> SaleLines = SaleLineService.retrieveAllSaleLines();
		return new ResponseEntity<List<SaleLine>>(SaleLines, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/SaleLines/{id}")
	public ResponseEntity<SaleLine> retrieveSaleLineById(@PathVariable Long id) {

		_log.info("retrieve SaleLine controller...!");
		SaleLine SaleLine = SaleLineService.getSaleLine(id);
		return new ResponseEntity<SaleLine>(SaleLine, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/SaleLines")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SaleLine> createSaleLine(@RequestBody SaleLine SaleLine) {

		_log.info("create SaleLine controller...!");
		SaleLine savedSaleLine = SaleLineService.createSaleLine(SaleLine);
		return new ResponseEntity<SaleLine>(savedSaleLine, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/SaleLines/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteSaleLine(@PathVariable Long id) throws Exception {
		_log.info("delete SaleLine controller...!");
		SaleLineService.deleteSaleLine(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/SaleLines")
	public ResponseEntity<SaleLine> updateSaleLine(@RequestBody SaleLine SaleLine) {

		_log.info("update SaleLine controller...!");
		SaleLine updatedSaleLine= SaleLineService.updateSaleLine(SaleLine);
		return new ResponseEntity<SaleLine>(updatedSaleLine, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
