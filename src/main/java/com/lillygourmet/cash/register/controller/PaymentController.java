/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * Payment Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.Payment;
import com.lillygourmet.cash.register.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PaymentController {

    private static Logger _log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService PaymentService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CaissierService caissierService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleService saleService;

    @GetMapping("api/Payments")
    public ResponseEntity<List<Payment>> retrieveAllPayments() {
        _log.info("retrieve all Payments controller...!");
        List<Payment> Payments = PaymentService.retrieveAllPayments();
        return new ResponseEntity<List<Payment>>(Payments, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("api/Payments/{id}")
    public ResponseEntity<Payment> retrievePaymentById(@PathVariable Long id) {

        _log.info("retrieve Payment controller...!");
        Payment Payment = PaymentService.getPayment(id);
        return new ResponseEntity<Payment>(Payment, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("api/Payments")
    @PreAuthorize("hasAnyRole('ADMIN','CASHIER')")
    public ResponseEntity<Payment> createPayment(@RequestBody String Payment) {
        JsonParser parser = new BasicJsonParser();
        Map<String, Object> PaymentJson = parser.parseMap(Payment);
        // Mapping payment object by JSON strategy
        Payment p = new Payment(PaymentJson.get("type").toString(),Float.parseFloat(PaymentJson.get("montant").toString()),Float.parseFloat(PaymentJson.get("rendre").toString()),Boolean.parseBoolean(PaymentJson.get("closed").toString()),PaymentJson.get("comment").toString(),saleService.getSale(Long.parseLong(PaymentJson.get("sale_id").toString())));
        _log.info("create Payment controller...!");
        Payment savedPayment = PaymentService.createPayment(p);
        return new ResponseEntity<Payment>(savedPayment, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("api/Payments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deletePayment(@PathVariable Long id) throws Exception {
        _log.info("delete Payment controller...!");
        PaymentService.deletePayment(id);
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("api/Payments")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment Payment) {

        _log.info("update Payment controller...!");
        Payment updatedPayment = PaymentService.updatePayment(Payment);
        return new ResponseEntity<Payment>(updatedPayment, new HttpHeaders(), HttpStatus.ACCEPTED);
    }
}
