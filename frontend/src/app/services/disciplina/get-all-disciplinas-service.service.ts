import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IDisciplina } from 'src/app/interfaces/IDisciplina';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class GetAllDisciplinasServiceService {

  private url = `${environment.apiUrl}/disciplinas` 

  constructor(private http: HttpClient) { }

    getAll(): Observable<IDisciplina[]> {
    return this.http.get<IDisciplina[]>(this.url)
  }
}
