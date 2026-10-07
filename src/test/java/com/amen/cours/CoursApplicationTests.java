package com.amen.cours;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amen.cours.entities.Cour;
import com.amen.cours.entities.Professeur;
import com.amen.cours.repos.CourRepository;

@SpringBootTest
class CoursApplicationTests {

    @Autowired
    private CourRepository courRepository;

    @Test
    public void testCreateCour() {
    	Cour crs = new Cour("Développement Web", 50.5, new Date());
        courRepository.save(crs);
    }
    
    @Test
    public void testFindCour() {
        Cour crs = courRepository.findById(1L).get();
        System.out.println(crs);
    }

    @Test
    public void testUpdateCour() {
        Cour crs = courRepository.findById(1L).get();
        crs.setDureeTotale(60.0);
        courRepository.save(crs);
        System.out.println(crs);
    }

    @Test
    public void testDeleteCour() {
        courRepository.deleteById(1L);
    }

    @Test
    public void testListerTousCours() {
        List<Cour> listeCours = courRepository.findAll();
        for (Cour c : listeCours) {
            System.out.println(c);
        }
    }
    
    
    @Test
    public void testFindByIntituleCour() {
        List<Cour> cours = courRepository.findByIntituleCour("Programmation Java");
        System.out.println(cours);
        for (Cour c : cours) {
            System.out.println(c);
        }
    }
    
    
    
    @Test
    public void testFindByIntituleCourCoontains() {
        List<Cour> cours = courRepository.findByIntituleCourContains("P");
        System.out.println(cours);
        for (Cour c : cours) {
            System.out.println(c);
        }
    }
    
    
    @Test
    public void testFindByIntituleDuree() {
        List<Cour> listeCours = courRepository.findByIntituleDuree1("Développement Web", 30.0);
        for (Cour c : listeCours) {
            System.out.println(c);
        }
    }
    
    
    
    @Test
    public void testFindByProfesseur() {
        Professeur prof = new Professeur();
        prof.setCodeProf(1L);
        List<Cour> listeCours = courRepository.findByProfesseur(prof);
        for (Cour c : listeCours) {
            System.out.println(c);
        }
    }
    
    
    @Test
    public void testFindByProfesseurCodeProf() {
        List<Cour> listeCours = courRepository.findByProfesseurCodeProf(1L);
        for (Cour c : listeCours) {
            System.out.println(c);
        }
    }
    
    
    @Test
    public void testFindByOrderByIntituleCourAsc() {
        List<Cour> listeCours = courRepository.findByOrderByIntituleCourAsc();
        for (Cour c : listeCours) {
            System.out.println(c);
        }
    }
    
    
    @Test
    public void testTrierCoursIntituleDuree() {
        List<Cour> listeCours = courRepository.trierCoursIntituleDuree();
        for (Cour c : listeCours) {
            System.out.println(c);
        }
    }
    
}