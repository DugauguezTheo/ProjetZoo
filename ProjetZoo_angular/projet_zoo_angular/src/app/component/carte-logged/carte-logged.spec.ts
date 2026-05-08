import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarteLogged } from './carte-logged';

describe('CarteLogged', () => {
  let component: CarteLogged;
  let fixture: ComponentFixture<CarteLogged>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarteLogged],
    }).compileComponents();

    fixture = TestBed.createComponent(CarteLogged);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
