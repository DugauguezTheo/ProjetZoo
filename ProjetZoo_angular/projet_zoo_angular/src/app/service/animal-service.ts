import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Animal } from '../model/animal';

@Injectable({
  providedIn: 'root',
})
export class AnimalService {

  constructor(private http: HttpClient) { }

  public findAllAnimals() {
    return this.http.get<Animal[]>('/animal');
  }

  public findAllByEnclosId(id: number | undefined) {
    return this.http.get<Animal[]>(`/animal/enclos/${id}`)
  }

  public findAnimalById(id: number | undefined) {
    return this.http.get<Animal>(`/animal/${id}`);
  }

  public addAnimal(animal: Animal) {
    return this.http.post<Animal>('/animal', animal);
  }

  public updateAnimal(animal: Animal) {
    return this.http.put<Animal>(`/animal/${animal.id}`, animal);
  }

  public deleteAnimal(id: number | undefined) {
    return this.http.delete(`/animal/${id}`);
  }

}
