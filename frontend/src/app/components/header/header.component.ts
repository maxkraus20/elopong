import {Component, HostListener} from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatMenu, MatMenuItem, MatMenuTrigger } from "@angular/material/menu";
import { MatIcon} from "@angular/material/icon";
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatListItem, MatNavList} from "@angular/material/list";
import {NgIf} from "@angular/common";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    RouterLink,
    MatMenu,
    MatMenuTrigger,
    MatMenuItem,
    MatIcon,
    MatSidenavModule,
    MatListItem,
    MatNavList,
    RouterLinkActive,
    NgIf
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  isMobile: boolean;
  isLoggedIn: boolean;

  constructor(
    public authService: AuthService
  ) {
    this.checkScreenSize();

  }

  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.checkScreenSize();
  }

  checkScreenSize() {
    this.isMobile = window.innerWidth < 500;
  }
}
