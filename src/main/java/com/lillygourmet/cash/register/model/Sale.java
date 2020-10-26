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
@Table(	name = "sales")
@EntityListeners(AuditingEntityListener.class)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.REMOVE})
    private User user;

    @OneToOne(cascade = {CascadeType.REMOVE})
    private User user1;

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

    public Sale() {
    }

    public Sale(Long id, User user, User user1, Date dateheures, List<SaleLine> saleLines, @NotBlank @Size(max = 255) Float total, @NotBlank Boolean finished, @NotBlank String comment) {
        this.id = id;
        this.user = user;
        this.user1 = user1;
        this.dateheures = dateheures;
        this.saleLines = saleLines;
        this.total = total;
        this.finished = finished;
        this.comment = comment;
    }

    public Sale(User user, User user1, List<SaleLine> saleLines, @NotBlank @Size(max = 255) Float total, @NotBlank Boolean finished, @NotBlank String comment) {
        this.user = user;
        this.user1 = user1;
        this.saleLines = saleLines;
        this.total = total;
        this.finished = finished;
        this.comment = comment;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", user=" + user +
                ", user1=" + user1 +
                ", dateheures=" + dateheures +
                ", saleLines=" + saleLines +
                ", total=" + total +
                ", finished=" + finished +
                ", comment='" + comment + '\'' +
                '}';
    }
}