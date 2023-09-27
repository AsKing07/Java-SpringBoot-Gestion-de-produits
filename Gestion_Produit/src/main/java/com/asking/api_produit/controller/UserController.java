package com.asking.api_produit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.asking.api_produit.modele.User;
import com.asking.api_produit.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserController 
{
    // Injection de dépendance pour UserRepository
    @Autowired
    private UserRepository userRepository;

    // Affiche le formulaire d'inscription
    @GetMapping("/register")
    public String showRegistrationForm(Model model) 
    {
        model.addAttribute("user", new User());
        return "createUser"; // Affiche la vue createUser.html
    }

    // Traitement de l'inscription
    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute User user) 
    {
        // Utilisation de BCrypt pour encoder le mot de passe
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Sauvegarde de l'utilisateur dans la base de données
        userRepository.save(user);

        return "register_success"; // Affiche la vue register_success.html
    }

    // Affiche la liste des utilisateurs
    @GetMapping("/users")
    public String listUsers(Model model) 
    {
        // Récupère la liste des utilisateurs depuis la base de données
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users"; // Affiche la vue users.html
    }

    // Supprime un utilisateur
    @GetMapping("/deleteUser/{id}")
    public String deleteProduct(@PathVariable(value="id") long id) 
    {
        // Supprime l'utilisateur avec l'ID spécifié
        this.userRepository.deleteById(id);
        return "redirect:/users"; // Redirige vers la liste des utilisateurs
    }
}
