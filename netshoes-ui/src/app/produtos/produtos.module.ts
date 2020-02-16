import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProdutosPesquisaComponent } from './produtos-pesquisa/produtos-pesquisa.component';
import { FormsModule } from '@angular/forms';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';

import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import { CurrencyMaskModule } from "ng2-currency-mask";
import { AppRoutingModule } from '../app-routing.module';
import { ProdutosFormComponent } from './produtos-form/produtos-form.component';

@NgModule({
  declarations: [ProdutosPesquisaComponent, ProdutosFormComponent, HomeComponent],
  exports: [ProdutosPesquisaComponent,  ProdutosFormComponent, HomeComponent],
  imports: [
    CommonModule,
    FormsModule,

    InputTextModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    MessagesModule,
    MessageModule,
    CurrencyMaskModule,
    AppRoutingModule
  ]
})
export class ProdutosModule {
  
 }
