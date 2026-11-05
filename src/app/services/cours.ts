import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cours } from '../model/cours.model';
import { ProfesseurWrapper } from '../model/professeur-wrapper.model';
import { environment } from '../../environments/environment';
import { Professeur } from '../model/professeur.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CoursService {
  apiURL: string = environment.apiURL;
  apiURLProf: string = environment.apiURLProf;

  constructor(private http: HttpClient) {}

  listeCours(): Observable<Cours[]> {
    return this.http.get<Cours[]>(this.apiURL);
  }

  ajouterCours(c: Cours): Observable<Cours> {
    return this.http.post<Cours>(this.apiURL, c, httpOptions);
  }

  supprimerCours(id: number) {
    return this.http.delete(`${this.apiURL}/${id}`, httpOptions);
  }

  consulterCours(id: number): Observable<Cours> {
    return this.http.get<Cours>(`${this.apiURL}/${id}`);
  }

  updateCours(c: Cours): Observable<Cours> {
    return this.http.put<Cours>(this.apiURL, c, httpOptions);
  }

  coursParProfesseur(codeProf: number): Observable<Cours[]> {
    return this.http.get<Cours[]>(`${this.apiURL}/courprof/${codeProf}`);
  }

  
  listeProfesseurs(): Observable<ProfesseurWrapper> {
    return this.http.get<ProfesseurWrapper>(this.apiURLProf);
  }
  rechercherParNom(nom: string): Observable<Cours[]>   {
    return this.http.get<Cours[]>(`${this.apiURL}/coursByName/${nom}`);
  }
  ajouterProfesseur(p: Professeur): Observable<Professeur> {
  // on n'envoie pas le code : il est généré par la base
  const body = {
    nomProf: p.nomProf,
    specialiteProf: p.specialiteProf,
    dateEmbauche: p.dateEmbauche
  };
  return this.http.post<Professeur>(this.apiURLProf, body, httpOptions);
}

updateProfesseur(p: Professeur): Observable<Professeur> {
  const body = {
    codeProf: p.codeProf,
    nomProf: p.nomProf,
    specialiteProf: p.specialiteProf,
    dateEmbauche: p.dateEmbauche
  };
  return this.http.put<Professeur>(`${this.apiURLProf}/${p.codeProf}`, body, httpOptions);
}
}