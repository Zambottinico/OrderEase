import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-panel-control',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  templateUrl: './panel-control.component.html',
  styleUrl: './panel-control.component.css',
})
export class PanelControlComponent {
  opcionSelected: string = 'Productos';
}
