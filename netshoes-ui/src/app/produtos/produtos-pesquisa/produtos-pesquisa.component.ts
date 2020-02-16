import { Produto } from './../Produto';
import { ProdutoService } from './../produto.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable, empty, Subject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {  Router } from "@angular/router";


@Component({
  selector: 'app-produtos-pesquisa',
  templateUrl: './produtos-pesquisa.component.html',
  styleUrls: ['./produtos-pesquisa.component.css']
})
export class ProdutosPesquisaComponent implements OnInit {

  produto = new Produto();


  produtos$: Observable<Produto[]>;
  error$ = new Subject<boolean>();

  constructor(private produtoService: ProdutoService, private router: Router) { }

  ngOnInit() {
    //this.produtoService.findAll().subscribe(dados => this.produtos = dados);
    this.onRefresh();

  }

   onRefresh() {
    this.produtos$ = this.produtoService.findAll()
      .pipe(
        catchError(error => {
          console.log(error);
          this.error$.next(true);
          return empty();
        })
      );
  }

  excluir(produto: Produto) {
    if (confirm("Tem certeza que você quer excluir?")) {
      this.produtoService.delete(produto.id)
        .subscribe(
          success => this.onRefresh(),
          error => alert("Erro ao remover o produto")
        );
    }
  }

  atualizar(){
    this.router.navigate(['/cadastrar']);
  }

}
