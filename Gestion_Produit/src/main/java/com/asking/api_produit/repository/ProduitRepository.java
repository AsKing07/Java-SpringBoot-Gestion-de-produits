package com.asking.api_produit.repository;

import com.asking.api_produit.modele.Produit;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Integer>
{

    Page<Produit> findByNomContaining(String recherche, org.springframework.data.domain.Pageable pageable); 
 
    Page<Produit> findByPrixContaining(String recherche, org.springframework.data.domain.Pageable pageable); 
 
    Page<Produit> findByFournisseurContaining(String recherche, org.springframework.data.domain.Pageable pageable); 
 
    Page<Produit> findByDateExpirationContaining(String recherche, org.springframework.data.domain.Pageable pageable); 
}

/*
 * Ce fichier est le repository de l'entité Produit.
 * Il étend l'interface JpaRepository de Spring Data JPA, ce qui permet d'effectuer
 * des opérations de base sur l'entité Produit telles que la création, la lecture, 
 * la mise à jour et la suppression.
 * Le type d'ID de l'entité Produit est Integer.
 */