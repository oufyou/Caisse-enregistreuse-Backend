/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * The type User.
 *
 * @author King
 */

@Entity
@Table(	name = "payments")
@EntityListeners(AuditingEntityListener.class)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String type;

    @NotBlank
    @Size(max = 255)
    private Float montant;

    @NotBlank
    @Size(max = 255)
    private Float rendre;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateheures", nullable = false)
    private Date dateheures;

    @NotBlank
    private Boolean closed;

    @NotBlank
    @Column(nullable = true)
    private String comment;

    @OneToOne(cascade = {CascadeType.REMOVE})
    private Sale sale;

    public Payment() {
    }

    public Payment(Long id, @NotBlank @Size(max = 255) String type, @NotBlank @Size(max = 255) Float montant, @NotBlank @Size(max = 255) Float rendre, Date dateheures, @NotBlank Boolean closed, @NotBlank String comment, Sale sale) {
        this.id = id;
        this.type = type;
        this.montant = montant;
        this.rendre = rendre;
        this.dateheures = dateheures;
        this.closed = closed;
        this.comment = comment;
        this.sale = sale;
    }

    public Payment(@NotBlank @Size(max = 255) String type, @NotBlank @Size(max = 255) Float montant, @NotBlank @Size(max = 255) Float rendre, @NotBlank Boolean closed, @NotBlank String comment, Sale sale) {
        this.type = type;
        this.montant = montant;
        this.rendre = rendre;
        this.closed = closed;
        this.comment = comment;
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Float getRendre() {
        return rendre;
    }

    public void setRendre(Float rendre) {
        this.rendre = rendre;
    }

    public Date getDateheures() {
        return dateheures;
    }

    public void setDateheures(Date dateheures) {
        this.dateheures = dateheures;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", montant=" + montant +
                ", rendre=" + rendre +
                ", dateheures=" + dateheures +
                ", closed=" + closed +
                ", comment='" + comment + '\'' +
                ", sale=" + sale +
                '}';
    }
}