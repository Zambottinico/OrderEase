import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  ActivatedRoute,
  Router,
  RouterModule,
  RouterOutlet,
} from '@angular/router';
import { Mesa } from '../models/mesa';
import { MesasService } from '../mesas.service';
import { PedidoComponent } from '../pedido/pedido.component';
import { Restaurant } from '../models/Restaurant';
import { Salon } from '../models/Salon';
import { DiningTableState } from '../models/DiningTableState.enum';
import { SidebarComponent } from '../sidebar/sidebar.component';

@Component({
  selector: 'app-salones',
  standalone: true,
  templateUrl: './salones.component.html',
  styleUrl: './salones.component.css',
  imports: [CommonModule, RouterOutlet, RouterModule, SidebarComponent],
})
export class SalonesComponent {
  public DiningTableState = DiningTableState;
  salon: Salon;
  idLastTableTouched: number;

  seleccionarSalon(_t10: Salon) {
    this.salon = this.mesasService.getMesas(_t10.id);
    this.id = _t10.id;
    console.log(this.salon);
  }
  restaurant: Restaurant;
  SeleccionarMesa(y: number, j: number) {
    if (this.salon.tableroDeMesas[y][j].state == DiningTableState.OPEN)
      this.idLastTableTouched = this.salon.tableroDeMesas[y][j].id;
  }
  //mesas: Mesa[][] = new Array(10).fill([]).map(() => new Array(10));
  id: number = 0; //Salon seleccionado
  constructor(
    private mesasService: MesasService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.mesasService.GetSalones().subscribe((data) => {
      this.id = this.route.snapshot.params['id'];
      this.salon = this.mesasService.getMesas(this.id);
      this.restaurant = this.mesasService.getRestaurant();
    });
  }
}
