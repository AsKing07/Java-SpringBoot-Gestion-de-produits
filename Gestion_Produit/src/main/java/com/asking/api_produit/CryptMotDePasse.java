package com.asking.api_produit;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CryptMotDePasse implements WebMvcConfigurer 
{
    // Cette classe est une configuration pour gérer les vues et les URLs de l'application.

    // La méthode addViewControllers permet de configurer les associations entre les URLs et les vues.
    public void addViewControllers(ViewControllerRegistry registry) {
        // Lorsque l'URL "/home" est accédée, la vue "home" sera affichée.
        registry.addViewController("/home").setViewName("home");

        // L'URL "/" est également associée à la vue "home", donc lorsque l'accès de base est effectué, la vue "home" est affichée.
        registry.addViewController("/").setViewName("home");

        // L'URL "/hello" est associée à la vue "hello".
        registry.addViewController("/hello").setViewName("hello");

        // L'URL "/login" est associée à la vue "Se connecter".
        registry.addViewController("/login").setViewName("Se connecter");

        // L'URL "/" est de nouveau associée à une autre vue "Bienvenue".
        registry.addViewController("/").setViewName("Bienvenue");

        // L'URL "/register" est associée à la vue "S'inscrire".
        registry.addViewController("/register").setViewName("S'inscrire");
    }
}
