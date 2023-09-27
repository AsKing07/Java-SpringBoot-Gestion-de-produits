package com.asking.api_produit.modele;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "utilisateur")
public class User {

    // Constructeur par défaut
    public User() {
        super();
    }

    // Constructeur avec paramètres
    public User(Long id, String email, String password, String prenom, String nom) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
    }

    // Identifiant de l'utilisateur
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Adresse e-mail de l'utilisateur
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    // Mot de passe de l'utilisateur
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    // Prénom de l'utilisateur
    @Column(name = "prenom", nullable = false, length = 20)
    private String prenom;

    // Nom de l'utilisateur
    @Column(name = "nom", nullable = false, length = 20)
    private String nom;
}