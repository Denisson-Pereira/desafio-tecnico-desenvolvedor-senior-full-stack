import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ITurma } from 'src/app/interfaces/ITurma';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class GetAllTurmasServiceService {

  private url = `${environment.apiUrl}/turmas` 

  constructor(private http: HttpClient) { }

    getAll(): Observable<ITurma[]> {
    return this.http.get<ITurma[]>(this.url)
  }
}
