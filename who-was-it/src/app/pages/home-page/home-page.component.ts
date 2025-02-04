import { Component, ElementRef, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { Opcione, VerPost } from '../../models/ver-post';
import { NgbModal, NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup } from '@angular/forms';
import { PostResponse } from '../../models/post-response';
import { DarMegusta } from '../../models/dar-megusta';
import { VerPerfil } from '../../models/ver-perfil';
import { CuestionarioResponse } from '../../models/cuestionario-response';
import { OpcionesResponse } from '../../models/crear-opciones';
import { VotarResponse } from '../../models/votar-response';
import { ResultadoVotacion } from '../../models/resultado-votacion';
import { VerOpciones } from '../../models/ver-opciones';
import { ActivatedRoute, Router } from '@angular/router';
import { PostUnaVezResponse } from '../../models/post-una-vez-reponse';
import { ProvinciasResponse } from '../../models/provincias-response';
import { EstadisticasDelPost } from '../../models/estadisticas-del-post';
import { LugaresAll } from '../../models/todos-lugares';
import { debounceTime, distinctUntilChanged, filter, map, merge, Observable, OperatorFunction, Subject } from 'rxjs';

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
  obtenerIdPost!:string;
  idPueblo!:string;
  unaVez!:PostUnaVezResponse;
  darMegusta!: DarMegusta;
  perfil!: VerPerfil;
  nombrePueblo!:string;
  todasProvincias: ProvinciasResponse [] = [];
  recomened!: VerPost;
  usuerName!: string;
  comprobarUsuarioVotado!:string;
  idCuestionario!:string;
  op:OpcionesResponse [] = [];
  verOpcioness:VerOpciones [] = [];
  votacion!:VotarResponse;
  usuarioActualId: string | null = null;
  estdisticas!:EstadisticasDelPost;
  resultadoVotacion: ResultadoVotacion [] = [];
  cuestionarioCreado!: CuestionarioResponse | null;
  model: any;
  modelInput:any;

  opcionesList: string[] = [];
  todosLugares: string[] = [];
  mostrarCuestionario: boolean = false;
cuestionarioCreados: boolean = false;
@ViewChild('instance', { static: true }) instance!: NgbTypeahead;
@ViewChild('instance1', { static: true }) instance1!: NgbTypeahead;
  
focus$ = new Subject<string>();
click$ = new Subject<string>();
opciones: string[] = [];
tituloCuestionarioControl = new FormControl('', { nonNullable: true });
nuevaOpcionControl = new FormControl('', { nonNullable: true });

toggleCuestionario() {
  this.mostrarCuestionario = !this.mostrarCuestionario;
}

//todos los lugares
verTodosLugares() {
  this.service.AllLugares().subscribe((r: LugaresAll) => {
    this.todosLugares = [
      ...r.lugares.map(lugar => lugar.nombreLugar),
      ...r.pueblos.map(pueblo => pueblo.nombrePueblo)
    ];
  });
}



// Función de búsqueda para Typeahead
search: OperatorFunction<string, readonly string[]> = (text$: Observable<string>) => {
  const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
  const clicksWithClosedPopup$ = this.click$.pipe(filter(() => !this.instance.isPopupOpen()));
  const inputFocus$ = this.focus$;

  return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
    map(term => {
      if (term === '') return this.todosLugares.slice(0, 5);
      
      const filtered = this.todosLugares
        .filter(v => v.toLowerCase().includes(term.toLowerCase()))
        .slice(0, 10);

      // Si el término ingresado no está en la lista, lo agregamos como opción
      if (!filtered.includes(term)) {
        filtered.push(term);
      }

      return filtered;
    })
  );
};
@ViewChild('inputLugar', { static: false }) inputLugar!: ElementRef;


