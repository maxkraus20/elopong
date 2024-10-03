import { Routes } from '@angular/router';
import {LoginComponent} from "./components/auth/login/login.component";
import {RegisterComponent} from "./components/auth/register/register.component";
import {LeaderboardComponent} from "./components/leaderboard/leaderboard.component";
import {authGuard} from "./guards/auth.guard";
import {unAuthGuard} from "./guards/un-auth.guard";

export const routes: Routes = [
  {path: '', redirectTo: '/leaderboard', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, canActivate: [unAuthGuard]},
  {path: 'register', component: RegisterComponent, canActivate: [unAuthGuard]},
  {path: 'leaderboard', component: LeaderboardComponent}
];
