import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {

  constructor(
    public authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
  this.authService.loadToken();
  const publicPages = ['/login', '/register', '/verifEmail'];
  const onPublicPage = publicPages.some(p => window.location.pathname.startsWith(p));

  if (!onPublicPage &&
      (this.authService.getToken() == null || this.authService.isTokenExpired()))
    this.router.navigate(['/login']);
}

  onLogout() {
    this.authService.logout();
  }
}