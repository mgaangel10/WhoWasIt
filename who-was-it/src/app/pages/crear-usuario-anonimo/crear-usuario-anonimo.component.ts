import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginResponse } from '../../models/login';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { UsuarioAnonimoResponse } from '../../models/usuario-anonimo-response';

@Component({
  selector: 'app-crear-usuario-anonimo',
  templateUrl: './crear-usuario-anonimo.component.html',
  styleUrl: './crear-usuario-anonimo.component.css'
})
export class CrearUsuarioAnonimoComponent {
  constructor(private loginService: UsuarioServiceService, private router: Router) { };

  profileLogin = new FormGroup({
    nombreUsuario: new FormControl(''),
    foto: new FormControl(''),
    conocidoComo: new FormControl('')
  })

  login() {
    console.log('Datos enviados al servidor:', this.profileLogin.value);

    this.loginService.crearUsuarioAnonimo(this.profileLogin.value.nombreUsuario!, this.profileLogin.value.foto!,this.profileLogin.value.conocidoComo!)
      .subscribe((l: UsuarioAnonimoResponse) => {
        this.router.navigate(['/home']);



      });
  }
}
