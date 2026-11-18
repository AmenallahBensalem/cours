import { Professeur } from './professeur.model';

export class Cours {
  codeCour?: number;
  intituleCour?: string;
  dureeTotale?: number;
  dateMiseAJour?: Date;
  professeur?: Professeur;
}