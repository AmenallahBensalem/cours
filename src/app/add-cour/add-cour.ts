import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Cours } from '../model/cours.model';
import { Professeur } from '../model/professeur.model';
import { CoursService } from '../services/cours';

@Component({
  selector: 'app-add-cour',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-cour.html',
})
export class AddCoursComponent implements OnInit {
  newCour = new Cours();
  message: string = '';
  erreur: string = '';

  professeurs: Professeur[] = [];
  newIdProf!: number;

  constructor(
    private coursService: CoursService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.coursService.listeProfesseurs().subscribe({
      next: profs => {
        console.log(profs);
        this.professeurs = profs._embedded.professeurs;
        if (this.professeurs.length > 0) {
          this.newIdProf = this.professeurs[0].codeProf;
        }
        this.cdr.markForCheck();
      },
    });
  }

  addCours() {
    this.message = '';
    this.erreur = '';

    const prof = this.professeurs.find(p => p.codeProf == this.newIdProf);
    if (!prof) {
      this.erreur = 'Aucun professeur sélectionné (la liste est vide ?).';
      return;
    }
    this.newCour.professeur = prof;
    console.log('Envoi :', this.newCour);

    this.coursService.ajouterCours(this.newCour).subscribe({
      next: c => {
        console.log('Reçu :', c);
        this.message = 'Cours ' + c.intituleCour + ' ajouté avec succès !';
        this.newCour = new Cours();
        this.cdr.markForCheck();
      },

    });
  }
}