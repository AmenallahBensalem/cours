import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Professeur } from '../model/professeur.model';

@Component({
  selector: 'app-update-professeur',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './update-professeur.html',
})
export class UpdateProfesseurComponent implements OnInit {
  @Input()
  professeur!: Professeur;

  @Input()
  ajout!: boolean;

  @Output()
  professeurUpdated = new EventEmitter<Professeur>();

  ngOnInit(): void {
    console.log("ngOnInit du composant UpdateProfesseur ", this.professeur);
  }

  saveProfesseur() {
    this.professeurUpdated.emit(this.professeur);
  }
}