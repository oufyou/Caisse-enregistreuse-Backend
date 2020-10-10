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
import com.lillygourmet.cash.register.service.CaissierService;
import com.lillygourmet.cash.register.service.CustomerService;
import com.lillygourmet.cash.register.service.ProductService;
import com.lillygourmet.cash.register.service.SaleService;
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
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SaleController {

    private static Logger _log = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService SaleService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CaissierService caissierService;
    @Autowired
    private ProductService productService;

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
    public ResponseEntity<Sale> createSale(@RequestBody String Sales) {
        JsonParser parser = new BasicJsonParser();
        Map<String, Object> saleJson = parser.parseMap(Sales);
        // get sale lines object and add it to a list
        ArrayList salelines = new ArrayList<>();
        List<SaleLine> saleLineList = new ArrayList<>();

        ((ArrayList) saleJson.get("saleLines")).forEach(saleline -> {
            Long idproduct = Long.parseLong(((Map<String, Object>) saleline).get("product_id").toString());
            Float quantity = Float.parseFloat(((Map<String, Object>) saleline).get("quantity").toString());
            SaleLine sl = new SaleLine(productService.getProduct(idproduct), quantity);
            saleLineList.add(sl);
        });

/*
		salelines = (ArrayList) saleJson.get("saleLines");
		salelines.forEach(saleline -> {
			SaleLine sl = new SaleLine(productService.getProduct(Long.parseLong(((LinkedHashMap) saleline).get("product_id").toString())), Float.parseFloat(((LinkedHashMap) saleline).get("quantity").toString()));
			saleLineList.add(sl);
		});

 */

        Sale Sale = new Sale(customerService.getCustomer(Long.parseLong(saleJson.get("customer_id").toString())), caissierService.getCaissier(Long.parseLong(saleJson.get("caissier_id").toString())), saleLineList, Float.parseFloat(saleJson.get("total").toString()), Boolean.parseBoolean(saleJson.get("finished").toString()),saleJson.get("comment").toString());

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
        Sale updatedSale = SaleService.updateSale(Sale);
        return new ResponseEntity<Sale>(updatedSale, new HttpHeaders(), HttpStatus.ACCEPTED);
    }
}
