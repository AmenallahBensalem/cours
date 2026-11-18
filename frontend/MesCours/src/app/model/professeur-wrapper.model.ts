import { Professeur } from './professeur.model';

export class ProfesseurWrapper {
  _embedded!: { professeurs: Professeur[] };
}