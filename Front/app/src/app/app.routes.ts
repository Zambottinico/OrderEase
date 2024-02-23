import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MesasComponent } from './mesas/mesas.component';
import { SalonesComponent } from './salones/salones.component';
import { ProductosComponent } from './productos/productos.component';
import { PanelControlComponent } from './panel-control/panel-control.component';

export const routes: Routes = [
  { path: 'EditarSalon/:id', component: MesasComponent },
  { path: 'Salones/:id', component: SalonesComponent },

  { path: 'Productos', component: ProductosComponent },
  { path: 'PanelDeControl', component: PanelControlComponent },
];
