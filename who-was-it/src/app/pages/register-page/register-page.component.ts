import { Component } from '@angular/core';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegistroResponse } from '../../models/registro-response';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {
  step1Form: FormGroup;
  step2Form: FormGroup;
  step3Form: FormGroup;

  constructor(
    private loginService: UsuarioServiceService,
    private router: Router,
    private fb: FormBuilder
  ) {
    // Configuración de los formularios por pasos
    this.step1Form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      lastName: ['', Validators.required]
    });

    this.step2Form = this.fb.group({
      pais: [''],
      provincia: [''],
      pueblo: ['']
    });

    this.step3Form = this.fb.group({
      password: ['', [Validators.required, Validators.minLength(6)]],
      phoneNumber: ['']
    });
  }

  // Método para enviar los datos completos al servidor
  submit() {
    // Combinar los datos de los tres formularios
    const fullFormData = {
      ...this.step1Form.value,
      ...this.step2Form.value,
      ...this.step3Form.value
    };

    console.log('Datos enviados al servidor:', fullFormData);

    // Llamada al servicio para registrar al usuario
    this.loginService
      .RegistroUsuario(
        fullFormData.email,
        fullFormData.name,
        fullFormData.lastName,
        fullFormData.pais,
        fullFormData.provincia,
        fullFormData.pueblo,
        fullFormData.password,
        fullFormData.phoneNumber
      )
      .subscribe((response: RegistroResponse) => {
        console.log('Registro exitoso:', response);
        // Redirigir a la página de inicio de sesión
        this.router.navigate(['/login']);
      });
  }
}
