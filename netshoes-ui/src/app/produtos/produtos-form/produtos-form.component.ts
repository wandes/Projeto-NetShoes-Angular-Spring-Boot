import { ProdutosPesquisaComponent } from './../produtos-pesquisa/produtos-pesquisa.component';
import { Produto } from './../Produto';
import { ProdutoService } from './../produto.service';
import { Component, OnInit, Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable, Subject, empty } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {  Router } from "@angular/router";



@Component({
  selector: 'app-produtos-form',
  templateUrl: './produtos-form.component.html',
  styleUrls: ['./produtos-form.component.css']
})

@Injectable()
export class ProdutosFormComponent implements OnInit {

  produto = new Produto();

 // produtos: Produto[];

  produtos$: Observable<Produto[]>;
  error$ = new Subject<boolean>();


  constructor(private produtoService: ProdutoService, private router: Router) { }

  ngOnInit() {
    //this.produtoService.findAll().subscribe(dados => this.produtos = dados);
  
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

  //recebendo um objeto do tipo NgForm 
  adicionar(form: NgForm) {
    this.produtoService.create(this.produto).subscribe(
      () => this.onRefresh(),
      error => console.error(error),
      () => console.log('request completo')
    );
    alert(this.produto.nome + " cadastrado com sucesso!");
    form.reset();
    this.produto = new Produto();
    this.router.navigate(['/listar']);
  }


}
