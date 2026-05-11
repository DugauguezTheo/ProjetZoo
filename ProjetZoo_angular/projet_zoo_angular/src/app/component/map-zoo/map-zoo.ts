import { Component,inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-map-zoo',
  imports: [],
  templateUrl: './map-zoo.html',
  styleUrl: './map-zoo.css',
})
export class MapZoo {
  private readonly router = inject(Router);

  onMapClick(event: MouseEvent): void {
    const target = event.target as Element | null;

    const path = target?.closest?.('path[data-route]') as SVGPathElement | null;
    console.log(target);
    console.log("click");
    console.log(path);
    if (!path) {
      return;
    }

    const route = path.getAttribute('data-route');

    if (!route) {
      return;
    }

    this.routeEnclos(route);
  }

  routeEnclos(route: string): void {
    this.router.navigateByUrl(route);
  }
}
