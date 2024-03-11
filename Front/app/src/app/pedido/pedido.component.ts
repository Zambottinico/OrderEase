import { CommonModule } from '@angular/common';
import { Component, Input, SimpleChanges } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ProductService } from '../product.service';
import { Product } from '../models/Product';
import { OrderService } from '../order.service';
import { GetDetailDto } from '../models/getDetailDto';
@Component({
  selector: 'app-pedido',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './pedido.component.html',
  styleUrl: './pedido.component.css',
})
export class PedidoComponent {
  @Input() id: number;
  @Input() idLounge: number;
  form: FormGroup<any>;
  products: Product[] = [];
  details: GetDetailDto[] = [];
  constructor(
    private fb: FormBuilder,
    private ProductService: ProductService,
    private orderService: OrderService
  ) {
    this.form = this.fb.group({
      product: ['', [Validators.required]],
      quantity: [1, [Validators.required, Validators.min(1)]],
      comment: ['', [Validators.maxLength(256)]],
    });
  }

  addProduct(quantity: number) {
    if (quantity === -1 && this.form.get('quantity')?.value === 1) {
      this.form.get('quantity')?.setValue(1);
    } else {
      this.form
        .get('quantity')
        ?.setValue(this.form.get('quantity')?.value + quantity);
    }
  }

  addDetail() {
    if (this.form.valid) {
      this.orderService
        .addDetail({
          idLounge: this.idLounge,
          idTable: this.id,
          idProduct: this.form.get('product')?.value,
          quantity: this.form.get('quantity')?.value,
          comment: this.form.get('comment')?.value,
        })
        .subscribe((data) => {
          this.details.push(data);
          this.form.reset();
          this.form.get('quantity')?.setValue(1);
        });
    }
  }
  onDelete(detail: number) {
    this.orderService.deleteDetail(detail).subscribe((data) => {
      this.details = this.details.filter((d) => d.id !== detail);
    });
  }
  ngOnChanges(changes: SimpleChanges) {
    if (changes['id'] && this.id != undefined) {
      this.ProductService.getProducts().subscribe((data) => {
        this.products = data;
      });
      console.log(this.id);
      this.orderService.getDetails(this.idLounge, this.id).subscribe((data) => {
        this.details = data;
      });
    }
  }
}
