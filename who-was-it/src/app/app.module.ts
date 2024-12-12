import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicioSesionComponent } from './pages/inicio-sesion/inicio-sesion.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { DetallesPostComponent } from './pages/detalles-post/detalles-post.component';
import { RegisterComponent } from './pages/register/register.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { CrearUsuarioAnonimoComponent } from './pages/crear-usuario-anonimo/crear-usuario-anonimo.component';
import { VerMencionesComponent } from './pages/ver-menciones/ver-menciones.component';
import { HeaderComponent } from './componentes/header/header.component';
import { RepostStackComponent } from './pages/repost-stack/repost-stack.component';
import { CharlasPageComponent } from './pages/charlas-page/charlas-page.component';

@NgModule({
  declarations: [
    AppComponent,
    InicioSesionComponent,
    HomePageComponent,
    DetallesPostComponent,
    RegisterComponent,
    CrearUsuarioAnonimoComponent,
    VerMencionesComponent,
    HeaderComponent,
    RepostStackComponent,
    CharlasPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MatSlideToggleModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    AppRoutingModule
]
,
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
