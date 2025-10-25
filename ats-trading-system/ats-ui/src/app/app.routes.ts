import { Routes } from '@angular/router';

// app.routes.ts
export const routes: Routes = [
  { path: 'login', loadComponent: () => import('./auth/login.component').then(m=>m.LoginComponent)},
  { path: 'trades', loadChildren: () => import('./trades/routes').then(m=>m.TRADES_ROUTES)},
  { path: '', pathMatch: 'full', redirectTo: 'login' }
];

