import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalPage } from './animal-page';

describe('AnimalPage', () => {
  let component: AnimalPage;
  let fixture: ComponentFixture<AnimalPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnimalPage],
    }).compileComponents();

    fixture = TestBed.createComponent(AnimalPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
