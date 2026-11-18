import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth';

const excludeArray: string[] = ['/login', '/register', '/verifyEmail'];

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const jwt = authService.getToken();
  const excluded = excludeArray.some(path => req.url.includes(path));

  if (excluded || !jwt)
    return next(req);

  return next(req.clone({
    setHeaders: { Authorization: 'Bearer ' + jwt }
  }));
};