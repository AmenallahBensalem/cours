package com.amen.cours.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity 
public class Cour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeCour;
    private String intituleCour;
    private Double dureeTotale;
    private Date dateMiseAJour;
    
    @ManyToOne
    private Professeur professeur;
    public Cour() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	public Cour(String intituleCour, Double dureeTotale, Date dateMiseAJour) {
		super();
		this.intituleCour = intituleCour;
		this.dureeTotale = dureeTotale;
		this.dateMiseAJour = dateMiseAJour;
	}
	
	
	public Long getCodeCour() {
		return codeCour;
	}
	public void setCodeCour(Long codeCour) {
		this.codeCour = codeCour;
	}
	public String getIntituleCour() {
		return intituleCour;
	}
	public void setIntituleCour(String intituleCour) {
		this.intituleCour = intituleCour;
	}
	public Double getDureeTotale() {
		return dureeTotale;
	}
	public void setDureeTotale(Double dureeTotale) {
		this.dureeTotale = dureeTotale;
	}
	public Date getDateMiseAJour() {
		return dateMiseAJour;
	}
	public void setDateMiseAJour(Date dateMiseAJour) {
		this.dateMiseAJour = dateMiseAJour;
	}
	
	
	@Override
	public String toString() {
		return "Cour [codeCour=" + codeCour + ", intituleCour=" + intituleCour + ", dureeTotale=" + dureeTotale
				+ ", dateMiseAJour=" + dateMiseAJour + "]";
	}


	public Professeur getProfesseur() {
		return professeur;
	}


	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}
	
	
//    
}
