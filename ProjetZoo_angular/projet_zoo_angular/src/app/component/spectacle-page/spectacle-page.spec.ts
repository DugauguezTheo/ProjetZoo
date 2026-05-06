import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpectaclePage } from './spectacle-page';

describe('SpectaclePage', () => {
  let component: SpectaclePage;
  let fixture: ComponentFixture<SpectaclePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpectaclePage],
    }).compileComponents();

    fixture = TestBed.createComponent(SpectaclePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
