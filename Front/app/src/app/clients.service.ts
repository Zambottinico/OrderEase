import { Injectable } from '@angular/core';
import { Client } from './models/Client';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClientsService {
  putClient(value: any): Observable<Client> {
    return this.http.put<Client>('http://localhost:8086/client/Edit', value);
  }
  deleteClient(product: Client): Observable<Client> {
    return this.http.delete<Client>(
      'http://localhost:8086/client/delete/' + product.id
    );
  }
  postClient(value: any): Observable<Client> {
    return this.http.post<Client>('http://localhost:8086/client/Create', value);
  }
  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>('http://localhost:8086/client/');
  }

  constructor(private http: HttpClient) {}
}
