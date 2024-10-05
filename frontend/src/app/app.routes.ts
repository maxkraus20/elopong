import { Routes } from '@angular/router';
import {LoginComponent} from "./components/auth/login/login.component";
import {RegisterComponent} from "./components/auth/register/register.component";
import {LeaderboardComponent} from "./components/leaderboard/leaderboard.component";
import {unAuthGuard} from "./guards/un-auth.guard";
import {AddGameComponent} from "./components/add-game/add-game.component";
import {authGuard} from "./guards/auth.guard";
import {ProfileComponent} from "./components/profile/profile.component";
import {HistoryComponent} from "./components/history/history.component";

export const routes: Routes = [
  {path: '', redirectTo: '/leaderboard', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, canActivate: [unAuthGuard]},
  {path: 'register', component: RegisterComponent, canActivate: [unAuthGuard]},
  {path: 'leaderboard', component: LeaderboardComponent},
  {path: 'add-game', component: AddGameComponent, canActivate: [authGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [authGuard]},
  {path: 'history', component: HistoryComponent, canActivate: [authGuard]}
];
