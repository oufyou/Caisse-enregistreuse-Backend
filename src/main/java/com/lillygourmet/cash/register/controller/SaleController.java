/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * Sale Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.Sale;
import com.lillygourmet.cash.register.service.SaleService;
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
public class SaleController {

	private static Logger _log = LoggerFactory.getLogger(SaleController.class);

	@Autowired
	private SaleService SaleService;

	@GetMapping("api/Sales")
	public ResponseEntity<List<Sale>> retrieveAllSales() {
		_log.info("retrieve all Sales controller...!");
		List<Sale> Sales = SaleService.retrieveAllSales();
		return new ResponseEntity<List<Sale>>(Sales, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/Sales/{id}")
	public ResponseEntity<Sale> retrieveSaleById(@PathVariable Long id) {

		_log.info("retrieve Sale controller...!");
		Sale Sale = SaleService.getSale(id);
		return new ResponseEntity<Sale>(Sale, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/Sales")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Sale> createSale(@RequestBody Sale Sale) {

		_log.info("create Sale controller...!");
		Sale savedSale = SaleService.createSale(Sale);
		return new ResponseEntity<Sale>(savedSale, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/Sales/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteSale(@PathVariable Long id) throws Exception {
		_log.info("delete Sale controller...!");
		SaleService.deleteSale(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/Sales")
	public ResponseEntity<Sale> updateSale(@RequestBody Sale Sale) {

		_log.info("update Sale controller...!");
		Sale updatedSale= SaleService.updateSale(Sale);
		return new ResponseEntity<Sale>(updatedSale, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
