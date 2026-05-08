import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AchatArticle } from './achat-article';

describe('AchatArticle', () => {
  let component: AchatArticle;
  let fixture: ComponentFixture<AchatArticle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AchatArticle],
    }).compileComponents();

    fixture = TestBed.createComponent(AchatArticle);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
