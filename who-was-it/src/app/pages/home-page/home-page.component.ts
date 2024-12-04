import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { Opcione, VerPost } from '../../models/ver-post';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup } from '@angular/forms';
import { PostResponse } from '../../models/post-response';
import { DarMegusta } from '../../models/dar-megusta';
import { VerPerfil } from '../../models/ver-perfil';
import { CuestionarioResponse } from '../../models/cuestionario-response';
import { OpcionesResponse } from '../../models/crear-opciones';
import { VotarResponse } from '../../models/votar-response';
import { ResultadoVotacion } from '../../models/resultado-votacion';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  @ViewChild('detailsModalComandas') detailsModalComandas!: TemplateRef<any>;
  @ViewChild('reposts') reposts!: TemplateRef<any>;

  verPosts: VerPost[] = [];
  modal: any;
  idPost!: string;
  darMegusta!: DarMegusta;
  perfil!: VerPerfil;
  recomened!: VerPost;
  comprobarUsuarioVotado!:string;
  idCuestionario!:string;
  votacion!:VotarResponse;
  usuarioActualId: string | null = null;

  resultadoVotacion: ResultadoVotacion [] = [];
  cuestionarioCreado!: CuestionarioResponse | null;
  opcionesList: string[] = [];
  mostrarCuestionario: boolean = false;
cuestionarioCreados: boolean = false;

opciones: string[] = [];
tituloCuestionarioControl = new FormControl('', { nonNullable: true });
nuevaOpcionControl = new FormControl('', { nonNullable: true });

toggleCuestionario() {
  this.mostrarCuestionario = !this.mostrarCuestionario;
}

  // Formularios
  crearCuestionarioForm = new FormGroup({
    titulo: new FormControl(''),
  });

  crearOpcionesForm = new FormGroup({
    opciones: new FormControl(''),
  });

  crerPost = new FormGroup({
    contenido: new FormControl(''),
    id: new FormControl(''),
    idCuestionario: new FormControl(''),
  });

  crerRepost = new FormGroup({
    contenido: new FormControl(''),
    id: new FormControl(''),
    idCuestionario: new FormControl(''),
  });

  constructor(private service: UsuarioServiceService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.verLosPost();
    this.verPerfil();
    this.usuarioActualId = localStorage.getItem('User_ID');

    this.resultadoDeLaVotacion();
  }

  // Crear Cuestionario
  crearCuestionario() {
    const titulo = this.crearCuestionarioForm.value.titulo; // Obtén el valor del campo 'titulo'
    
    if (titulo && titulo.trim()) {
      // Asegúrate de que el título no esté vacío antes de enviarlo
      this.service.crearCuestionario(titulo).subscribe((cuestionario: CuestionarioResponse) => {
        this.cuestionarioCreado = cuestionario; 
        console.log('Cuestionario creado:', this.cuestionarioCreado); // Verifica que el título esté presente aquí
      });
    } else {
      console.error('El título del cuestionario está vacío');
    }
  }
  
  
  agregarOpcion() {
    if (this.cuestionarioCreado) {
      this.service.crearOpciones(this.cuestionarioCreado.id!, this.crearOpcionesForm.value.opciones!).subscribe((respuesta: OpcionesResponse) => {
        this.opcionesList.push(this.crearOpcionesForm.value.opciones!);
        console.log('Opción agregada:', respuesta);
        console.log('Opciones List:', this.opcionesList);  // Verifica las opciones almacenadas
        this.crearOpcionesForm.reset();
      });
    }
  }
  
  eliminarOpcion(index: number) {
    this.opciones.splice(index, 1);
  }

  votar(id: string): void {
    this.service.votar(id).subscribe(r => {
        this.votacion = r;
        this.comprobarUsuarioVotado=r.getUsuario.id;
        this.idCuestionario = r.idCuestionario;
        this.verLosPost();
        
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
  

  
  // Publicar Post
  login(modal: any) {
    if (this.cuestionarioCreado) {
      this.crerPost.patchValue({ idCuestionario: this.cuestionarioCreado.id });
    }

    this.service
      .crearPost(this.crerPost.value.contenido!, this.crerPost.value.id!, this.crerPost.value.idCuestionario!)
      .subscribe((post: PostResponse) => {
        this.verLosPost();
        modal.close();
        this.resetCuestionario();
      });
  }

  // Publicar Repost
  repost(modal: any) {
    if (this.cuestionarioCreado) {
      this.crerRepost.patchValue({ idCuestionario: this.cuestionarioCreado.id });
    }

    this.service
      .crearPost(this.crerRepost.value.contenido!, this.crerRepost.value.id!, this.crerRepost.value.idCuestionario!)
      .subscribe((post: PostResponse) => {
        this.verLosPost();
        modal.close();
        this.resetCuestionario();
      });
  }

  // Obtener Perfil
  verPerfil() {
    this.service.verPerfil().subscribe((perfil: VerPerfil) => {
      this.perfil = perfil;
    });
  }

  // Obtener Posts
  verLosPost() {
    this.service.verPost().subscribe((posts: VerPost[]) => {
      this.verPosts = posts;
      console.log('Posts:', this.verPosts);
    });
  }

  // Recomendar Post
  recomendar(id: string) {
    this.service.recomendar(id).subscribe((post: VerPost) => {
      this.recomened = post;
      this.verLosPost();
    });
  }

  // Dar "Me gusta" a un Post
  darmegusta(id: string) {
    this.service.darMegusta(id).subscribe((response: DarMegusta) => {
      this.darMegusta = response;
      this.verLosPost();
    });
  }

  // Abrir Modal para Comandas
  openModalComandas(comanda: any) {
    this.modalService.open(this.detailsModalComandas, { ariaLabelledBy: 'modal-basic-title' });
  }

  // Abrir Modal para Repost
  openModalRepost(postId: string) {
    this.crerRepost.patchValue({ id: postId });
    this.modalService.open(this.reposts, { ariaLabelledBy: 'modal-basic-title' });
  }

  // Resetear Cuestionario
  resetCuestionario() {
    this.cuestionarioCreado = null;
    this.opcionesList = [];
    this.crearCuestionarioForm.reset();
    this.crearOpcionesForm.reset();
  }
}
