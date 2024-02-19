import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pedido',
  standalone: true,
  imports: [],
  templateUrl: './pedido.component.html',
  styleUrl: './pedido.component.css',
})
export class PedidoComponent {
  @Input() id: number;
  @Input() idLounge: number;
}
