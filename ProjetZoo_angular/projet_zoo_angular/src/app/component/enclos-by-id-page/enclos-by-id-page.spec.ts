import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnclosByIdPage } from './enclos-by-id-page';

describe('EnclosByIdPage', () => {
  let component: EnclosByIdPage;
  let fixture: ComponentFixture<EnclosByIdPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnclosByIdPage],
    }).compileComponents();

    fixture = TestBed.createComponent(EnclosByIdPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
