import { Routes } from '@angular/router';
import { AnimalPage } from './component/animal-page/animal-page';
import { authGuard } from './guard/auth-guard';
import { Connexion } from './component/connexion/connexion';

export const routes: Routes = [

    { path: 'accueil', component: AccueilPage, canActivate: [authGuard] },
    { path: 'achat', component: AchatPage, canActivate: [authGuard] },
    { path: 'admin', component: AdminPage, canActivate: [authGuard] },
    { path: 'animal', component: AnimalPage, canActivate: [authGuard] },
    { path: 'article', component: ArticlePage, canActivate: [authGuard] },
    { path: 'compte', component: ComptePage, canActivate: [authGuard] },
    { path: 'enclos', component: EnclosPage, canActivate: [authGuard] },
    { path: 'reservation', component: ReservationPage, canActivate: [authGuard] },
    { path: 'soin', component: SoinPage, canActivate: [authGuard] },
    { path: 'spectacle', component: SpectaclePage, canActivate: [authGuard] },
    { path: 'veterinaire', component: VeterinairePage, canActivate: [authGuard] },
    { path: 'visiteur', component: VisiteurPage, canActivate: [authGuard] },

    { path: 'connexion', component: Connexion },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
