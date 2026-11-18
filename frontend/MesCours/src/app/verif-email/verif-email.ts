import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { AuthService } from '../services/auth';

@Component({
  selector: 'app-verif-email',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './verif-email.html',
})
export class VerifEmailComponent implements OnInit {
  code: string = '';
  user: User = new User();
  err = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.user = this.authService.getRegisteredUser();
  }

  onValidateEmail() {
    this.err = '';
    this.authService.validateEmail(this.code).subscribe({
      next: () => {
        alert('Email confirmé');
        // connexion automatique avec les identifiants saisis à l'inscription
        this.authService.login(this.user).subscribe({
          next: (data) => {
            let jwToken = data.headers.get('Authorization')!;
            this.authService.saveToken(jwToken);
            this.router.navigate(['/']);
          },
          error: () => this.router.navigate(['/login'])
        });
      },
      error: (err: any) => {
        if (err.error?.errorCode == 'INVALID_TOKEN')
          this.err = 'Code invalide !';
        else if (err.error?.errorCode == 'EXPIRED_TOKEN')
          this.err = 'Code expiré !';
        else
          this.err = err.error?.message ?? 'Erreur de validation';
      }
    });
  }
}