/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * The type User.
 *
 * @author King
 */

@Entity
@Table(	name = "SessionPOS")
@EntityListeners(AuditingEntityListener.class)
public class SessionPOS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private User UserCaissier;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Opendateheures", nullable = true)
    private Date Opendateheures;

    @NotBlank
    @Size(max = 255)
    @Column(name = "OpenMontant", nullable = true)
    private Float OpenMontant;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Closedateheures", nullable = true)
    private Date Closedateheures;

    @NotBlank
    @Size(max = 255)
    @Column(name = "CloseMontant", nullable = true)
    private Float CloseMontant;

    @NotBlank
    @Size(max = 255)
    private Boolean State;

    @NotBlank
    @Column(nullable = true)
    private String Comment;

    public SessionPOS() {
    }

    public SessionPOS(User userCaissier, @NotBlank @Size(max = 255) Float openMontant, @NotBlank @Size(max = 255) Float closeMontant, @NotBlank @Size(max = 255) Boolean state, @NotBlank String comment) {
        UserCaissier = userCaissier;
        OpenMontant = openMontant;
        CloseMontant = closeMontant;
        State = state;
        Comment = comment;
    }

    public SessionPOS(Long id, User userCaissier, Date opendateheures, @NotBlank @Size(max = 255) Float openMontant, Date closedateheures, @NotBlank @Size(max = 255) Float closeMontant, @NotBlank @Size(max = 255) Boolean state, @NotBlank String comment) {
        this.id = id;
        UserCaissier = userCaissier;
        Opendateheures = opendateheures;
        OpenMontant = openMontant;
        Closedateheures = closedateheures;
        CloseMontant = closeMontant;
        State = state;
        Comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserCaissier() {
        return UserCaissier;
    }

    public void setUserCaissier(User userCaissier) {
        UserCaissier = userCaissier;
    }

    public Float getOpenMontant() {
        return OpenMontant;
    }

    public void setOpenMontant(Float openMontant) {
        OpenMontant = openMontant;
    }

    public Float getCloseMontant() {
        return CloseMontant;
    }

    public void setCloseMontant(Float closeMontant) {
        CloseMontant = closeMontant;
    }

    public Boolean getState() {
        return State;
    }

    public void setState(Boolean state) {
        State = state;
    }

    public Date getOpendateheures() {
        return Opendateheures;
    }

    public void setOpendateheures(Date opendateheures) {
        Opendateheures = opendateheures;
    }

    public Date getClosedateheures() {
        return Closedateheures;
    }

    public void setClosedateheures(Date closedateheures) {
        Closedateheures = closedateheures;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    @Override
    public String toString() {
        return "SessionPOS{" +
                "id=" + id +
                ", UserCaissier=" + UserCaissier +
                ", Opendateheures=" + Opendateheures +
                ", OpenMontant=" + OpenMontant +
                ", Closedateheures=" + Closedateheures +
                ", CloseMontant=" + CloseMontant +
                ", State=" + State +
                ", comment='" + Comment + '\'' +
                '}';
    }
}