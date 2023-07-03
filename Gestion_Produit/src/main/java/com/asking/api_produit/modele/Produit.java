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
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     private String nom;
    private double prix;
     @Column(length = 3)
    private String devise;
     private Integer taxe;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateExpiration;
     private String fournisseur;
     private String image; // Nouvelle propriété pour l'image du produit
     @Transient
     private MultipartFile imageFile;
     

 }