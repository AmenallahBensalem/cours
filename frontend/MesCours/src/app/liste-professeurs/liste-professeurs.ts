import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Professeur } from '../model/professeur.model';
import { CoursService } from '../services/cours';
import { UpdateProfesseurComponent } from '../update-professeur/update-professeur';

@Component({
  selector: 'app-liste-professeurs',
  standalone: true,
  imports: [CommonModule, UpdateProfesseurComponent],
  templateUrl: './liste-professeurs.html',
})
export class ListeProfesseursComponent implements OnInit {
  professeurs: Professeur[] = [];
  updatedProf: Professeur = new Professeur(); // formulaire vide
  ajout: boolean = true;

  constructor(
    private coursService: CoursService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.chargerProfesseurs();
  }

  chargerProfesseurs() {
    this.coursService.listeProfesseurs().subscribe(profs => {
      console.log(profs);
      this.professeurs = profs._embedded.professeurs;
      this.cdr.markForCheck();
    });
  }

  professeurUpdated(prof: Professeur) {
    console.log("Prof updated event", prof);
    if (this.ajout) {
      this.coursService.ajouterProfesseur(prof).subscribe(() => this.terminer());
    } else {
      this.coursService.updateProfesseur(prof).subscribe(() => this.terminer());
    }
  }

  updateProf(prof: Professeur) {
    this.updatedProf = { ...prof }; // copie : on ne modifie pas la ligne du tableau avant validation
    this.ajout = false;
  }

  // retour au mode « ajout » avec un formulaire vide, puis rechargement de la liste
  private terminer() {
    this.updatedProf = new Professeur();
    this.ajout = true;
    this.chargerProfesseurs();
  }
}