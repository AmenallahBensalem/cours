package com.amen.cours.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amen.cours.entities.Cour;
import com.amen.cours.service.CourService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin
@AllArgsConstructor
public class CourRESTController {
	//@Autowired
	private final CourService courService;
	
	//@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public List<Cour> getAllCours() {
        return courService.getAllCours();
    }
	
	@GetMapping("/{id}")
    public Cour getCourById(@PathVariable("id") Long id) {
        return courService.getCour(id);
    }
	
	@PostMapping()
    public Cour createCour(@RequestBody Cour cour) {
        return courService.saveCour(cour);
    }
	
	@PutMapping()
    public Cour updateCour(@RequestBody Cour cour) {
        return courService.updateCour(cour);
    }
	
	@DeleteMapping("/{id}")
    public void deleteCour(@PathVariable("id") Long id) {
        courService.deleteCourById(id);
    }
	
	@GetMapping("/courprof/{codeProf}")
    public List<Cour> getCoursByProfId(@PathVariable("codeProf") Long codeProf) {
        return courService.findByProfesseurCodeProf(codeProf);
    }
	
}
