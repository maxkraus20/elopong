import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatMenu, MatMenuItem, MatMenuTrigger } from "@angular/material/menu";
import { MatIcon} from "@angular/material/icon";
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatListItem, MatNavList} from "@angular/material/list";

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
    RouterLinkActive
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

}
