import { Component } from '@angular/core';
import {MatButton} from "@angular/material/button";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-add-game',
  standalone: true,
  imports: [
    MatButton,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './add-game.component.html',
  styleUrl: './add-game.component.css'
})
export class AddGameComponent {

  isSingle: boolean | null = null;

  setGameMode(isSingle: boolean) {
    this.isSingle = isSingle;
  }

}
