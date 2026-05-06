import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AchatPage } from './achat-page';

describe('AchatPage', () => {
  let component: AchatPage;
  let fixture: ComponentFixture<AchatPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AchatPage],
    }).compileComponents();

    fixture = TestBed.createComponent(AchatPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
