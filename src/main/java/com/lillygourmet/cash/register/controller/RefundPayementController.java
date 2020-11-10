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
import com.lillygourmet.cash.register.model.SaleLine;
import com.lillygourmet.cash.register.model.SessionPOS;
import com.lillygourmet.cash.register.repository.RefundRepository;
import com.lillygourmet.cash.register.repository.SaleRepository;
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
public class RefundPayementController {

    private static Logger _log = LoggerFactory.getLogger(RefundPayementController.class);

    @Autowired
    private RefundRepository refundRepository;



    @GetMapping("api/RefundPäyment")
    @PreAuthorize("hasAnyRole('ADMIN','CASHIER')")
    public ResponseEntity<Boolean> retrieveSaleById(@RequestBody String refundpayment) {
        Boolean rep = false;
        JsonParser parser = new BasicJsonParser();
        Map<String, Object> refundJson = parser.parseMap(refundpayment);
        // Mapping refunded payment by JSON strategy
        int insert = refundRepository.refundPayement(refundJson.get("comment").toString(),Float.parseFloat(refundJson.get("montant").toString()),Float.parseFloat(refundJson.get("rendre").toString()),refundJson.get("type").toString(),Long.parseLong(refundJson.get("sale_id").toString()));
        if(insert>0){
            _log.info("refund record added controller...!");
            rep=true;
        }else{
            _log.info("refund record not added controller...!");
        }
        int update = refundRepository.updatePaymentRefund(Long.parseLong(refundJson.get("sale_id").toString()));
        _log.info("refund payment executed controller...!");
        return new ResponseEntity<Boolean>(rep, new HttpHeaders(), HttpStatus.OK);
    }

}
