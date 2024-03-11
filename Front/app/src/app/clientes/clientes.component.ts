import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Client } from '../models/Client';
import { ClientsService } from '../clients.service';
import { CommonModule } from '@angular/common';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-clientes',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule, ReactiveFormsModule],

  templateUrl: './clientes.component.html',
  styleUrl: './clientes.component.css',
})
export class ClientesComponent {
  sortLastNameFlag: boolean = false;
  sortNameFlag: boolean = false;
  clientSelected: Client;
  form: FormGroup<any>;
  formEdit: FormGroup<any>;
  clients: Client[] = [];

  sortName() {
    this.sortNameFlag = !this.sortNameFlag;
    if (this.sortNameFlag) {
      this.clients.sort((a, b) => b.name.localeCompare(a.name));
    } else {
      this.clients.sort((a, b) => a.name.localeCompare(b.name));
    }
  }
  sortLastName() {
    this.sortLastNameFlag = !this.sortLastNameFlag;
    if (this.sortLastNameFlag) {
      this.clients.sort((a, b) => b.lastName.localeCompare(a.lastName));
    } else {
      this.clients.sort((a, b) => a.lastName.localeCompare(b.lastName));
    }
  }
  onSubmitEdit() {
    console.log(this.formEdit.value);
    this.clientService.putClient(this.formEdit.value).subscribe((data) => {
      console.log(this.clientSelected);
      this.clients = this.clients.filter((p) => p.id !== data.id);
      this.clients.push(data);
      this.formEdit.reset();
    });
  }

  onSelected(client: Client) {
    this.clientSelected = client;
    this.formEdit = new FormGroup({
      id: new FormControl(this.clientSelected.id),
      name: new FormControl(this.clientSelected.name, {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
      lastName: new FormControl(this.clientSelected.lastName, {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
    });
  }
  onDelete(client: Client) {
    this.clientService.deleteClient(client).subscribe((data) => {
      if (data) {
        this.clients = this.clients.filter((p) => p.id !== client.id);
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.clientService.postClient(this.form.value).subscribe((data) => {
        console.log(data);
        this.form.reset();
        this.clients.push(data);
      });
    }
  }
  initForms() {
    this.form = new FormGroup({
      name: new FormControl('', {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
      lastName: new FormControl('', {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
    });
  }

  constructor(private clientService: ClientsService) {}

  ngOnInit() {
    this.clientSelected = new Client(0, '', '');

    this.clientService.getClients().subscribe((data) => {
      this.clients = data;
      if (this.clients) {
        this.clientSelected = this.clients[0];
      }

      this.initForms();
    });
    this.formEdit = new FormGroup({
      id: new FormControl(this.clientSelected.id),
      name: new FormControl(this.clientSelected.name, {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
      lastName: new FormControl(this.clientSelected.lastName, {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
    });
  }
}
