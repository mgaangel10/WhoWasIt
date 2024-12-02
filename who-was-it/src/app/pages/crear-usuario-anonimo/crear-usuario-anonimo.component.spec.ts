import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearUsuarioAnonimoComponent } from './crear-usuario-anonimo.component';

describe('CrearUsuarioAnonimoComponent', () => {
  let component: CrearUsuarioAnonimoComponent;
  let fixture: ComponentFixture<CrearUsuarioAnonimoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CrearUsuarioAnonimoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CrearUsuarioAnonimoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
