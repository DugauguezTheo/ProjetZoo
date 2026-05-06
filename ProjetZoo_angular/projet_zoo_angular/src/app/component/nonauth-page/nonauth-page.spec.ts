import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NonauthPage } from './nonauth-page';

describe('NonauthPage', () => {
  let component: NonauthPage;
  let fixture: ComponentFixture<NonauthPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NonauthPage],
    }).compileComponents();

    fixture = TestBed.createComponent(NonauthPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
