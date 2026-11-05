import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cours } from '../model/cours.model';
import { Professeur } from '../model/professeur.model';
import { CoursService } from '../services/cours';

@Component({
  selector: 'app-update-cour',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './update-cour.html',
})
export class UpdateCoursComponent implements OnInit {
  currentCours = new Cours();
  professeurs: Professeur[] = [];
  updatedProfId!: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private coursService: CoursService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.coursService.listeProfesseurs().subscribe(profs => {
      console.log(profs);
      this.professeurs = profs._embedded.professeurs;
      this.cdr.markForCheck();
    });

    this.coursService
      .consulterCours(Number(this.activatedRoute.snapshot.params['id']))
      .subscribe(c => {
        this.currentCours = c;
        this.updatedProfId = c.professeur!.codeProf;
        this.cdr.markForCheck();
      });
  }

  updateCours() {
    this.currentCours.professeur =
      this.professeurs.find(p => p.codeProf == this.updatedProfId)!;
    this.coursService.updateCours(this.currentCours).subscribe(() => {
      this.router.navigate(['cours']);
    });
  }
}