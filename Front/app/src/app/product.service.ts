import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './models/Product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  putProduct(value: any): Observable<Product> {
    return this.http.put<Product>('http://localhost:8086/product/Edit', value);
  }
  deleteProduct(product: Product): Observable<Product> {
    return this.http.delete<Product>(
      'http://localhost:8086/product/delete/' + product.id
    );
  }
  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('http://localhost:8086/product/');
  }

  postProduct(product: any): Observable<Product> {
    return this.http.post<Product>(
      'http://localhost:8086/product/Create',
      product
    );
  }
}
