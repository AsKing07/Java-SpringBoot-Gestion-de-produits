package com.asking.api_produit.controller;
import com.asking.api_produit.modele.Produit;
import com.asking.api_produit.service.ProduitService;

import lombok.AllArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;




@Controller
@RequestMapping("/ProduitApp")
@AllArgsConstructor

public class ProduitController 
{
     private final ProduitService produitService;
     final String acceuil="redirect:/ProduitApp/";
     @RequestMapping("/")
    public String liste(Model model) 
    {
        return listePaginee(model, 0, 2,"id");
    }
     @RequestMapping("/creation/")
    public String pageCreate() 
    {
        return "create";
    }
     @RequestMapping("/maj/{id}")
    public String update(Model model, @PathVariable Integer id) 
    {
        Produit produit = produitService.trouverParId(id);
        if (produit == null) 
        {
            return acceuil;
        }
        model.addAttribute("produit", produit);
        return "update";
    }
     
     
    @RequestMapping(value = {"/liste/{page}/{pageSize}", "/liste/{page}/{pageSize}/{sortBy}"}, method = RequestMethod.GET)
    public String listePaginee(Model model, @PathVariable int page, @PathVariable int pageSize, @PathVariable String sortBy) {
        
         if (page < 0 || pageSize < 1) 
        {
            model.addAttribute("errorMessage", "Page ou taille de page non valide");
            return "error";
        }
        
        if (page < 0) 
        {
            model.addAttribute("errorMessage", "Cette page n'existe pas!!!");
            return "error";
        }
        Page<Produit> produitsPage = produitService.lireProduitsPagine(page, pageSize, sortBy);
        if (page > produitsPage.getTotalPages()) 
        {
            model.addAttribute("errorMessage", "Cette page n'existe pas!!!");
            return "error";
        }
        model.addAttribute("produits", produitsPage);
        model.addAttribute("sortBy", sortBy);
        return "list";
    }
     @PostMapping("create")
    public String creer(@ModelAttribute Produit produit) 
    {
        produitService.creer(produit);
        return acceuil;
    }
     @GetMapping(value = "/read")
    public List<Produit> read() 
    {
        return produitService.lire();
    }
     @PostMapping("/update/{id}")
    public String update(@ModelAttribute Produit produit) 
    {
        if (produitService.trouverParId(produit.getId()) != null) 
        {
            produitService.modifier(produit.getId(), produit);
        }
        return acceuil;
    }
     @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) 
    {
        if (produitService.trouverParId(id) != null) 
        {
            produitService.supprimer(id);
            return true;
        }
        return false;
    }
     @RequestMapping("/delete/{id}")
    public String suppression(Model model,@PathVariable Integer id) 
    {
        if(delete(id)) 
        {
            return acceuil;
        } 
        else 
        {
            model.addAttribute("errorMessage", "Suppression impossible");
            return "error";
        }
    }

    @PostMapping("/recherche")
    public String recherche(@RequestParam("recherche") String recherche, @RequestParam("critere") String critere, Model model) {
        Page<Produit> produits = produitService.recherche(recherche, critere);
        model.addAttribute("produits", produits);
        return "resultRecherche";


}
}
/*
 Le contrôleur ProduitController gère les requêtes liées aux produits.

 Il est annoté avec @Controller pour indiquer qu'il est un composant du contrôleur dans Spring MVC. 
 Il est également annoté avec @RequestMapping("/ProduitApp") pour spécifier le chemin de base pour toutes les requêtes liées aux produits.

 Le constructeur de la classe prend en paramètre un objet ProduitService, qui est utilisé pour effectuer les opérations CRUD sur les produits.

 Le contrôleur gère les pages en retournant les noms des fichiers de modèle correspondants. 
 Par exemple, la méthode liste() retourne "list" pour afficher la page list.html.
 Cette méthode récupère la liste des produits à partir de ProduitService et l'ajoute au modèle avant de la renvoyer à la vue.
 Les autres méthodes gèrent les actions liées aux produits. 

 Par exemple, la méthode creer() est annotée avec @PostMapping("create") pour spécifier qu'elle gère les requêtes POST vers "/ProduitApp/create". 
 Elle prend un objet Produit en tant que paramètre, qui est lié aux données envoyées dans la requête. 
 Cette méthode utilise ProduitService pour créer le produit et redirige ensuite vers la page list.html.

 La méthode read() est annotée avec @GetMapping("/read") pour spécifier qu'elle gère les requêtes GET vers "/ProduitApp/read". 
 Elle utilise ProduitService pour récupérer la liste des produits et la renvoie en tant que réponse.
                                      
 Les autres méthodes gèrent les mises à jour et les suppressions de produits. 
 Par exemple, la méthode update() est annotée avec @PostMapping("/update/{id}") pour spécifier qu'elle gère les requêtes POST vers "/ProduitApp/update/{id}". 
 Elle prend un objet Produit en tant que paramètre, qui est lié aux données envoyées dans la requête. 
 Cette méthode utilise ProduitService pour mettre à jour le produit avec l'ID spécifié et redirige ensuite vers la page list.html.

 La méthode delete() est annotée avec @DeleteMapping() pour spécifier qu'elle gère les requêtes DELETE vers "/ProduitApp/{id}". 
 Elle utilise ProduitService pour supprimer le produit avec l'ID spécifié et renvoie true si la suppression a réussi.

 La méthode suppression() est annotée avec @RequestMapping("/delete/{id}") pour spécifier qu'elle gère les requêtes GET et POST vers "/ProduitApp/delete/{id}". 
 Elle utilise la méthode delete() pour supprimer le produit avec l'ID spécifié et redirige ensuite vers la page list.html.
 
 En résumé, le contrôleur ProduitController gère les requêtes liées aux produits, y compris la création, la lecture, la mise à jour et la suppression des produits. Il utilise ProduitService pour effectuer les opérations CRUD sur les produits et renvoie les réponses appropriées en fonction des actions effectuées.
 */
