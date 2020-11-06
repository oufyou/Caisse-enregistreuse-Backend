/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * SessionPOS Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.SessionPOS;
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

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SessionPOSController {

    private static Logger _log = LoggerFactory.getLogger(SessionPOSController.class);

    @Autowired
    private SessionPOSService SessionPOSService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CaissierService caissierService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleService saleService;

    @GetMapping("api/SessionPOSs")
    public ResponseEntity<List<SessionPOS>> retrieveAllSessionPOSs() {
        _log.info("retrieve all SessionPOSs controller...!");
        List<SessionPOS> SessionPOSs = SessionPOSService.retrieveAllSessionPOSs();
        return new ResponseEntity<List<SessionPOS>>(SessionPOSs, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("api/SessionPOSs/{id}")
    public ResponseEntity<SessionPOS> retrieveSessionPOSById(@PathVariable Long id) {
        _log.info("retrieve SessionPOS controller...!");
        SessionPOS SessionPOS = SessionPOSService.getSessionPOS(id);
        return new ResponseEntity<SessionPOS>(SessionPOS, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("api/SessionPOSs")
    @PreAuthorize("hasAnyRole('ADMIN','CASHIER')")
    public ResponseEntity<SessionPOS> createSessionPOS(@RequestBody String SessionPOS) {
        JsonParser parser = new BasicJsonParser();
        Map<String, Object> SessionPOSJson = parser.parseMap(SessionPOS);
        // Mapping SessionPOS object by JSON strategy
        SessionPOS sp = new SessionPOS(caissierService.getCaissier(Long.parseLong(SessionPOSJson.get("caissier_id").toString())),Float.parseFloat(SessionPOSJson.get("OpenMontant").toString()),Float.parseFloat(SessionPOSJson.get("CloseMontant").toString()),Boolean.parseBoolean(SessionPOSJson.get("State").toString()),SessionPOSJson.get("Comment").toString());
        _log.info("create SessionPOS controller...!");
        SessionPOS savedSessionPOS = SessionPOSService.createSessionPOS(sp);
        return new ResponseEntity<SessionPOS>(savedSessionPOS, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("api/SessionPOSs/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteSessionPOS(@PathVariable Long id) throws Exception {
        _log.info("delete SessionPOS controller...!");
        SessionPOSService.deleteSessionPOS(id);
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("api/SessionPOSs")
    public ResponseEntity<SessionPOS> updateSessionPOS(@RequestBody SessionPOS SessionPOS) {
        _log.info("update SessionPOS controller...!");
        SessionPOS updatedSessionPOS = SessionPOSService.updateSessionPOS(SessionPOS);
        return new ResponseEntity<SessionPOS>(updatedSessionPOS, new HttpHeaders(), HttpStatus.ACCEPTED);
    }
}
