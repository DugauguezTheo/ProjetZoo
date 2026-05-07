import { Routes } from '@angular/router';
import { AnimalPage } from './component/animal-page/animal-page';
import { Connexion } from './component/connexion/connexion';
import { EnclosPage } from './component/enclos-page/enclos-page';
import { AchatPage } from './component/achat-page/achat-page';
import { AdminPage } from './component/admin-page/admin-page';
import { ArticlePage } from './component/article-page/article-page';
import { ComptePage } from './component/compte-page/compte-page';
import { authGuard } from './guard/auth-guard';
import { ReservationPage } from './component/reservation-page/reservation-page';
import { SoinPage } from './component/soin-page/soin-page';
import { SpectaclePage } from './component/spectacle-page/spectacle-page';
import { VeterinairePage } from './component/veterinaire-page/veterinaire-page';
import { VisiteurPage } from './component/visiteur-page/visiteur-page';
import { NonauthPage } from './component/nonauth-page/nonauth-page';
import { CreateComptePage } from './component/create-compte-page/create-compte-page';

export const routes: Routes = [


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
    { path: 'inscription', component: CreateComptePage},
    { path: 'home', component : NonauthPage},
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
