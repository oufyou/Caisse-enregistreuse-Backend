/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.repository;

import com.lillygourmet.cash.register.model.Payment;
import com.lillygourmet.cash.register.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RefundRepository extends CrudRepository<Payment, Long> {

    @Modifying
    @Query(value = "insert into payments (closed,comment,dateheures,montant,rendre,type,sale_id) VALUES (0,?1,NOW(),?2,?3,?4,?5)", nativeQuery = true)
    @Transactional
    int refundPayement(String comment,Float montant,Float rendre,String type,Long Saleid);

    @Modifying(clearAutomatically = true)
    @Query(value= "update payments set closed=1 where sale_id=?1 and rendre>=0",nativeQuery =true)
    @Transactional
    int updatePaymentRefund(Long id);


    // Getter le crédit restant à rembourser d'une vente donnée par un client donné
    @Query(value="SELECT p.id idp,p.closed,p.comment,p.dateheures,p.montant,p.rendre,p.type,p.sale_id FROM payments p,sales s WHERE p.sale_id=?1 and s.customer_id=?2 order by p.dateheures desc limit 1;",nativeQuery=true)
    List<Object[]> credit_a_rembourser(Long sale_id,Long customer_id);

}
