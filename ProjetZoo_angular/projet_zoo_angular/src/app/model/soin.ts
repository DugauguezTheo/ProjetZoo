import { Veterinaire } from './veterinaire';
import { Animal } from './animal';
export interface Soin {
  id ?: number;
  dateSoin : string;
  description : string;
  veterinaire : Veterinaire;
  animal : Animal;
}
