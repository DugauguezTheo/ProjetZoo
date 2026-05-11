export interface Compte {
    id?: number;
    login: string;
    password: string;
    role: 'VISITEUR' | 'VETERINAIRE' | 'ADMIN';
}
