import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cours } from '../model/cours.model';
import { CoursService } from '../services/cours';
import { SearchFilterPipe } from '../search-filter-pipe';

@Component({
  selector: 'app-recherche-par-nom',
  standalone: true,
  imports: [FormsModule, CommonModule, SearchFilterPipe],
  templateUrl: './recherche-par-nom.html',
})
export class RechercheParNomComponent implements OnInit {
  searchTerm!: string;
  cours: Cours[] = [];

  constructor(
    private coursService: CoursService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.coursService.listeCours().subscribe(cs => {
      console.log(cs);
      this.cours = cs;
      this.cdr.markForCheck();
    });
  }
}