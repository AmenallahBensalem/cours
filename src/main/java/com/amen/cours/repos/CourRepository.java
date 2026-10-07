package com.amen.cours.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.amen.cours.entities.Cour;
import com.amen.cours.entities.Professeur;
@RepositoryRestResource(path = "rest")
public interface CourRepository extends JpaRepository<Cour, Long> {

    List<Cour> findByIntituleCour(String intitule);

    List<Cour> findByIntituleCourContains(String intitule);
    
    
    @Query("select c from Cour c where c.intituleCour like %?1 and c.dureeTotale > ?2")
    List<Cour> findByIntituleDuree(String intitule,Double duree);
    
    @Query("select c from Cour c where c.intituleCour like %:intitule and c.dureeTotale > :duree")
    List<Cour> findByIntituleDuree1( @Param("intitule") String intitule, @Param("duree") Double duree);
    
    
    @Query("select c from Cour c where c.professeur = ?1")
    List<Cour> findByProfesseur(Professeur professeur);
    
    List<Cour> findByProfesseurCodeProf(Long id);
    
    List<Cour> findByOrderByIntituleCourAsc();//desc
    
    @Query("select c from Cour c order by c.intituleCour ASC, c.dureeTotale ASC")
    List<Cour> trierCoursIntituleDuree();

}
