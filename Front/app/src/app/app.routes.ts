import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MesasComponent } from './mesas/mesas.component';
import { SalonesComponent } from './salones/salones.component';
import { ProductosComponent } from './productos/productos.component';
import { PanelControlComponent } from './panel-control/panel-control.component';
import { ClientesComponent } from './clientes/clientes.component';
import { EmpleadosComponent } from './empleados/empleados.component';

export const routes: Routes = [
  { path: 'EditarSalon/:id', component: MesasComponent },
  { path: 'Salones/:id', component: SalonesComponent },

  {
    path: 'PanelDeControl',
    component: PanelControlComponent,
    children: [
      { path: 'Productos', component: ProductosComponent },
      { path: 'Clientes', component: ClientesComponent },
      { path: 'Empleados', component: EmpleadosComponent },
    ],
  },
];
