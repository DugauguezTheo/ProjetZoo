import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Espece } from '../model/espece';

@Injectable({
  providedIn: 'root',
})
export class EspeceService {
  constructor(private http: HttpClient) {
   }

   private apiUrl = 'http://localhost:8080/api/espece';

  public findAllEspeces() : Observable<Espece[]> {
    return this.http.get<Espece[]>(this.apiUrl);
  }
}
