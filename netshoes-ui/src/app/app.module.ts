import { HttpClientModule } from '@angular/common/http';
import { CoreModule } from './core/core.module';


import { ProdutosModule } from './produtos/produtos.module';


import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';


import { AppComponent } from './app.component';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';

import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';

import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import { CurrencyMaskModule } from "ng2-currency-mask";
import { ProdutoService } from './produtos/produto.service';

import { AppRoutingModule } from './app-routing.module';


@NgModule({
  declarations: [
    AppComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    ProdutosModule,
    CoreModule,

    InputTextModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    MessagesModule,
    MessageModule,
    CurrencyMaskModule,
    HttpClientModule,
    AppRoutingModule
  ],
  // injeção de dependencia, fornecendo para o angular a classe, todos utilizam a mesma instancia
  providers: [ ProdutoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
