import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateComptePage } from './create-compte-page';

describe('CreateComptePage', () => {
  let component: CreateComptePage;
  let fixture: ComponentFixture<CreateComptePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateComptePage],
    }).compileComponents();

    fixture = TestBed.createComponent(CreateComptePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
