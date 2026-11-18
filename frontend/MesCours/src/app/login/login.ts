import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { AuthService } from '../services/auth';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
})
export class LoginComponent {
  user = new User();
  erreur: number  = 0;
  message: string = 'Login ou mot de passe erronés..';
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onLoggedin() {
  this.authService.login(this.user).subscribe({
    next: (data) => {
      let jwToken = data.headers.get('Authorization')!;
      this.authService.saveToken(jwToken);
      this.router.navigate(['/']);
    },
    error: (err) => {
      this.erreur = 1;
      if (err.error?.errorCause == 'disabled')
        this.message = 'Utilisateur désactivé, veuillez contacter votre administrateur';
      else
        this.message = 'Login ou mot de passe erronés..';
    }
  });
}
}