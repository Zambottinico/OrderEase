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
  imports: [
    CommonModule,
    RouterOutlet,
    RouterModule,
    SidebarComponent,
    PedidoComponent,
  ],
})
export class SalonesComponent {
  onEmiter(data: any) {
    console.log('emitido');
    this.lastTableTouchesIsOpen = false;
    this.restaurant = this.mesasService.getRestaurant();
    this.salon = this.mesasService.getMesas(this.id);
    console.log(this.salon);
    for (let i = 0; i < this.salon.tableroDeMesas.length; i++) {
      for (let j = 0; j < this.salon.tableroDeMesas[i].length; j++) {
        if (this.salon.tableroDeMesas[i][j].id == data) {
          this.salon.tableroDeMesas[i][j].state = DiningTableState.OCCUPIED;
        }
      }
    }
  }
  public DiningTableState = DiningTableState;
  salon: Salon;
  idLastTableTouched: number;
  lastTableTouchesIsOpen: boolean;

  seleccionarSalon(_t10: Salon) {
    this.salon = this.mesasService.getMesas(_t10.id);
    this.id = _t10.id;
    console.log(this.salon);
  }
  restaurant: Restaurant;
  SeleccionarMesa(y: number, j: number) {
    this.idLastTableTouched = this.salon.tableroDeMesas[y][j].id;
    if (this.salon.tableroDeMesas[y][j].state == DiningTableState.OPEN) {
      this.lastTableTouchesIsOpen = true;
    } else if (
      this.salon.tableroDeMesas[y][j].state == DiningTableState.OCCUPIED
    ) {
      this.lastTableTouchesIsOpen = false;
    }
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
