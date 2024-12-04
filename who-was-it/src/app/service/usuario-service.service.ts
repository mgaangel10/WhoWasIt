import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../models/login';
import { Observable } from 'rxjs';
import { CrearPost } from '../models/crea-post';
import { VerPost } from '../models/ver-post';
import { PostResponse } from '../models/post-response';
import { UsuarioAnonimoResponse } from '../models/usuario-anonimo-response';
import { DarMegusta } from '../models/dar-megusta';
import { VerPerfil } from '../models/ver-perfil';
import { CuestionarioResponse } from '../models/cuestionario-response';
import { OpcionesResponse } from '../models/crear-opciones';
import { VotarResponse } from '../models/votar-response';
import { ResultadoVotacion } from '../models/resultado-votacion';

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

  crearUsuarioAnonimo(nombreUsuario:string,foto:string,conocidoComo:string):Observable<UsuarioAnonimoResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<UsuarioAnonimoResponse>(`${this.url}/usuario/crear/usuario/anonimo`,
      {
        "nombreUsuario": `${nombreUsuario}`,
        "foto": `${foto}`,
        "conocidoComo":`${conocidoComo}`
      },{
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  crearPost(contenido:string,id:string,idCuestionario:string):Observable<PostResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<PostResponse>(`${this.url}/usuario/nuevo/post`,
      {
        "contenido": `${contenido}`,
        "id": `${id}`,
        "idCuestionario":`${idCuestionario}`
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

  darMegusta(id:string):Observable<DarMegusta>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<DarMegusta>(`${this.url}/usuario/dar/megusta/${id}`,{},
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  verPerfil():Observable<VerPerfil>{
    let token = localStorage.getItem('TOKEN');
    return this.http.get<VerPerfil>(`${this.url}/usuario/ver/perfil`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  verMenciones():Observable<VerPost[]>{
    let token = localStorage.getItem('TOKEN');
    return this.http.get<VerPost[]>(`${this.url}/usuario/ver/menciones`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  recomendar(id:string):Observable<VerPost>{
    let token = localStorage.getItem('TOKEN');
    return this.http.post<VerPost>(`${this.url}/usuario/recomendar/${id}`,{}, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }
  crearCuestionario(titulo:string):Observable<CuestionarioResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<CuestionarioResponse>(`${this.url}/usuario/crear/cuestionario`,
      {
        "titulo": `${titulo}`
        
      },{
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  crearOpciones(id:string,opciones:string):Observable<OpcionesResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<OpcionesResponse>(`${this.url}/usuario/crear/opciones/${id}`,
      {
        
        "opciones": `${opciones}`
        
      },{
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  votar(id:string):Observable<VotarResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<VotarResponse>(`${this.url}/usuario/votar/${id}`,
      {
        "id":`${id}`,
        
        
      },{
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  verResultadoVotacion():Observable<ResultadoVotacion[]>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<ResultadoVotacion[]>(`${this.url}/usuario/todos/cuestioanario/votados`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  eliminarOpcion(id:string){
    let token = localStorage.getItem('TOKEN');

    return this.http.delete(`${this.url}/usuario/eliminar/opcion/${id}`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }



}
