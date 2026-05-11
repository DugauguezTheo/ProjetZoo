import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapZoo } from './map-zoo';

describe('MapZoo', () => {
  let component: MapZoo;
  let fixture: ComponentFixture<MapZoo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MapZoo],
    }).compileComponents();

    fixture = TestBed.createComponent(MapZoo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
