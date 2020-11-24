/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.pool.TypePool;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@Table(	name = "sales")
@EntityListeners(AuditingEntityListener.class)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.REMOVE})
    private User customer;

    @OneToOne(cascade = {CascadeType.REMOVE})
    private User caissier;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateheures", nullable = false)
    private Date dateheures;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private List<SaleLine> saleLines;

    @NotBlank
    @Size(max = 255)
    private Float total;

    @NotBlank
    private Boolean finished;

    @NotBlank
    @Column(nullable = true)
    private String comment;

    @Column(columnDefinition="float default '0.00'",nullable = true)
    private Float Supplement;

    public Sale() {
    }

    public Sale(Long id, User customer, User caissier, Date dateheures, List<SaleLine> saleLines, @NotBlank @Size(max = 255) Float total, @NotBlank Boolean finished, @NotBlank String comment) {
        this.id = id;
        this.customer = customer;
        this.caissier = caissier;
        this.dateheures = dateheures;
        this.saleLines = saleLines;
        this.total = total;
        this.finished = finished;
        this.comment = comment;
    }

    public Sale(User customer, User caissier, List<SaleLine> saleLines, @NotBlank @Size(max = 255) Float total, @NotBlank Boolean finished, @NotBlank String comment) {
        this.customer = customer;
        this.caissier = caissier;
        this.saleLines = saleLines;
        this.total = total;
        this.finished = finished;
        this.comment = comment;
    }

    public Sale(User customer, User caissier, List<SaleLine> saleLines, @NotBlank @Size(max = 255) Float total, @NotBlank Boolean finished, @NotBlank String comment,Float supplement) {
        this.customer = customer;
        this.caissier = caissier;
        this.saleLines = saleLines;
        this.total = total;
        this.finished = finished;
        this.comment = comment;
        this.Supplement=supplement;
    }


    public Float getSupplement() {
        return Supplement;
    }

    public void setSupplement(Float supplement) {
        Supplement = supplement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateheures() {
        return dateheures;
    }

    public void setDateheures(Date dateheures) {
        this.dateheures = dateheures;
    }

    public List<SaleLine> getSaleLines() {
        return saleLines;
    }

    public void setSaleLines(List<SaleLine> saleLines) {
        this.saleLines = saleLines;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getCaissier() {
        return caissier;
    }

    public void setCaissier(User caissier) {
        this.caissier = caissier;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", customer=" + customer +
                ", caissier=" + caissier +
                ", dateheures=" + dateheures +
                ", saleLines=" + saleLines +
                ", total=" + total +
                ", finished=" + finished +
                ", comment='" + comment + '\'' +
                '}';
    }
}