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

import com.lillygourmet.cash.register.Exception.ResourceNotFoundException;
import com.lillygourmet.cash.register.model.SessionPOS;
import com.lillygourmet.cash.register.repository.SessionPOSRepository;
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
    @Autowired
    private  UserService userService;
@Autowired
private SessionPOSRepository sessionPOSRepository;
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

    // ouverture de caisse
    @PostMapping("api/SessionPOSs")
    @PreAuthorize("hasAnyRole('ADMIN','CASHIER')")
    public ResponseEntity<SessionPOS> createSessionPOS(@RequestBody String SessionPOS) {
        JsonParser parser = new BasicJsonParser();
        Map<String, Object> SessionPOSJson = parser.parseMap(SessionPOS);
        // Mapping SessionPOS object by JSON strategy
        SessionPOS sp = new SessionPOS(userService.getUser(Long.parseLong(SessionPOSJson.get("caissier_id").toString())),Float.parseFloat(SessionPOSJson.get("OpenMontant").toString()),Float.parseFloat(SessionPOSJson.get("CloseMontant").toString()),Boolean.parseBoolean(SessionPOSJson.get("State").toString()),SessionPOSJson.get("Comment").toString());
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

    // Fermeture de caisse
    @PutMapping("api/SessionPOSs/{id}")
    public ResponseEntity<SessionPOS> updateSessionPOS(@PathVariable Long id,@RequestBody String SessionPOS)throws ResourceNotFoundException {
        SessionPOS spos = sessionPOSRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("SessionPOS not found for this id :: " + id));
        JsonParser parser = new BasicJsonParser();
        Map<String, Object> SessionPOSJson = parser.parseMap(SessionPOS);
        // Mapping SessionPOS object by JSON strategy
        // SessionPOS sp = new SessionPOS(userService.getUser(Long.parseLong(SessionPOSJson.get("caissier_id").toString())),Float.parseFloat(SessionPOSJson.get("OpenMontant").toString()),Float.parseFloat(SessionPOSJson.get("CloseMontant").toString()),Boolean.parseBoolean(SessionPOSJson.get("State").toString()),SessionPOSJson.get("Comment").toString());
           spos.setUserCaissier(spos.getUserCaissier());
           spos.setOpenMontant(spos.getOpenMontant());
           spos.setCloseMontant(Float.parseFloat(SessionPOSJson.get("CloseMontant").toString()));
           spos.setState(true);
           spos.setComment(SessionPOSJson.get("Comment").toString());
        SessionPOS updatedSessionPOS = SessionPOSService.updateSessionPOS(spos);
        _log.info("update SessionPOS controller...!");
        return new ResponseEntity<SessionPOS>(updatedSessionPOS, new HttpHeaders(), HttpStatus.ACCEPTED);
    }
}
