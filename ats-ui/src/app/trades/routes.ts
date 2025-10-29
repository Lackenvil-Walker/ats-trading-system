// trades/routes.ts
import { Routes } from '@angular/router';
export const TRADES_ROUTES: Routes = [
  { path: '', loadComponent: () => import('./swaps/swaps-list.component').then(m=>m.SwapsListComponent) }
];
