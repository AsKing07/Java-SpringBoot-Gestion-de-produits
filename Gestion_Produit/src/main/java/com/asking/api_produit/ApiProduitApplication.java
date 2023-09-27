package com.asking.api_produit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ApiProduitApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(ApiProduitApplication.class, args);
	}

	@Configuration
	public class StaticResourceConfiguration implements WebMvcConfigurer 
	{
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/Images/**")
					.addResourceLocations("classpath:/static/Images/");
		}

		
	}

}

/*
 * Ce code est la classe principale qui démarre l'application Spring Boot pour l'API Produit. 
 * La méthode  main  est le point d'entrée de l'application. 
 * Elle utilise la classe  SpringApplication  pour démarrer l'application Spring Boot en utilisant 
 * la classe  ApiProduitApplication  et les arguments fournis.L'annotation @SpringBootApplication  
 * indique à Spring Boot que cette classe est une classe de configuration principale pour l'application.
 */