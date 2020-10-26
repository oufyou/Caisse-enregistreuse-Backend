/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.repository;

import com.lillygourmet.cash.register.model.Caissier;
import com.lillygourmet.cash.register.model.Category;
import com.lillygourmet.cash.register.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CaissierRepository extends JpaRepository<Caissier, Long> {
    @Query(value="select u.* from users u,roles r,user_roles ur where r.name=?1 and u.id=ur.user_id and r.id=ur.role_id",nativeQuery=true)
    List<Object[]> findUsersbyRole(String role);
}
