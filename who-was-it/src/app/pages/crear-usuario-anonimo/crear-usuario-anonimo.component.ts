import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginResponse } from '../../models/login';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { UsuarioAnonimoResponse } from '../../models/usuario-anonimo-response';
import { TodosAvatares } from '../../models/todos-avatares';

@Component({
  selector: 'app-crear-usuario-anonimo',
  templateUrl: './crear-usuario-anonimo.component.html',
  styleUrl: './crear-usuario-anonimo.component.css'
})
export class CrearUsuarioAnonimoComponent implements OnInit{

  avatares:TodosAvatares[] = [];

  constructor(private loginService: UsuarioServiceService, private router: Router) { }

  ngOnInit(): void {
      this.todosAvatares();
  }

  todosAvatares(){
    this.loginService.todosAvatares().subscribe(r=>{
      this.avatares = r;
    })
  }

  profileLogin = new FormGroup({
    nombreUsuario: new FormControl(''),
    foto: new FormControl(''),
    conocidoComo: new FormControl('')
  })

  login() {
    console.log('Datos enviados al servidor:', this.profileLogin.value);

    this.loginService.crearUsuarioAnonimo(this.profileLogin.value.nombreUsuario!, this.profileLogin.value.foto!,this.profileLogin.value.conocidoComo!)
      .subscribe((l: UsuarioAnonimoResponse) => {
        localStorage.setItem('USERNAME', l.nombreUsuario);
        this.router.navigate(['/home']);



      });
  }
}
