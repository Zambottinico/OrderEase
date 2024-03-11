import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AddDetailDto } from './models/AddDetailDto';
import { Observable } from 'rxjs';
import { GetDetailDto } from './models/getDetailDto';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  addDetail(detail: AddDetailDto): Observable<boolean> {
    return this.http.put<boolean>(
      'http://localhost:8086/order/AddDetail/',
      detail
    );
  }
  getDetails(idLounge: number, idTable: number): Observable<GetDetailDto[]> {
    return this.http.get<GetDetailDto[]>(
      `http://localhost:8086/order/getDetails/${idLounge}/${idTable}`
    );
  }
  constructor(private http: HttpClient) {}
}
