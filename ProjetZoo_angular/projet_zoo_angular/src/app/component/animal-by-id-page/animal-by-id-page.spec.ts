import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalByIdPage } from './animal-by-id-page';

describe('AnimalByIdPage', () => {
  let component: AnimalByIdPage;
  let fixture: ComponentFixture<AnimalByIdPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnimalByIdPage],
    }).compileComponents();

    fixture = TestBed.createComponent(AnimalByIdPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
