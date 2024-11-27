import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesPostComponent } from './detalles-post.component';

describe('DetallesPostComponent', () => {
  let component: DetallesPostComponent;
  let fixture: ComponentFixture<DetallesPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetallesPostComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetallesPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
