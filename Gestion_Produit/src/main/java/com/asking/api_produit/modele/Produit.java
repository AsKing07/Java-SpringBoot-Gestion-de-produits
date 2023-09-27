package com.asking.api_produit.modele;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "PRODUITS")
@Getter
@Setter
@NoArgsConstructor
public class Produit {

    // Identifiant généré automatiquement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nom du produit
    private String nom;

    // Prix du produit
    private double prix;

    // Devise du prix (ex: EUR, USD, etc.)
    @Column(length = 3)
    private String devise;

    // Taxe appliquée au produit (en pourcentage)
    private Integer taxe;

    // Date d'expiration du produit
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateExpiration;

    // Nom du fournisseur du produit
    private String fournisseur;

    // Nom de l'image du produit
    private String image;

    // Fichier image du produit (transient car non stocké en base de données)
    @Transient
    private MultipartFile imageFile;
}