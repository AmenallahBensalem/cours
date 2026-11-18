import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Cours } from '../model/cours.model';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CoursService } from '../services/cours';
import { AuthService } from '../services/auth';

@Component({
  selector: 'app-cours',
  imports: [CommonModule, RouterLink],
  templateUrl: './cours.html',
})
export class CoursComponent implements OnInit {
  cours: Cours[] = [];

  constructor(
    private coursService: CoursService,
    private cdr: ChangeDetectorRef,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.chargerCours();
  }

  chargerCours() {
    this.coursService.listeCours().subscribe(cs => {
      console.log(cs);
      this.cours = cs;
      this.cdr.markForCheck();
    });
  }

  supprimerCours(c: Cours) {
    let conf = confirm("Etes-vous sûr ?");
    if (conf)
      this.coursService.supprimerCours(c.codeCour!).subscribe(() => {
        console.log("cours supprimé");
        this.chargerCours();
      });
  }
}