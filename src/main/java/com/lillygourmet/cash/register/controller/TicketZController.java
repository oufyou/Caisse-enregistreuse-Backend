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
import com.lillygourmet.cash.register.repository.TicketZRepository;
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
public class TicketZController {

    @Autowired
    private TicketZRepository ticketZRepository;

    private static Logger _log = LoggerFactory.getLogger(TicketZController.class);

    // get Ticket Z par jour
    @RequestMapping(value="api/ticketZJour/{date}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getticketZJour(@PathVariable String date) {

        Object[] Totaldestickets_Jour = ticketZRepository.Totaldestickets_Jour(date);
        Object[] Totaldescredits_Jour = ticketZRepository.Totaldescredits_Jour(date);
        Object[] Totalencaissements_Jour = ticketZRepository.Totalencaissements_Jour(date);
        Object[] TotalfactureHT_Jour = ticketZRepository.TotalfactureHT_Jour(date);
        Object[] TotalfactureTTC_Jour = ticketZRepository.TotalfactureTTC_Jour(date);
        Object[] TotalfactureTVA_Jour = ticketZRepository.TotalfactureTVA_Jour(date);
        List<Object[]> TotalTTCparVendeur_Jour = ticketZRepository.TotalTTCparVendeur_Jour(date);
        List<Object[]> TotalEncaissementsParTypePayement_Jour = ticketZRepository.TotalEncaissementsParTypePayement_Jour(date);
        JSONObject entity = new JSONObject();
        entity.put("Ticket Z du Jour", date);
        entity.put("Total_des_tickets_Jour", Totaldestickets_Jour[0]);
        entity.put("Total_credits_des_tickets_Jour", Totaldescredits_Jour[0]);
        entity.put("Totalencaissements_Jour", Totalencaissements_Jour[0]);
        entity.put("TotalfactureHT_Jour", TotalfactureHT_Jour[0]);
        entity.put("TotalfactureTTC_Jour", TotalfactureTTC_Jour[0]);
        entity.put("TotalfactureTVA_Jour", TotalfactureTVA_Jour[0]);

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for(int i=0;i<TotalTTCparVendeur_Jour.size();i++){
            JSONObject entitys = new JSONObject();
            entitys.put("id_caissier", TotalTTCparVendeur_Jour.get(i)[0]);
            entitys.put("first_name", TotalTTCparVendeur_Jour.get(i)[1]);
            entitys.put("last_name", TotalTTCparVendeur_Jour.get(i)[2]);
            entitys.put("total",TotalTTCparVendeur_Jour.get(i)[3]);
            entities.add(entitys);
        }
        entity.put("TotalTTCparVendeur_Jour",entities);

        List<JSONObject> entitiess = new ArrayList<JSONObject>();
        for(int i=0;i<TotalEncaissementsParTypePayement_Jour.size();i++){
            JSONObject entitys = new JSONObject();
            entitys.put("type_payement", TotalEncaissementsParTypePayement_Jour.get(i)[0]);
            entitys.put("total",TotalEncaissementsParTypePayement_Jour.get(i)[1]);
            entitiess.add(entitys);
        }
        entity.put("TotalEncaissementsParTypePayement_Jour",entitiess);

        _log.info("Ticket_Z_du_jour_selected ...!");
        return  entity.toString();
    }

    // get Ticket Z par mois
    @RequestMapping(value="api/ticketZMois/{date}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getticketZMois(@PathVariable String date) {

        Object[] Totaldestickets_Mois = ticketZRepository.Totaldestickets_Mois(date);
        Object[] Totaldescredits_Mois = ticketZRepository.Totaldescredits_Mois(date);
        Object[] Totalencaissements_Mois = ticketZRepository.Totalencaissements_Mois(date);
        Object[] TotalfactureHT_Mois = ticketZRepository.TotalfactureHT_Mois(date);
        Object[] TotalfactureTTC_Mois = ticketZRepository.TotalfactureTTC_Mois(date);
        Object[] TotalfactureTVA_Mois = ticketZRepository.TotalfactureTVA_Mois(date);
        List<Object[]> TotalTTCparVendeur_Mois = ticketZRepository.TotalTTCparVendeur_Mois(date);
        List<Object[]> TotalEncaissementsParTypePayement_Mois = ticketZRepository.TotalEncaissementsParTypePayement_Mois(date);
        JSONObject entity = new JSONObject();
        entity.put("Ticket Z du Mois", date);
        entity.put("Total_des_tickets_Mois", Totaldestickets_Mois[0]);
        entity.put("Total_credits_des_tickets_Mois", Totaldescredits_Mois[0]);
        entity.put("Totalencaissements_Mois", Totalencaissements_Mois[0]);
        entity.put("TotalfactureHT_Mois", TotalfactureHT_Mois[0]);
        entity.put("TotalfactureTTC_Mois", TotalfactureTTC_Mois[0]);
        entity.put("TotalfactureTVA_Mois", TotalfactureTVA_Mois[0]);

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for(int i=0;i<TotalTTCparVendeur_Mois.size();i++){
            JSONObject entitys = new JSONObject();
            entitys.put("id_caissier", TotalTTCparVendeur_Mois.get(i)[0]);
            entitys.put("first_name", TotalTTCparVendeur_Mois.get(i)[1]);
            entitys.put("last_name", TotalTTCparVendeur_Mois.get(i)[2]);
            entitys.put("total",TotalTTCparVendeur_Mois.get(i)[3]);
            entities.add(entitys);
        }
        entity.put("TotalTTCparVendeur_Mois",entities);

        List<JSONObject> entitiess = new ArrayList<JSONObject>();
        for(int i=0;i<TotalEncaissementsParTypePayement_Mois.size();i++){
            JSONObject entitys = new JSONObject();
            entitys.put("type_payement", TotalEncaissementsParTypePayement_Mois.get(i)[0]);
            entitys.put("total",TotalEncaissementsParTypePayement_Mois.get(i)[1]);
            entitiess.add(entitys);
        }
        entity.put("TotalEncaissementsParTypePayement_Mois",entitiess);

        _log.info("Ticket_Z_du_mois_selected ...!");
        return  entity.toString();
    }


}
