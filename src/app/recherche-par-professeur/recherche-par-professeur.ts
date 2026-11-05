import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cours } from '../model/cours.model';
import { Professeur } from '../model/professeur.model';
import { CoursService } from '../services/cours';

@Component({
  selector: 'app-recherche-par-professeur',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './recherche-par-professeur.html',
})
export class RechercheParProfesseurComponent implements OnInit {
  cours: Cours[] = [];
  codeProfesseur!: number;
  professeurs: Professeur[] = [];

  constructor(
    private coursService: CoursService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.coursService.listeProfesseurs().subscribe(profs => {
      console.log(profs);
      this.professeurs = profs._embedded.professeurs;
      this.cdr.markForCheck();
    });
  }

  onChange() {
    this.coursService.coursParProfesseur(this.codeProfesseur).subscribe(cs => {
      console.log(cs);
      this.cours = cs;
      this.cdr.markForCheck();
    });
  }
}