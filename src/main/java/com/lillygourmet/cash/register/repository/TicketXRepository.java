package com.lillygourmet.cash.register.repository;

import com.lillygourmet.cash.register.model.RootEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketXRepository extends JpaRepository<RootEntity, Integer> {

    // Ticket X

    // Ticket X entre last open datehours and now()
    @Query(value="select now(),max(sp.opendateheures) from sessionpos sp where user_caissier_id=?1",nativeQuery=true)
    List<Object[]> Betweendestickets_X(Long id);

    // total des tickets
    @Query(value="select sum(total) from sales ,sessionpos sp where user_caissier_id=?1 and sp.opendateheures BETWEEN (select max(spos.opendateheures) from sessionpos spos) and now()",nativeQuery=true)
    Object[] Totaldestickets_X(Long id);

    // total des crédits sur tickets
    @Query(value="select -1*sum(p.rendre) from payments p,sales s,sessionpos sp where user_caissier_id=?1 and sp.opendateheures BETWEEN ((select max(spos.opendateheures) from sessionpos spos) and now()) and p.rendre<0 and p.sale_id=s.id",nativeQuery=true)
    Object[] Totaldescredits_X(Long id);

    // total encaissements sur tickets
    @Query(value="select SUM(montant)-SUM(CASE WHEN rendre>=0 THEN rendre ELSE 0 END) from payments ,sessionpos sp where user_caissier_id=?1 and sp.opendateheures BETWEEN (select max(spos.opendateheures) from sessionpos spos) and now()",nativeQuery=true)
    Object[] Totalencaissements_X(Long id);

    // CA TOTAL HT TTC TVA
    // HT
    @Query(value="select sum(total)-((sum(total)/120)*20) from sales ,sessionpos sp where user_caissier_id=?1 and sp.opendateheures BETWEEN (select max(spos.opendateheures) from sessionpos spos) and now()",nativeQuery=true)
    Object[] TotalfactureHT_X(Long id);

    // TTC
    @Query(value="select sum(total) from sales ,sessionpos sp where user_caissier_id=?1 and sp.opendateheures BETWEEN (select max(spos.opendateheures) from sessionpos spos) and now()",nativeQuery=true)
    Object[] TotalfactureTTC_X(Long id);

    // TVA
    @Query(value="select (sum(total)/120)*20 from sales ,sessionpos sp where user_caissier_id=?1 and sp.opendateheures BETWEEN (select max(spos.opendateheures) from sessionpos spos) and now()",nativeQuery=true)
    Object[] TotalfactureTVA_X(Long id);

    // SOMME facturée ttc par vendeur caissier
    @Query(value="select s.caissier_id,u.first_name,u.last_name,sum(total) from sales s,users u ,sessionpos sp where user_caissier_id=?1 and sp.opendateheures BETWEEN ((select max(spos.opendateheures) from sessionpos spos) and now()) and u.id=s.caissier_id group by s.caissier_id",nativeQuery=true)
    List<Object[]> TotalTTCparVendeur_X(Long id);

    // Encaissements par type de payement
    @Query(value="select type,SUM(montant)-SUM(CASE WHEN rendre>=0 THEN rendre ELSE 0 END) from payments ,sessionpos sp where user_caissier_id=?1 and sp.opendateheures BETWEEN (select max(spos.opendateheures) from sessionpos spos) and now() group by type",nativeQuery=true)
    List<Object[]> TotalEncaissementsParTypePayement_X(Long id);

    /*// total facturé par catégorie
    @Query(value="select c.nom ,sum(sl.quantity*(select p.pu from products p where sl.product_id=p.id))
    from salesline sl,categories c,subcategories s,products pr
    where pr.sub_category_id=s.id and c.id=s.category_id and date(dateheures)=?1 group by c.nom",nativeQuery=true)
    List<Object[]> TotalFactureParCategorie_Jour(String date);
    */

///////////////////////////////////////////////////////

}