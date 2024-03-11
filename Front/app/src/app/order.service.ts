import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AddDetailDto } from './models/AddDetailDto';
import { Observable } from 'rxjs';
import { GetDetailDto } from './models/getDetailDto';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  deleteDetail(detail: number) {
    return this.http.delete(`http://localhost:8086/order/${detail}`);
  }
  addDetail(detail: AddDetailDto): Observable<GetDetailDto> {
    return this.http.put<GetDetailDto>(
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
