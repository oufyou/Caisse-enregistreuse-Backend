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

import com.lillygourmet.cash.register.repository.TicketXRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TicketXController {

    @Autowired
    private TicketXRepository ticketXRepository;

    private static Logger _log = LoggerFactory.getLogger(TicketXController.class);

    // get Ticket X between last open datehours and now()

    @RequestMapping(value="api/ticketX/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getticketZJour(@PathVariable Long id) {

        List<Object[]> Betweendestickets_X = ticketXRepository.Betweendestickets_X(id);
        Object[] Totaldestickets_X = ticketXRepository.Totaldestickets_X(id);
        Object[] Totaldescredits_X = ticketXRepository.Totaldescredits_X(id);
        Object[] Totalencaissements_X = ticketXRepository.Totalencaissements_X(id);
        Object[] TotalfactureHT_X = ticketXRepository.TotalfactureHT_X(id);
        Object[] TotalfactureTTC_X = ticketXRepository.TotalfactureTTC_X(id);
        Object[] TotalfactureTVA_X = ticketXRepository.TotalfactureTVA_X(id);
        List<Object[]> TotalTTCparVendeur_X = ticketXRepository.TotalTTCparVendeur_X(id);
        List<Object[]> TotalEncaissementsParTypePayement_X = ticketXRepository.TotalEncaissementsParTypePayement_X(id);
        JSONObject entity = new JSONObject();
        String between = Betweendestickets_X.get(0)[0].toString()+" || "+Betweendestickets_X.get(0)[1].toString();
        entity.put("Ticket_X_Entre", between);
        entity.put("Total_des_tickets", Totaldestickets_X[0]);
        entity.put("Total_credits_des_tickets", Totaldescredits_X[0]);
        entity.put("Totalencaissements", Totalencaissements_X[0]);
        entity.put("TotalfactureHT", TotalfactureHT_X[0]);
        entity.put("TotalfactureTTC", TotalfactureTTC_X[0]);
        entity.put("TotalfactureTVA", TotalfactureTVA_X[0]);

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for(int i=0;i<TotalTTCparVendeur_X.size();i++){
            JSONObject entitys = new JSONObject();
            entitys.put("id_caissier", TotalTTCparVendeur_X.get(i)[0]);
            entitys.put("first_name", TotalTTCparVendeur_X.get(i)[1]);
            entitys.put("last_name", TotalTTCparVendeur_X.get(i)[2]);
            entitys.put("total",TotalTTCparVendeur_X.get(i)[3]);
            entities.add(entitys);
        }
        entity.put("TotalTTCparVendeur",entities);

        List<JSONObject> entitiess = new ArrayList<JSONObject>();
        for(int i=0;i<TotalEncaissementsParTypePayement_X.size();i++){
            JSONObject entitys = new JSONObject();
            entitys.put("type_payement", TotalEncaissementsParTypePayement_X.get(i)[0]);
            entitys.put("total",TotalEncaissementsParTypePayement_X.get(i)[1]);
            entitiess.add(entitys);
        }
        entity.put("TotalEncaissementsParTypePayement",entitiess);

        _log.info("Ticket_X_selected ...!");
        return  entity.toString();
    }



}
