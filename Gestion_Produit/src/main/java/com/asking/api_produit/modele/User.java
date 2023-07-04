package com.asking.api_produit.modele;
 import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
//@AllArgsConstructor
@Entity
@Table(name = "utilisateur")
public class User 
{
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String email, String password, String prenom, String nom) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
	}
   
@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true, length = 45)
    private String email;
     
    @Column(name = "password", nullable = false, length = 64)
    private String password;
     
    @Column(name = "prenom", nullable = false, length = 20)
    private String prenom;
     
    @Column(name = "nom", nullable = false, length = 20)
    private String nom;

   
}