package com.asking.api_produit.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import com.asking.api_produit.modele.Produit;
import com.asking.api_produit.repository.ProduitRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ProduitServiceImpl implements ProduitService
{
    private final ProduitRepository produitRepository;

    @Override
    public Produit creer(Produit produit) 
    {
        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> lire() 
    {
        return produitRepository.findAll();
    }

    @Override
    public Produit modifier(Integer id, Produit produit) 
    {
        return produitRepository.findById(id)
                        .map(p ->{
                            p.setPrix(produit.getPrix());
                            p.setNom(produit.getNom());
                            p.setDevise(produit.getDevise());
                            p.setFournisseur(produit.getFournisseur());
                            p.setTaxe(produit.getTaxe());
                            p.setDateExpiration(produit.getDateExpiration());
                            p.setImage(produit.getImage());
                       return  produitRepository.save(p);
                        }).orElseThrow(() -> new RuntimeException("Produit non trouvé..."));
    }

    @Override
    public String supprimer(Integer id) 
    {
      produitRepository.deleteById(id);
      return "Produit supprimé";
    }
    

    public Produit trouverParId(Integer id)
    {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
public Page<Produit> lireProduitsPagine(int numeroPage, int taillePage, String triPar) 
{
    
    if (taillePage <= 0) 
    {
        throw new IllegalArgumentException("La taille de la page doit être supérieure à zéro");
    }
     Sort.Direction directionTri = Sort.Direction.ASC; // Par défaut, tri ascendant
     String proprieteTri = triPar; // Par défaut, tri par l'id
     if (triPar != null) 
     {
        if (triPar.startsWith("-")) 
        {
            directionTri = Sort.Direction.DESC;
            proprieteTri = triPar.substring(1); // Retirer le préfixe "-"
        } 
        else 
        {
            proprieteTri = triPar;
        }
    }
    else
    {
        proprieteTri = "id";
    }
     PageRequest pageRequest = PageRequest.of(numeroPage, taillePage, directionTri, proprieteTri);
     try {
        return produitRepository.findAll(pageRequest);
    } catch (Exception e) {
        throw new RuntimeException("Erreur lors de la récupération des produits paginés", e);
    }
}


    public Page<Produit> recherche(String recherche, String critere) {
    org.springframework.data.domain.Pageable pageable = PageRequest.of(0,Integer.MAX_VALUE); // ou autre pagination
    switch (critere) {
        case "nom":
            return produitRepository.findByNomContaining(recherche, pageable);
        case "fournisseur":
            return produitRepository.findByFournisseurContaining(recherche, pageable);
        default:
            throw new IllegalArgumentException("Critère de recherche non valide: " + critere);
    }
}
}
  


/*
 * Le service ProduitServiceImpl est une implémentation de l'interface ProduitService. 
 * Il utilise le repository ProduitRepository pour effectuer les opérations CRUD sur les produits.
 La méthode creer() permet de créer un nouveau produit en utilisant la méthode save() du repository.
 La méthode lire() permet de récupérer tous les produits en utilisant la méthode findAll() du repository.
 La méthode modifier() permet de mettre à jour un produit existant en utilisant la méthode findById() du repository pour trouver le produit par son identifiant, puis en modifiant 
 ses attributs avec les valeurs du produit passé en paramètre. Enfin, la méthode save() du repository est utilisée pour enregistrer les modifications.
 La méthode supprimer() permet de supprimer un produit en utilisant la méthode deleteById() du repository.
 La méthode trouverParId() permet de trouver un produit par son identifiant en utilisant la méthode findById() du repository.
 Ces méthodes sont utilisées pour effectuer les opérations CRUD sur les produits dans le contrôleur de l'API.
 */

