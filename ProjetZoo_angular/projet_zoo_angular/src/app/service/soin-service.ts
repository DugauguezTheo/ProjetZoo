import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Soin } from '../model/soin';

@Injectable({
  providedIn: 'root',
})
export class SoinService {
  constructor(private http: HttpClient){}

  public findAllSoin() {
    return this.http.get<Soin[]>('/soins');
  }

  public addSoin(soin: Soin) {
    return this.http.post<Soin>('/soins', soin);
  }

  public updateSoin(soin: Soin) {
    return this.http.put<Soin>(`/soins/${soin.id}`, soin);
  }

  public deleteSoin(id: number | undefined) {
    return this.http.delete(`/soins/${id}`);
  }
}

