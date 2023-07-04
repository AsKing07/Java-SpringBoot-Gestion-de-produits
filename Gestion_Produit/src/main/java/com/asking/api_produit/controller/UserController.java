package com.asking.api_produit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.asking.api_produit.modele.User;
import com.asking.api_produit.repository.UserRepository;
import java.lang.String;

import lombok.AllArgsConstructor; 


@AllArgsConstructor
@Controller
public class UserController 
{

    

    @Autowired
	private UserRepository userRepository;


    @GetMapping("/register")
	public String showRegistrationForm(Model model) 
	{
         model.addAttribute("user", new User());
	    return "createUser";
	}




    @PostMapping("/process_register")
	public String processRegister(@ModelAttribute User user) 
	{
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    userRepository.save(user);
	    return "register_success";
	}


    @GetMapping("/users")
	public String listUsers(Model model) 
	{
	    List<User> listUsers = userRepository.findAll();
	    model.addAttribute("listUsers", listUsers);
	    return "users";
	}

    @GetMapping("/deleteUser/{id}")
	public String deleteProduct(@PathVariable(value="id") long id) 
	{
		this.userRepository.deleteById(id);
		return "redirect:/users";
	}
    
    


    
   
}