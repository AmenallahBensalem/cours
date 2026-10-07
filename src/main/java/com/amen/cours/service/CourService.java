package com.amen.cours.service;

import java.util.List;

import com.amen.cours.entities.Cour;
import com.amen.cours.entities.Professeur;

public interface CourService {
	Cour saveCour(Cour c);
    Cour updateCour(Cour c);
    void deleteCour(Cour c);
    void deleteCourById(Long id);
    Cour getCour(Long id);
    List<Cour> getAllCours();
    
    List<Cour> findByIntituleCour(String intitule);
    List<Cour> findByIntituleCourContains(String intitule);
    List<Cour> findByIntituleDuree(String intitule, Double duree);
    List<Cour> findByProfesseur(Professeur professeur);
    List<Cour> findByProfesseurCodeProf(Long id);
    List<Cour> findByOrderByIntituleCourAsc();
    List<Cour> trierCoursIntituleDuree();
}
