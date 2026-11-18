package com.amen.cours.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.amen.cours.entities.Professeur;

@RepositoryRestResource(path = "prof")
@CrossOrigin("http://localhost:4200") 
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
}