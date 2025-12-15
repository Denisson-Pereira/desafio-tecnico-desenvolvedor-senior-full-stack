import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UpdateNotasDTO } from 'src/app/dtos/UpdateNotasDTO';
import { IBoletim } from 'src/app/interfaces/IBoletim';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class GetAllBoletimServiceService {

  private urlBase = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getAll(turmaId: number, disciplinaId: number): Observable<IBoletim[]> {
    return this.http.get<IBoletim[]>(`${this.urlBase}/turmas/${turmaId}/disciplinas/${disciplinaId}/notas-com-media`);
  }

  updateNotas(turmaId: number, disciplinaId: number, payload: UpdateNotasDTO): Observable<void> {
    return this.http.patch<void>(
      `${this.urlBase}/turmas/${turmaId}/disciplinas/${disciplinaId}/notas`,
      payload
    );
  }
}
