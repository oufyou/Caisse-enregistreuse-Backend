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
@AttributeOverride(name="id", column=@Column(name="caissier_id"))
@Table(	name = "caissiers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code")
        })

@EntityListeners(AuditingEntityListener.class)
public class Caissier extends User{

    @NotBlank
    @Size(max = 155)
    @Column(name = "cin")
    private String cin;

    @NotBlank
    @Size(max = 20)
    private Float salaire;


    @NotBlank
    @Size(max = 155)
    @Column(name = "code")
    private String code;

    public Caissier() {
    }

    public Caissier(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, String createdBy, String updatedBy, @NotBlank @Size(max = 155) String cin, @NotBlank @Size(max = 20) Float salaire, @NotBlank @Size(max = 155) String code) {
        super(firstName, lastName, username, email, password, createdBy, updatedBy);
        this.cin = cin;
        this.salaire = salaire;
        this.code = code;
    }

    public Caissier(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, Set<Role> roles, String createdBy, String updatedBy, @NotBlank @Size(max = 155) String cin, @NotBlank @Size(max = 20) Float salaire, @NotBlank @Size(max = 155) String code) {
        super(firstName, lastName, username, email, password, roles, createdBy, updatedBy);
        this.cin = cin;
        this.salaire = salaire;
        this.code = code;
    }

    public Caissier(Long id, @NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Set<Role> roles, Date createdAt, String createdBy, Date updatedAt, String updatedBy, @NotBlank @Size(max = 155) String cin, @NotBlank @Size(max = 20) Float salaire, @NotBlank @Size(max = 155) String code) {
        super(id, firstName, lastName, sexe, bdate, adress, phone, email, username, password, roles, createdAt, createdBy, updatedAt, updatedBy);
        this.cin = cin;
        this.salaire = salaire;
        this.code = code;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Float getSalaire() {
        return salaire;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}