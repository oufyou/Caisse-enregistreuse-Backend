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
@Table(	name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String nom;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 155)
    private String codebarre;

    @NotBlank
    @Size(max = 155)
    private float pu;

    @NotBlank
    @Size(max = 155)
    private String etatexiste;

    @NotBlank
    @Size(max = 155)
    private String codecolor;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private SubCategory subCategory;

    public Product() {
    }

    public Product(Long id, @NotBlank @Size(max = 255) String nom, @NotBlank @Size(max = 255) String description, @NotBlank @Size(max = 155) String codebarre, @NotBlank @Size(max = 155) float pu, @NotBlank @Size(max = 155) String etatexiste, @NotBlank @Size(max = 155) String codecolor, SubCategory subCategory) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.codebarre = codebarre;
        this.pu = pu;
        this.etatexiste = etatexiste;
        this.codecolor = codecolor;
        this.subCategory = subCategory;
    }

    public Product(@NotBlank @Size(max = 255) String nom, @NotBlank @Size(max = 255) String description, @NotBlank @Size(max = 155) String codebarre, @NotBlank @Size(max = 155) float pu, @NotBlank @Size(max = 155) String etatexiste, @NotBlank @Size(max = 155) String codecolor, SubCategory subCategory) {
        this.nom = nom;
        this.description = description;
        this.codebarre = codebarre;
        this.pu = pu;
        this.etatexiste = etatexiste;
        this.codecolor = codecolor;
        this.subCategory = subCategory;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
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

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public float getPu() {
        return pu;
    }

    public void setPu(float pu) {
        this.pu = pu;
    }

    public String getEtatexiste() {
        return etatexiste;
    }

    public void setEtatexiste(String etatexiste) {
        this.etatexiste = etatexiste;
    }

    public String getCodecolor() {
        return codecolor;
    }

    public void setCodecolor(String codecolor) {
        this.codecolor = codecolor;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", codebarre='" + codebarre + '\'' +
                ", pu=" + pu +
                ", etatexiste='" + etatexiste + '\'' +
                ", codecolor='" + codecolor + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }
}