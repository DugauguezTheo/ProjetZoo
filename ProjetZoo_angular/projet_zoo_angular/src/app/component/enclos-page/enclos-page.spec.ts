import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnclosPage } from './enclos-page';

describe('EnclosPage', () => {
  let component: EnclosPage;
  let fixture: ComponentFixture<EnclosPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnclosPage],
    }).compileComponents();

    fixture = TestBed.createComponent(EnclosPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
