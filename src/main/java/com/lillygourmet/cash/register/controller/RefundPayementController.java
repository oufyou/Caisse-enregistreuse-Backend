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
import com.lillygourmet.cash.register.model.User;
import com.lillygourmet.cash.register.repository.RefundRepository;
import com.lillygourmet.cash.register.repository.SaleRepository;
import com.lillygourmet.cash.register.service.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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



    @GetMapping("api/RefundPayment")
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
    @RequestMapping(value="api/GetCredit/{sid}/{custid}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getCredit(@PathVariable Long sid,@PathVariable Long custid) {
        _log.info("retrieve crédit controller...!");
        List<Object[]> credit = refundRepository.credit_a_rembourser(sid,custid);
        JSONObject entity = new JSONObject();

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for(int i=0;i<credit.size();i++){
            JSONObject entitys = new JSONObject();
            entitys.put("id_payment", credit.get(i)[0]);
            entitys.put("closed", credit.get(i)[1]);
            entitys.put("comment", credit.get(i)[2]);
            entitys.put("dateheures_payement", credit.get(i)[3]);
            entitys.put("montant", credit.get(i)[4]);
            entitys.put("rendre", credit.get(i)[5]);
            entitys.put("type", credit.get(i)[6]);
            entitys.put("sale_id", credit.get(i)[7]);
            entities.add(entitys);
        }
        entity.put("credit",entities);



        return  entity.toString();
    }

}
