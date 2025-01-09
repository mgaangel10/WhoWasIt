import { NgModule } from '@angular/core';

import { InicioSesionComponent } from './pages/inicio-sesion/inicio-sesion.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { CrearUsuarioAnonimoComponent } from './pages/crear-usuario-anonimo/crear-usuario-anonimo.component';
import { Routes, RouterModule } from '@angular/router';
import { VerMencionesComponent } from './pages/ver-menciones/ver-menciones.component';
import { DetallesPostComponent } from './pages/detalles-post/detalles-post.component';
import { CharlasPageComponent } from './pages/charlas-page/charlas-page.component';
import { RegisterComponent } from './pages/register/register.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';

const routes: Routes = [
  { path: 'login', component: InicioSesionComponent},
  { path: 'register', component: RegisterPageComponent},
  { path: 'usuario-anonimo', component: CrearUsuarioAnonimoComponent},
  { path: 'menciones', component: VerMencionesComponent},
  { path: 'charlas', component: CharlasPageComponent},
  { path: 'detalles-post/:idPost', component: DetallesPostComponent},


  { path: 'home', component: HomePageComponent},


  { path: '**', redirectTo: '/register', pathMatch: 'full' }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
