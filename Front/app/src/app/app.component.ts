import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MesasComponent } from './mesas/mesas.component';
import { SidebarComponent } from './sidebar/sidebar.component';

import { MesasService } from './mesas.service';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLink,
    MesasComponent,
    SidebarComponent,
  ],
})
export class AppComponent {
  showSidebar = false;

  title = 'app';
  constructor(private mesasService: MesasService) {
    this.mesasService.GetSalones();
  }
}
