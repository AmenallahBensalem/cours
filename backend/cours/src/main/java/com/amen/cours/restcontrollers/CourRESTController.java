package com.amen.cours.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	//@RequestMapping(path = "all", method = RequestMethod.GET)
	@GetMapping("/all")
	public List<Cour> getAllCours() {
        return courService.getAllCours();
    }
	
	//@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
	@GetMapping("/getbyid/{id}")
    public Cour getCourById(@PathVariable("id") Long id) {
        return courService.getCour(id);
    }
	
	//@RequestMapping(path = "/addcour", method = RequestMethod.POST)
	@PostMapping("/addcour")
	@PreAuthorize("hasAuthority('ADMIN')")
    public Cour createCour(@RequestBody Cour cour) {
        return courService.saveCour(cour);
    }
	
	//@RequestMapping(path = "/updatecour", method = RequestMethod.PUT)
	@PutMapping("/updatecour")
    public Cour updateCour(@RequestBody Cour cour) {
        return courService.updateCour(cour);
    }
	
	//@RequestMapping(value = "/delcour/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("/delcour/{id}")
    public void deleteCour(@PathVariable("id") Long id) {
        courService.deleteCourById(id);
    }
	
	//@RequestMapping(value = "/courprof/{codeProf}", method = RequestMethod.GET)
	@GetMapping("/courprof/{codeProf}")
    public List<Cour> getCoursByProfId(@PathVariable("codeProf") Long codeProf) {
        return courService.findByProfesseurCodeProf(codeProf);
    }
	
	//@RequestMapping(value = "/coursByName/{nom}", method = RequestMethod.GET)
	@GetMapping("/coursByName/{nom}")
	public List<Cour> findByIntituleCourContains(@PathVariable("nom") String nom) {
	    return courService.findByIntituleCourContainsIgnoreCase(nom);
	}
	
}
