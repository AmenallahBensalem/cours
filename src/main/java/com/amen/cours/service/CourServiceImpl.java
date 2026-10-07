package com.amen.cours.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.amen.cours.entities.Cour;
import com.amen.cours.entities.Professeur;
import com.amen.cours.repos.CourRepository;

@Service
public class CourServiceImpl implements CourService{
	//@Autowired
	private final CourRepository courRepository;
	
	
	public CourServiceImpl(CourRepository courRepository) {
		super();
		this.courRepository = courRepository;
	}

	@Override
	public Cour saveCour(Cour c) {
		return courRepository.save(c);
	}

	@Override
	public Cour updateCour(Cour c) {
		return courRepository.save(c);
	}

	@Override
	public void deleteCour(Cour c) {
		courRepository.delete(c);
		
	}

	@Override
	public void deleteCourById(Long id) {
		courRepository.deleteById(id);
		
	}

	@Override
	public Cour getCour(Long id) {
		return courRepository.findById(id).get();
	}

	@Override
	public List<Cour> getAllCours() {
		return courRepository.findAll();
	}
	@Override
	public List<Cour> findByIntituleCour(String intitule) {
	    return courRepository.findByIntituleCour(intitule);
	}

	@Override
	public List<Cour> findByIntituleCourContains(String intitule) {
	    return courRepository.findByIntituleCourContains(intitule);
	}

	@Override
	public List<Cour> findByIntituleDuree(String intitule, Double duree) {
	    return courRepository.findByIntituleDuree(intitule, duree);
	}

	@Override
	public List<Cour> findByProfesseur(Professeur professeur) {
	    return courRepository.findByProfesseur(professeur);
	}

	@Override
	public List<Cour> findByProfesseurCodeProf(Long id) {
	    return courRepository.findByProfesseurCodeProf(id);
	}

	@Override
	public List<Cour> findByOrderByIntituleCourAsc() {
	    return courRepository.findByOrderByIntituleCourAsc();
	}

	@Override
	public List<Cour> trierCoursIntituleDuree() {
	    return courRepository.trierCoursIntituleDuree();
	}

}
