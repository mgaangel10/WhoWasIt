import { Component, OnInit } from '@angular/core';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { FormControl, FormGroup } from '@angular/forms';
import { CharlaResponse } from '../../models/charla-response';
import { VerCharla } from '../../models/ver-charla';
import { DarMegusta } from '../../models/dar-megusta';
import { interval } from 'rxjs';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-charlas-page',
  templateUrl: './charlas-page.component.html',
  styleUrls: ['./charlas-page.component.css']
})
export class CharlasPageComponent implements OnInit {
  mensajes: VerCharla[] = [];
  darMegusta!: DarMegusta;
  cuentaRegresiva: { horas: number; minutos: number; segundos: number } | null = null;
  mostrarCuentaRegresiva = false;
  

  constructor(private service: UsuarioServiceService,private config: NgbModalConfig,
		private modalService: NgbModal) {
      config.backdrop = 'static';
		config.keyboard = false;
    }

    open(content:any) {
      this.modalService.open(content);
    }


  ngOnInit(): void {
    this.verMensajes();
    this.iniciarCuentaRegresiva();
  }

  verMensajes() {
    this.service.verCharla().subscribe((r) => {
      this.mensajes = r;
    });
  }

  profileLogin = new FormGroup({
    contenido: new FormControl('')
  });

  login() {
    console.log('Datos enviados al servidor:', this.profileLogin.value);

    this.service.crearCharla(this.profileLogin.value.contenido!)
      .subscribe((l: CharlaResponse) => {
        this.verMensajes();
      });
  }

  darmegusta(id: string) {
    this.service.darMegustaCharla(id).subscribe((response: DarMegusta) => {
      this.darMegusta = response;
      this.verMensajes();
    });
  }

  iniciarCuentaRegresiva() {
    const ahora = new Date();
    const horaInicioCharlas = new Date();
    horaInicioCharlas.setHours(0,51, 0, 0);

    if (ahora > horaInicioCharlas) {
      horaInicioCharlas.setDate(horaInicioCharlas.getDate() + 1);
    }

    interval(1000).subscribe(() => {
      const ahora = new Date();
      const diferencia = horaInicioCharlas.getTime() - ahora.getTime();

      if (diferencia > 0) {
        const horas = Math.floor(diferencia / (1000 * 60 * 60));
        const minutos = Math.floor((diferencia % (1000 * 60 * 60)) / (1000 * 60));
        const segundos = Math.floor((diferencia % (1000 * 60)) / 1000);

        this.cuentaRegresiva = { horas, minutos, segundos };
        this.mostrarCuentaRegresiva = true;
      } else {
        this.cuentaRegresiva = null;
        this.mostrarCuentaRegresiva = false;

        // Llama a la función para lanzar el confeti
        this.lanzarConfeti();
      }
    });
  }

  lanzarConfeti() {
    confetti({
      particleCount: 150,
      spread: 100,
      origin: { y: 0.6 } // Ajusta la posición del confeti
    });

    // Opcional: Puedes repetir la animación varias veces
    const duration = 3000;
    const animationEnd = Date.now() + duration;
    const interval = setInterval(() => {
      if (Date.now() > animationEnd) {
        clearInterval(interval);
      } else {
        confetti({
          particleCount: 100,
          spread: 70,
          origin: { y: 0.6 }
        });
      }
    }, 250);
  }
 
}


function confetti(arg0: {
  particleCount: number; spread: number; origin: { y: number; }; // Ajusta la posición del confeti
}) {
  throw new Error('Function not implemented.');
}

