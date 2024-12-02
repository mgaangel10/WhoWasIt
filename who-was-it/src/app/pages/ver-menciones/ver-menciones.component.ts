import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { VerPost } from '../../models/ver-post';
import { FormGroup, FormControl } from '@angular/forms';
import { PostResponse } from '../../models/post-response';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-ver-menciones',
  templateUrl: './ver-menciones.component.html',
  styleUrl: './ver-menciones.component.css'
})
export class VerMencionesComponent implements OnInit{
  @ViewChild('reposts') reposts!: TemplateRef<any>;

  menciones: VerPost [] = [];

  constructor(private service:UsuarioServiceService,private modalService: NgbModal){}
  crerRepost = new FormGroup({
    contenido: new FormControl(''),
    id: new FormControl('')
  })

  repost(modal: any) {
    console.log('Datos enviados al servidor:', this.crerRepost.value);

    this.service.crearPost(this.crerRepost.value.contenido!, this.crerRepost.value.id!)
      .subscribe((l: PostResponse) => {
        
        modal.close(); // Cierra el modal después de la respuesta exitosa
      });
}


  ngOnInit(): void {
    this.verMenciones()
  }


  verMenciones(){
    this.service.verMenciones().subscribe(r=>{
      this.menciones = r;
    })
  }


  openModalRepost(postId: string) {
    this.crerRepost.patchValue({ id: postId }); // Establecer el id de la publicación en el formulario
    this.modalService.open(this.reposts, { ariaLabelledBy: 'modal-basic-title' });
}
}
