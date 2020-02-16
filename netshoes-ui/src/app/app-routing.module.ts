import { ProdutosFormComponent } from './produtos/produtos-form/produtos-form.component';
import { HomeComponent } from './produtos/home/home.component';
import { ProdutosPesquisaComponent } from './produtos/produtos-pesquisa/produtos-pesquisa.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'listar', component: ProdutosPesquisaComponent},
  { path: 'cadastrar', component: ProdutosFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
