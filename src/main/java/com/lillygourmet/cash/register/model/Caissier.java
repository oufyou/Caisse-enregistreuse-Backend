/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * The type User.
 *
 * @author King
 */

@Entity
@Table(	name = "caissiers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code")
        })
@EntityListeners(AuditingEntityListener.class)
public class Caissier extends Employee{

    @NotBlank
    @Size(max = 155)
    @Column(name = "code")
    private String code;

    public Caissier() {
    }

    public Caissier(Long id, @NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Set<Role> roles, Date createdAt, String createdBy, Date updatedAt, String updatedBy, @NotBlank @Size(max = 155) String cin, @NotBlank @Size(max = 20) Float salaire, @NotBlank @Size(max = 155) String code) {
        super(id, firstName, lastName, sexe, bdate, adress, phone, email, username, password, roles, createdAt, createdBy, updatedAt, updatedBy, cin, salaire);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Caissier{" +
                "code='" + code + '\'' +
                '}';
    }
}