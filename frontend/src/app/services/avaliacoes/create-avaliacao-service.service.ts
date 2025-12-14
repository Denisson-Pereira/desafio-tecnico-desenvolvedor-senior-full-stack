import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateAvaliacaoDTO } from 'src/app/dtos/CreateAvaliacaoDTO';
import { IAvaliacao } from 'src/app/interfaces/IAvaliacao';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CreateAvaliacaoService {

  private url = `${environment.apiUrl}/avaliacoes`; 

  constructor(private http: HttpClient) { }

  create(payload: CreateAvaliacaoDTO): Observable<IAvaliacao> {
    return this.http.post<IAvaliacao>(this.url, payload); 
  }
}
