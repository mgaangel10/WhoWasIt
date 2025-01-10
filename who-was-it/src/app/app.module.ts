import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { MatStepperModule } from '@angular/material/stepper'; // Importar MatStepperModule
import {MatMenuModule} from '@angular/material/menu';

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
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
import { RegisterPageComponent } from './pages/register-page/register-page.component';

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
    CharlasPageComponent,
    RegisterPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MatStepperModule,
    MatMenuModule,
    MatSlideToggleModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    HttpClientModule,
    MatInputModule,
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
