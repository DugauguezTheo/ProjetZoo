import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VeterinairePage } from './veterinaire-page';

describe('VeterinairePage', () => {
  let component: VeterinairePage;
  let fixture: ComponentFixture<VeterinairePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VeterinairePage],
    }).compileComponents();

    fixture = TestBed.createComponent(VeterinairePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
