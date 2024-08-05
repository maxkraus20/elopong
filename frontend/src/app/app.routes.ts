import { Routes } from '@angular/router';
import {LoginComponent} from "./components/auth/login/login.component";
import {RegisterComponent} from "./components/auth/register/register.component";
import {LeaderboardComponent} from "./components/leaderboard/leaderboard.component";

export const routes: Routes = [
  {path: '', redirectTo: '/leaderboard', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'leaderboard', component: LeaderboardComponent}
];
