import { Component, OnInit } from '@angular/core';
import {
  AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule,
  ValidationErrors, Validators
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../model/user.model';
import { AuthService } from '../services/auth';
import { ToastrService } from 'ngx-toastr';
// vérifie que les deux mots de passe sont identiques
function passwordsMatch(group: AbstractControl): ValidationErrors | null {
  return group.get('password')?.value === group.get('confirmPassword')?.value
    ? null
    : { mismatch: true };
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.html',
})
export class RegisterComponent implements OnInit {
  myForm!: FormGroup;
  err = '';
  loading = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.myForm = this.formBuilder.group(
      {
        username: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', [Validators.required]]
      },
      { validators: passwordsMatch }
    );
  }

  onRegister() {
    this.err = '';
    this.loading = true;

    const user = new User();
    user.username = this.myForm.value.username;
    user.email = this.myForm.value.email;
    user.password = this.myForm.value.password;

    this.authService.registerUser(user).subscribe({
      next: () => {
        this.loading = false;
        this.authService.setRegisteredUser(user);
        this.toastr.success('Veuillez confirmer votre email', 'Confirmation');
        this.router.navigate(['/verifEmail']);
      },
      error: (err: any) => {
        this.loading = false;
        this.err = err.error?.message ?? "Erreur lors de l'inscription";
      }
    });
  }
}