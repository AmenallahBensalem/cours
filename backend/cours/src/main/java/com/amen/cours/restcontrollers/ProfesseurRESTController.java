package com.amen.cours.restcontrollers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amen.cours.entities.Professeur;
import com.amen.cours.repos.ProfesseurRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/prof")
@CrossOrigin
@AllArgsConstructor
public class ProfesseurRESTController {

    private final ProfesseurRepository professeurRepository;

    @GetMapping
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    @GetMapping("/{id}")
    public Professeur getProfesseurById(@PathVariable("id") Long id) {
        return professeurRepository.findById(id).orElse(null);
    }
}