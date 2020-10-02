/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The type User.
 *
 * @author King
 */

@Entity
@Table(	name = "categories")
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 155)
    private String nom;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 255)
    private String imagelink;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = {CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<SubCategory> subcategories;

    public Category() {
    }

    public Category(Long id, @NotBlank @Size(max = 155) String nom, @NotBlank @Size(max = 255) String description, @NotBlank @Size(max = 255) String imagelink, List<SubCategory> subcategories) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.imagelink = imagelink;
        this.subcategories = subcategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public List<SubCategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubCategory> subcategories) {
        this.subcategories = subcategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", imagelink='" + imagelink + '\'' +
                ", subcategories=" + subcategories +
                '}';
    }
}