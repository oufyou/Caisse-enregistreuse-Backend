package com.lillygourmet.cash.register.repository;

import com.lillygourmet.cash.register.model.RootEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketZRepository extends JpaRepository<RootEntity, Integer> {

    // Ticket Z Par Jour
    // total des tickets
    @Query(value="select sum(total) from sales where date(dateheures)=?1",nativeQuery=true)
    Object[] Totaldestickets_Jour(String date);

    // total des crédits sur tickets
    @Query(value="select -1*sum(p.rendre) from payments p,sales s where p.rendre<0 and date(s.dateheures)=?1 and p.sale_id=s.id",nativeQuery=true)
    Object[] Totaldescredits_Jour(String date);

    // total encaissements sur tickets
    @Query(value="select SUM(montant)-SUM(CASE WHEN rendre>=0 THEN rendre ELSE 0 END) from payments where date(dateheures)=?1",nativeQuery=true)
    Object[] Totalencaissements_Jour(String date);

    // CA TOTAL HT TTC TVA
    // HT
    @Query(value="select sum(total)-((sum(total)/120)*20) from sales where date(dateheures)=?1",nativeQuery=true)
    Object[] TotalfactureHT_Jour(String date);

    // TTC
    @Query(value="select sum(total) from sales where date(dateheures)=?1",nativeQuery=true)
    Object[] TotalfactureTTC_Jour(String date);

    // TVA
    @Query(value="select (sum(total)/120)*20 from sales where date(dateheures)=?1",nativeQuery=true)
    Object[] TotalfactureTVA_Jour(String date);

    // SOMME facturée ttc par vendeur caissier
    @Query(value="select s.caissier_id,u.first_name,u.last_name,sum(total) from sales s,users u where date(s.dateheures)=?1 and u.id=s.caissier_id group by s.caissier_id",nativeQuery=true)
    List<Object[]> TotalTTCparVendeur_Jour(String date);

    // Encaissements par type de payement
    @Query(value="select type,SUM(montant)-SUM(CASE WHEN rendre>=0 THEN rendre ELSE 0 END) " +
            "from payments where date(dateheures)=?1 group by type",nativeQuery=true)
    List<Object[]> TotalEncaissementsParTypePayement_Jour(String date);

    /*// total facturé par catégorie
    @Query(value="select c.nom ,sum(sl.quantity*(select p.pu from products p where sl.product_id=p.id))
    from salesline sl,categories c,subcategories s,products pr
    where pr.sub_category_id=s.id and c.id=s.category_id and date(dateheures)=?1 group by c.nom",nativeQuery=true)
    List<Object[]> TotalFactureParCategorie_Jour(String date);
    */

///////////////////////////////////////////////////////

    // Ticket Z par Mois
    // total des tickets
    @Query(value="select sum(total) from sales where year(dateheures)=EXTRACT(YEAR FROM ?1) and month(dateheures)=EXTRACT(MONTH FROM ?1)",nativeQuery=true)
    Object[] Totaldestickets_Mois(String date);

    // total des crédits sur tickets
    @Query(value="select -1*sum(p.rendre) from payments p,sales s where p.rendre<0 and year(s.dateheures)=EXTRACT(YEAR FROM ?1) and month(s.dateheures)=EXTRACT(MONTH FROM ?1) and p.sale_id=s.id",nativeQuery=true)
    Object[] Totaldescredits_Mois(String date);

    // total encaissements sur tickets
    @Query(value="select SUM(montant)-SUM(CASE WHEN rendre>=0 THEN rendre ELSE 0 END) from payments where year(dateheures)=EXTRACT(YEAR FROM ?1) and month(dateheures)=EXTRACT(MONTH FROM ?1)",nativeQuery=true)
    Object[] Totalencaissements_Mois(String date);

    // CA TOTAL HT TTC TVA
    // HT
    @Query(value="select sum(total)-((sum(total)/120)*20) from sales where year(dateheures)=EXTRACT(YEAR FROM ?1) and month(dateheures)=EXTRACT(MONTH FROM ?1)",nativeQuery=true)
    Object[] TotalfactureHT_Mois(String date);

    // TTC
    @Query(value="select sum(total) from sales where year(dateheures)=EXTRACT(YEAR FROM ?1) and month(dateheures)=EXTRACT(MONTH FROM ?1)",nativeQuery=true)
    Object[] TotalfactureTTC_Mois(String date);

    // TVA
    @Query(value="select (sum(total)/120)*20 from sales where year(dateheures)=EXTRACT(YEAR FROM ?1) and month(dateheures)=EXTRACT(MONTH FROM ?1)",nativeQuery=true)
    Object[] TotalfactureTVA_Mois(String date);

    // SOMME facturée ttc par vendeur caissier
    @Query(value="select s.caissier_id,u.first_name,u.last_name,sum(total) from sales s,users u where year(s.dateheures)=EXTRACT(YEAR FROM ?1) and month(s.dateheures)=EXTRACT(MONTH FROM ?1) and u.id=s.caissier_id group by s.caissier_id",nativeQuery=true)
    List<Object[]> TotalTTCparVendeur_Mois(String date);

    // Encaissements par type de payement
    @Query(value="select type,SUM(montant)-SUM(CASE WHEN rendre>=0 THEN rendre ELSE 0 END) from payments where year(dateheures)=EXTRACT(YEAR FROM ?1) and month(dateheures)=EXTRACT(MONTH FROM ?1) group by type",nativeQuery=true)
    List<Object[]> TotalEncaissementsParTypePayement_Mois(String date);

    /* total facturé par catégorie
    @Query(value="select c.nom ,sum(sl.quantity*(select p.pu from products p where sl.product_id=p.id))
    from salesline sl,categories c,subcategories s,products pr
    where pr.sub_category_id=s.id and c.id=s.category_id and date(dateheures)=?1 group by c.nom",nativeQuery=true)
    List<Object[]> TotalFactureParCategorie_Jour(String date);
    */
}