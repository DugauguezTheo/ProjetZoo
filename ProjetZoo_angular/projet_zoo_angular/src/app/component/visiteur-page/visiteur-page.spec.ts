import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisiteurPage } from './visiteur-page';

describe('VisiteurPage', () => {
  let component: VisiteurPage;
  let fixture: ComponentFixture<VisiteurPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VisiteurPage],
    }).compileComponents();

    fixture = TestBed.createComponent(VisiteurPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
