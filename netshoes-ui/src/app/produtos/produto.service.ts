
import { Produto } from './Produto';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { take, tap, delay } from 'rxjs/operators';
import { Observable } from 'rxjs';


@Injectable({
    providedIn: 'root'
})

export class ProdutoService {

    private readonly API = 'http://localhost:8080/api';

    constructor(private http: HttpClient) { }

    // Http Headers
    // httpOptions = {
    //     headers: new HttpHeaders({
    //         'Content-Type': 'application/json'
    //     })
    // }

    findAll(): Observable<Produto[]>  {
        return this.http.get<Produto[]>(`${this.API}/produtos`).pipe(
            //delay(2000),
            tap(console.log)
        );
    }

    create(produto: Produto) {
        return this.http.post(`${this.API}/produto`, produto).pipe(take(1));
    }

    delete(id: number) {
        return this.http.delete(`${this.API}/produto/${id}`).pipe(take(1));
    }

}