import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SoinPage } from './soin-page';

describe('SoinPage', () => {
  let component: SoinPage;
  let fixture: ComponentFixture<SoinPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SoinPage],
    }).compileComponents();

    fixture = TestBed.createComponent(SoinPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
