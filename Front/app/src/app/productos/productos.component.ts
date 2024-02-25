import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RouterModule, RouterOutlet } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../models/Product';

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule, ReactiveFormsModule],
  templateUrl: './productos.component.html',
  styleUrl: './productos.component.css',
})
export class ProductosComponent {
  sortPriceFlag: boolean = false;
  sortNameFlag: boolean = false;
  sortPrice() {
    this.sortPriceFlag = !this.sortPriceFlag;
    if (this.sortPriceFlag) {
      this.products.sort((a, b) => b.price - a.price);
    } else {
      this.products.sort((a, b) => a.price - b.price);
    }
  }
  sortName() {
    this.sortNameFlag = !this.sortNameFlag;
    if (this.sortNameFlag) {
      this.products.sort((a, b) => b.name.localeCompare(a.name));
    } else {
      this.products.sort((a, b) => a.name.localeCompare(b.name));
    }
  }
  onSubmitEdit() {
    console.log(this.formEdit.value);
    this.productService.putProduct(this.formEdit.value).subscribe((data) => {
      console.log(this.productSelected);
      this.products = this.products.filter((p) => p.id !== data.id);
      this.products.push(data);
      this.formEdit.reset();
    });
  }
  productSelected: Product;
  onSelected(produc: Product) {
    this.productSelected = produc;
    this.formEdit = new FormGroup({
      id: new FormControl(this.productSelected.id),
      name: new FormControl(this.productSelected.name, {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
      price: new FormControl(this.productSelected.price, {
        validators: [Validators.required, Validators.min(0)],
      }),
    });
  }
  onDelete(product: Product) {
    this.productService.deleteProduct(product).subscribe((data) => {
      if (data) {
        this.products = this.products.filter((p) => p.id !== product.id);
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.productService.postProduct(this.form.value).subscribe((data) => {
        console.log(data);
        this.form.reset();
        this.products.push(data);
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
      price: new FormControl('', {
        validators: [Validators.required, Validators.min(0)],
      }),
    });

    this.formEdit = new FormGroup({
      id: new FormControl(this.productSelected.id),
      name: new FormControl(this.productSelected.name, {
        validators: [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
      price: new FormControl(this.productSelected.price, {
        validators: [Validators.required, Validators.min(0)],
      }),
    });
  }

  form: FormGroup<any>;
  formEdit: FormGroup<any>;
  products: Product[] = [];
  constructor(private productService: ProductService) {
    this.productService.getProducts().subscribe((data) => {
      this.products = data;
      this.productSelected = this.products[0];
      this.initForms();
    });
  }
}
