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
import { VerOpciones } from '../models/ver-opciones';
import { DetallesPost } from '../models/detalles-post';
import { VerMensajes } from '../models/ver-mensajes';
import { CharlaResponse } from '../models/charla-response';
import { VerCharla } from '../models/ver-charla';
import { PostUnaVezResponse } from '../models/post-una-vez-reponse';
import { TodosAvatares } from '../models/todos-avatares';
import { EstadisticasDelPost } from '../models/estadisticas-del-post';
import { RegistroResponse } from '../models/registro-response';


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

  RegistroUsuario(email:string,name:string,lastName:string,pais:string,provincia:string,pueblo:string,password:string,phoneNumber:string):Observable<RegistroResponse>{
    return this.http.post<RegistroResponse>(`${this.url}/auth/register/user`,
      {
        "email": `${email}`,
        "name": `${name}`,
        "lastName": `${lastName}`,
        "pais": `${pais}`,
        "provincia": `${provincia}`,
        "pueblo": `${pueblo}`,
        "password": `${password}`,
        "phoneNumber": `${phoneNumber}`,
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

  crearPost(contenido:string,id:string,idCuestionario:string,postUnaVez:boolean,desorden:boolean):Observable<PostResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<PostResponse>(`${this.url}/usuario/nuevo/post`,
      {
        "contenido": `${contenido}`,
        "id": `${id}`,
        "idCuestionario":`${idCuestionario}`,
        "postUnaVez":`${postUnaVez}`,
        "desorden":`${desorden}`
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

  verOpcionesCuestionario(id:string):Observable<VerOpciones[]>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<VerOpciones[]>(`${this.url}/usuario/ver/opciones/${id}`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  eliminarCuestionario(id:string){
    let token = localStorage.getItem('TOKEN');

    return this.http.delete(`${this.url}/usuario/eliminar/cuestionario/${id}`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  detallesDelPost(id:string):Observable<DetallesPost>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<DetallesPost>(`${this.url}/usuario/ver/detalles/post/${id}`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  verComentarios(id:string):Observable<VerMensajes[]>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<VerMensajes[]>(`${this.url}/usuario/ver/comentarios/${id}`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  comentar(id:string,contenido:string):Observable<VerMensajes>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<VerMensajes>(`${this.url}/usuario/comentar/${id}`,{
      "contenido": `${contenido}`
    },
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  crearCharla(contenido:string):Observable<CharlaResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<CharlaResponse>(`${this.url}/usuario/crear/charla`,{
      "contenido": `${contenido}`
    },
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  verCharla():Observable<VerCharla[]>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<VerCharla[]>(`${this.url}/usuario/ver/charlas`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  darMegustaCharla(id:string):Observable<DarMegusta>{
    let token = localStorage.getItem('TOKEN');

    return this.http.post<DarMegusta>(`${this.url}/usuario/dar/megusta/charla/${id}`,{},
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
  }

  verPostUnaVez(id:string):Observable<PostUnaVezResponse>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<PostUnaVezResponse>(`${this.url}/usuario/ver/post/una/vez/${id}`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });

  }
  
  todosAvatares():Observable<TodosAvatares[]>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<TodosAvatares[]>(`${this.url}/usuario/todos/avatares`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });

  }

  estadisticasDelPost(id:string):Observable<EstadisticasDelPost>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<EstadisticasDelPost>(`${this.url}/usuario/estadisticas/del/post/${id}`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });

  }


}
