import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { VerPost } from '../../models/ver-post';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup } from '@angular/forms';
import { PostResponse } from '../../models/post-response';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit{
  @ViewChild('detailsModalComandas') detailsModalComandas!: TemplateRef<any>;
  @ViewChild('reposts') reposts!: TemplateRef<any>;

  verPosts: VerPost [] = [];
  modal:any;
  idPost!:string;
  constructor(private service:UsuarioServiceService,private modalService: NgbModal,){}
  crerPost = new FormGroup({
    contenido: new FormControl(''),
    id: new FormControl('')
  })

  login(modal:any) {
    console.log('Datos enviados al servidor:', this.crerPost.value);

    this.service.crearPost(this.crerPost.value.contenido!, this.crerPost.value.id!)
      .subscribe((l: PostResponse) => {
        this.verLosPost();
        modal.close();

      });
  }

  crerRepost = new FormGroup({
    contenido: new FormControl(''),
    id: new FormControl('')
  })

  repost(modal: any) {
    console.log('Datos enviados al servidor:', this.crerRepost.value);

    this.service.crearPost(this.crerRepost.value.contenido!, this.crerRepost.value.id!)
      .subscribe((l: PostResponse) => {
        this.verLosPost();
        modal.close(); // Cierra el modal después de la respuesta exitosa
      });
}



  ngOnInit(): void {
    this.verLosPost();
  }

  verLosPost(){
    this.service.verPost().subscribe(r=>{
      this.verPosts = r;
      console.log(this.verPosts)
    })
  }

  openModalComandas(comanda: any) {
    
    this.modalService.open(this.detailsModalComandas, { ariaLabelledBy: 'modal-basic-title' });
  }
  openModalRepost(postId: string) {
    this.crerRepost.patchValue({ id: postId }); // Establecer el id de la publicación en el formulario
    this.modalService.open(this.reposts, { ariaLabelledBy: 'modal-basic-title' });
}



}
