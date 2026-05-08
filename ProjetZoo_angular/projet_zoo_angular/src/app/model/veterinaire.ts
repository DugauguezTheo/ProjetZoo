import { Soin } from "./soin";

export interface Veterinaire {
  id?: number;
    login: string;
    password: string;
    soins : Soin;
}
