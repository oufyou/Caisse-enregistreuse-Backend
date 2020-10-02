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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * The type User.
 *
 * @author King
 */

@Entity
@Table(	name = "subcategories")
@EntityListeners(AuditingEntityListener.class)
public class SubCategory {

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
    private List<Product> products;

    public SubCategory() {
    }

    public SubCategory(Long id, @NotBlank @Size(max = 155) String nom, @NotBlank @Size(max = 255) String description, @NotBlank @Size(max = 255) String imagelink, List<Product> products) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.imagelink = imagelink;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", imagelink='" + imagelink + '\'' +
                ", products=" + products +
                '}';
    }
}