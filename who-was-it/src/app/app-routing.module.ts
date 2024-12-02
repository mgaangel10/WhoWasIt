import { NgModule } from '@angular/core';

import { InicioSesionComponent } from './pages/inicio-sesion/inicio-sesion.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { CrearUsuarioAnonimoComponent } from './pages/crear-usuario-anonimo/crear-usuario-anonimo.component';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'login', component: InicioSesionComponent},
  { path: 'usuario-anonimo', component: CrearUsuarioAnonimoComponent},

  { path: 'home', component: HomePageComponent},


  { path: '**', redirectTo: '/login', pathMatch: 'full' }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
