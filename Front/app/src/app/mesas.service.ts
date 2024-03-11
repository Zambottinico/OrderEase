import { Injectable } from '@angular/core';
import { Mesa } from './models/mesa';
import { Restaurant } from './models/Restaurant';
import { Salon } from './models/Salon';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EditarSalonDto } from './models/EditarSalonDto';
import { FormGroup } from '@angular/forms';
import { OcuppyTableDto } from './models/OcuppyTableDto';

@Injectable({
  providedIn: 'root',
})
export class MesasService {
  salones: Salon[];
  mesas: Mesa[][] = new Array(10).fill([]).map(() => new Array(10));
  restaurant: Restaurant;
  agregarSalon(): number {
    let id = this.http.post<Salon>('http://localhost:8086/lounge/Create', null);
    id.subscribe((data) => {
      this.GetSalones();
      return data.id;
    });
    return null;
  }
  getRestaurant(): Restaurant {
    this.GetSalones();
    return this.restaurant;
  }

  editarSalon(mesas: Mesa[][], id: number, name: string): Observable<Salon> {
    console.log(id);
    const dto = new EditarSalonDto(id, name, mesas);
    let rta = this.http.put<Salon>(
      'http://localhost:8086/lounge/Edit/' + id,
      dto
    );
    rta.subscribe((data) => {
      this.GetSalones();
    });
    return rta;
  }
  /* agregarMesa(i: number, j: number) {
    this.mesas[i][j].state = this.mesas[i][j].state ? false : true;
  } */

  getMesas(id: number) {
    for (let index = 0; index < this.restaurant.salones.length; index++) {
      const element = this.restaurant.salones[index];
      if (element.id == id) {
        return element;
      }
    }
    return null;
  }
  // construirSalon(id: number): Salon {
  //   let nuevoSalon = new Salon(id, 'salon ' + id);
  //   let mesasNuevas: Mesa[][] = new Array(10).fill([]).map(() => new Array(10));
  //   for (let i = 0; i < 10; i++) {
  //     mesasNuevas[i] = [];
  //     for (let j = 0; j < 10; j++) {
  //       const id = i * 10 + j + 1; // Calcula un ID único para cada mesa
  //       const mesa = new Mesa(id);
  //       mesasNuevas[i][j] = mesa;
  //     }
  //   }
  //   nuevoSalon.mesas = mesasNuevas;
  //   console.log(mesasNuevas);
  //   return nuevoSalon;
  // }

  constructor(private http: HttpClient) {
    this.restaurant = new Restaurant();
  }
  //Nuevos metodosssss
  GetSalones(): Observable<Salon[]> {
    let xd = this.http.get<Salon[]>('http://localhost:8086/GetLounges/');
    xd.subscribe((data) => {
      console.log(data);
      this.restaurant.salones = data;
    });
    return xd;
  }

  occupyTable(
    form: FormGroup,
    idLounge: number,
    idTable: number
  ): Observable<any> {
    let dto: OcuppyTableDto = new OcuppyTableDto();
    dto.idClient = parseInt(form.value.client);
    dto.people = form.value.people;
    dto.idLounge = idLounge;
    dto.id = idTable;
    console.log(dto);
    let rta = this.http.put('http://localhost:8086/diningTable/Edit/', dto);
    return rta;
  }
}
