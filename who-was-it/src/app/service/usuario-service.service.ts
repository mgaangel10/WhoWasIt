import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../models/login';
import { Observable } from 'rxjs';
import { CrearPost } from '../models/crea-post';
import { VerPost } from '../models/ver-post';
import { PostResponse } from '../models/post-response';
import { UsuarioAnonimoResponse } from '../models/usuario-anonimo-response';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServiceService {

  constructor(private http:HttpClient) { }

  url = "http://localhost:9000"

  LoginResponseAdministrador(email: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.url}/auth/login/user`,
      {
        "email": `${email}`,
        "password": `${password}`
      },{
        
      });
  }

  crearUsuarioAnonimo(nombreUsuario:string,foto:string):Observable<UsuarioAnonimoResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<UsuarioAnonimoResponse>(`${this.url}/usuario/crear/usuario/anonimo`,
      {
        "nombreUsuario": `${nombreUsuario}`,
        "foto": `${foto}`
      },{
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  crearPost(contenido:string,id:string):Observable<PostResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<PostResponse>(`${this.url}/usuario/nuevo/post`,
      {
        "contenido": `${contenido}`,
        "id": `${id}`
      },{
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }
  verPost():Observable<VerPost[]>{
    let token = localStorage.getItem('TOKEN');
    return this.http.get<VerPost[]>(`${this.url}/usuario/ver/post`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    });
  }

}
