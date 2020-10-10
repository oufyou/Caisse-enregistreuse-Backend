/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.
 *
 * @author King
 */

@Entity
@AttributeOverride(name="id", column=@Column(name="employee_id"))
@Table(	name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cin")
        })
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee extends User{

    @NotBlank
    @Size(max = 155)
    @Column(name = "cin")
    private String cin;

    @NotBlank
    @Size(max = 20)
    private Float salaire;


    public Employee() {
    }

    public Employee(Long id, @NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Set<Role> roles, Date createdAt, String createdBy, Date updatedAt, String updatedBy, @NotBlank @Size(max = 155) String cin, @NotBlank @Size(max = 20) Float salaire) {
        super(id, firstName, lastName, sexe, bdate, adress, phone, email, username, password, roles, createdAt, createdBy, updatedAt, updatedBy);
        this.cin = cin;
        this.salaire = salaire;
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

    @Override
    public String toString() {
        return "Employee{" +
                "cin='" + cin + '\'' +
                ", salaire=" + salaire +
                '}';
    }
}