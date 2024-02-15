import { Component } from '@angular/core';
import { Mesa } from '../models/mesa';
import { MesasService } from '../mesas.service';
import { CommonModule } from '@angular/common';
import {
  ActivatedRoute,
  Router,
  RouterModule,
  RouterOutlet,
} from '@angular/router';
import { Restaurant } from '../models/Restaurant';
import { Salon } from '../models/Salon';
import { DiningTableState } from '../models/DiningTableState.enum';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-mesas',
  standalone: true,

  templateUrl: './mesas.component.html',
  styleUrl: './mesas.component.css',
  imports: [CommonModule, RouterOutlet, RouterModule, ReactiveFormsModule],
})
export class MesasComponent {
  form: FormGroup<any>;
  public DiningTableState = DiningTableState;
  restaurant: Restaurant;
  // mesas: Mesa[][] = new Array(10).fill([]).map(() => new Array(10));
  salon: Salon;
  id: number = 0; //Salon seleccionado
  onSubmit() {
    if (this.form.valid) {
      this.mesasService.editarSalon(
        this.salon.tableroDeMesas,
        this.id,
        this.form.value.name
      );
    } else {
      this.form.markAllAsTouched();
      console.log('error');
    }
  }

  seleccionarSalon(_t10: Salon) {
    this.salon = this.mesasService.getMesas(_t10.id);
    this.id = _t10.id;
    this.form = new FormGroup({
      name: new FormControl(this.salon.name, {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
    });
  }
  agregarSalon() {
    let idSalonNew = this.mesasService.agregarSalon();
    this.id = this.route.snapshot.params['id'];
    this.restaurant = this.mesasService.getRestaurant();
    this.salon = this.mesasService.getMesas(this.id);
    //this.routerTP.navigate(['/EditarSalon/' + idSalonNew]);
  }

  agregarMesa(i: number, j: number) {
    if (this.salon.tableroDeMesas[i][j].state !== DiningTableState.OPEN) {
      this.salon.tableroDeMesas[i][j].state = DiningTableState.OPEN;
    } else {
      this.salon.tableroDeMesas[i][j].state = DiningTableState.NOT_EXIST;
    }

    //this.mesasService.agregarMesa(i, j);
    //this.mesas[i][j].state = this.mesas[i][j].state ? false : true;
  }

  constructor(
    private mesasService: MesasService,
    private route: ActivatedRoute,
    private routerTP: Router
  ) {}

  ngOnInit() {
    this.mesasService.GetSalones().subscribe((data) => {
      this.id = this.route.snapshot.params['id'];
      this.salon = this.mesasService.getMesas(this.id);
      this.restaurant = this.mesasService.getRestaurant();
      this.form = new FormGroup({
        name: new FormControl(this.salon.name, {
          validators: [
            Validators.required,
            Validators.minLength(3),
            Validators.maxLength(30),
          ],
        }),
      });
    });
  }
}
