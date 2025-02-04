import { Component, OnInit } from '@angular/core';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { DetallesPost } from '../../models/detalles-post';
import { ActivatedRoute } from '@angular/router';
import { VerPerfil } from '../../models/ver-perfil';
import { VerPost } from '../../models/ver-post';
import { DarMegusta } from '../../models/dar-megusta';
import { VotarResponse } from '../../models/votar-response';
import { ResultadoVotacion } from '../../models/resultado-votacion';
import { VerMensajes } from '../../models/ver-mensajes';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-detalles-post',
  templateUrl: './detalles-post.component.html',
  styleUrl: './detalles-post.component.css'
})
export class DetallesPostComponent implements OnInit{

  detallesDelPost!:DetallesPost;
  postId!: string | null;
  perfil!: VerPerfil;
  recomened!: VerPost;
  darMegusta!: DarMegusta;
  votacion!:VotarResponse;
  comprobarUsuarioVotado!:string;
  idCuestionario!:string;
  comentario: VerMensajes[] = [];
  resultadoVotacion: ResultadoVotacion [] = [];

  constructor(private service:UsuarioServiceService,private route: ActivatedRoute,private r: ActivatedRoute){
    this.route.params.subscribe(params => {
      this.postId = params['idPost'];
    });
  }



  ngOnInit(): void {
    
    this.verDetallesPost();
    this.verPerfil();
    this.verComentarios();
  }

  verDetallesPost(){
    this.postId = this.r.snapshot.paramMap.get('idPost');
    if (this.postId != null) {
    this.service.detallesDelPost(this.postId).subscribe(r=>{
      this.detallesDelPost = r;
    })
  }
  }

  verPerfil() {
    this.service.verPerfil().subscribe((perfil: VerPerfil) => {
      this.perfil = perfil;
    });
  }
  recomendar(id: string) {
    this.service.recomendar(id).subscribe((post: VerPost) => {
      this.recomened = post;
      
    });
  }

  votar(id: string): void {
    this.service.votar(id).subscribe(r => {
        this.votacion = r;
        this.comprobarUsuarioVotado=r.getUsuario.id;
        this.idCuestionario = r.idCuestionario;
        
        
        this.resultadoDeLaVotacion();
    }, error => {
        console.error('Error al votar:', error);
    });
}

resultadoDeLaVotacion(): void {
    
      
      
  this.service.verResultadoVotacion().subscribe(r => {
        console.log('Resultado de la votación:', r); // Agregar este log
        this.resultadoVotacion = r;
    }, error => {
        console.error('Error al obtener los resultados de la votación:', error);
    });


}
  darmegusta(id: string) {
    this.service.darMegusta(id).subscribe((response: DarMegusta) => {
      this.darMegusta = response;
      this.verDetallesPost();
    });
  }

  verComentarios(){
    this.service.verComentarios(this.postId!).subscribe(r=>{
      this.comentario = r;
    })
  }

  profileLogin = new FormGroup({
    contenido: new FormControl('')
    
  })

  login() {
    console.log('Datos enviados al servidor:', this.profileLogin.value);

    this.service.comentar(this.postId!,this.profileLogin.value.contenido!)
      .subscribe((l: VerMensajes) => {
       this.verComentarios();
        this.verDetallesPost();
      });
  }
}
