package com.amen.cours.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Professeur {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long codeProf;
    private String nomProf;
    private String specialiteProf;
    private Date dateEmbauche;
	
    
    @OneToMany (mappedBy = "professeur")
    @JsonIgnore
    private List<Cour> listeCours;
       
}
