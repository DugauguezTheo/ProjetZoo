import { Routes } from '@angular/router';

export const routes: Routes = [

    { path: 'home', component: AccueilPage, canActivate: [authGuard] },
    { path: 'livre', component: Livres, canActivate: [authGuard] },
    { path: 'auteur', component: AuteurPage, canActivate: [authGuard] },
    { path: 'collection', component: CollectionsPage, canActivate: [authGuard] },
    { path: 'editeur', component: Editeurs, canActivate: [authGuard] },
    { path: 'avis', component: AvisPage, canActivate: [authGuard] },


    // { path: 'home', component: AccueilPage },
    // { path: 'livre', component: Livres },
    // { path: 'auteur', component: AuteurPage },
    // { path: 'collection', component: CollectionsPage },
    // { path: 'editeur', component: Editeurs },
    // { path: 'avis', component: AvisPage },

    { path: 'login', component: LoginPage },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