onSelect(event: any) {
  if (event && event.item) {
    // Si selecciona un elemento de la lista
    this.nombrePueblo = event.item;
  } else {
    // Si escribe un nuevo lugar que no está en la lista
    this.nombrePueblo = this.inputLugar.nativeElement.value; 
  }

  console.log('Lugar seleccionado o nuevo:', this.nombrePueblo);
  this.crerPost.patchValue({ lugar: this.nombrePueblo });

  console.log('Formulario actualizado:', this.crerPost.value);
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
    lugar: new FormControl(),
    id: new FormControl(''),
    idCuestionario: new FormControl(''),
    postUnaVez : new FormControl(),
    desorden: new FormControl()
    
  });

  crerRepost = new FormGroup({
    contenido: new FormControl(''),
    id: new FormControl(''),
    idCuestionario: new FormControl(''),
    postUnaVez : new FormControl(),
    desorden: new FormControl()
  });

  constructor(private service: UsuarioServiceService, private modalService: NgbModal,private router: Router) {}

  ngOnInit(): void {
    this.verLosPost();
    this.verPerfil();
    this.filtrarPost();
    this.ObtenerEstadisticas();
    this.verTodosLugares();
    this.provincias();
    this.usuarioActualId = localStorage.getItem('User_ID');
    this.verLasOpcionesDelCUestionario();
    this.resultadoDeLaVotacion();
    this.verPostUnaVEz(this.idPost);
  }

  ObtenerEstadisticas(){
    console.log('holaaaaaaaa '+this.obtenerIdPost)
    this.service.estadisticasDelPost(this.obtenerIdPost).subscribe(r=>{
      this.estdisticas = r;
    })
  }

  provincias(){
    this.service.AllProvincias().subscribe(r=>{
      this.todasProvincias = r;
    })
  }

  cogerIdPueblo(id:string){
    this.idPueblo = id;
    
    console.log('id del pueblo: '+this.idPueblo)
    this.filtrarPost();

  }
  filtrarPost(){
    if(this.idPueblo!=null && this.nombrePueblo!=null){
      this.service.FiltrarPost(this.idPueblo).subscribe(r=>{
        this.verPosts = r;
        console.log('se esta filtrando')
      })
    }else{
      this.nombrePueblo = 'La puebla del rio';
      this.service.FiltrarPost('141f26c9-640b-4e4e-aeb5-bcce5a88d79a').subscribe(r=>{
        this.verPosts = r;
        console.log('se esta filtrando')
      })
    }
 
  }

  verPostUnaVEz(id:string){
    this.idPost = id;
    this.service.verPostUnaVez(id).subscribe(r=>{
      this.unaVez = r;
      this.verLosPost();
    })
  }

  open(content:any,id:string) {
    this.obtenerIdPost = id;
    console.log('holaaaaaaaa '+this.obtenerIdPost)
		this.modalService.open(content);
    this.ObtenerEstadisticas();
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
        console.log('Opciones List:', this.opcionesList);
        this.verLasOpcionesDelCUestionario();  // Verifica las opciones almacenadas
        this.crearOpcionesForm.reset();
      });
    }
  }
  

  eliminarOpciones(id:string){
    this.service.eliminarOpcion(id).subscribe(r=>{
      this.verLasOpcionesDelCUestionario();
    })
  }
  eliminarCuestionario(){
    this.service.eliminarCuestionario(this.cuestionarioCreado?.id!).subscribe(r=>{

    })
  }
  verLasOpcionesDelCUestionario(){
    this.service.verOpcionesCuestionario(this.cuestionarioCreado?.id!).subscribe(r=>{
      this.verOpcioness = r;
    })
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
    if(this.idPueblo!=null){
      this.service
      .crearPost(this.idPueblo,this.crerPost.value.contenido!,this.crerPost.value.lugar!, this.crerPost.value.id!, this.crerPost.value.idCuestionario!,this.crerPost.value.postUnaVez!,this.crerPost.value.desorden!)
      .subscribe((post: PostResponse) => {
        
        this.verLosPost();
        this.crerPost.reset();
        modal.close();
        this.resetCuestionario();
      });
    }else{
      this.service
      .crearPost('141f26c9-640b-4e4e-aeb5-bcce5a88d79a',this.crerPost.value.contenido!,this.crerPost.value.lugar!, this.crerPost.value.id!, this.crerPost.value.idCuestionario!,this.crerPost.value.postUnaVez!,this.crerPost.value.desorden!)
      .subscribe((post: PostResponse) => {
        
        this.verLosPost();
        modal.close();
        this.resetCuestionario();
      });
    }

    
  }

  // Publicar Repost
  repost(modal: any) {
    if (this.cuestionarioCreado) {
      this.crerRepost.patchValue({ idCuestionario: this.cuestionarioCreado.id });
    }

    this.service
      .crearPost(this.idPueblo,this.crerRepost.value.contenido!,this.crerPost.value.lugar!, this.crerRepost.value.id!, this.crerRepost.value.idCuestionario!,this.crerRepost.value.postUnaVez!,this.crerPost.value.desorden!)
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
     
      let nombre = localStorage.getItem('USERNAME');
      this.usuerName = nombre!;
      console.log('nombre de uuario: '+this.usuerName)
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

  detallesPost(id:string){
    
      this.router.navigate(['/detalles-post', id]);
    
  }
}
