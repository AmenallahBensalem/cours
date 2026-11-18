import { Routes } from '@angular/router';
import { CoursComponent } from './cours/cours';
import { AddCoursComponent } from './add-cour/add-cour';
import { UpdateCoursComponent } from './update-cour/update-cour';
import { RechercheParProfesseurComponent } from './recherche-par-professeur/recherche-par-professeur';
import { RechercheParNomComponent } from './recherche-par-nom/recherche-par-nom';
import { ListeProfesseursComponent } from './liste-professeurs/liste-professeurs';
import { LoginComponent } from './login/login';
import { ForbiddenComponent } from './forbidden/forbidden';
import { coursGuard } from './cours-guard';
import { RegisterComponent } from './register/register';
import { VerifEmailComponent } from './verif-email/verif-email';

export const routes: Routes = [
  { path: '', redirectTo: 'cours', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'app-forbidden', component: ForbiddenComponent },
  { path: 'cours', component: CoursComponent },
  { path: 'add-cour', component: AddCoursComponent, canActivate: [coursGuard] },
  { path: 'updateCours/:id', component: UpdateCoursComponent, canActivate: [coursGuard] },
  { path: 'rechercheParProfesseur', component: RechercheParProfesseurComponent },
  { path: 'rechercheParNom', component: RechercheParNomComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'verifEmail', component: VerifEmailComponent },
  { path: 'listeProfesseurs', component: ListeProfesseursComponent }
];