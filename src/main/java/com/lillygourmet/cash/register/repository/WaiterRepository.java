/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.repository;

import com.lillygourmet.cash.register.model.Caissier;
import com.lillygourmet.cash.register.model.User;
import com.lillygourmet.cash.register.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter, Long> {
    @Query(
            value = "select * from users u,roles r,user_roles ur where r.name=?1 and u.id=ur.user_id and r.id=ur.role_id",
            nativeQuery = true)
    ArrayList<User> findUsersbyRole(String role);
}
