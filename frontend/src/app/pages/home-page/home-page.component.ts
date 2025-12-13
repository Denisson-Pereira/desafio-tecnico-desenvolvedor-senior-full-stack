import { Component } from '@angular/core';
import { IDisciplina } from 'src/app/interfaces/IDisciplina';
import { ITurma } from 'src/app/interfaces/ITurma';
import { GetAllDisciplinasServiceService } from 'src/app/services/disciplina/get-all-disciplinas-service.service';
import { GetAllTurmasServiceService } from 'src/app/services/turma/get-all-turmas-service.service';
import { GetAllBoletimServiceService } from 'src/app/services/boletim/get-all-boletim-service.service';
import { IBoletim } from 'src/app/interfaces/IBoletim';
import { IBoletimAlunoView } from 'src/app/interfaces/IBoletimAlunoView';
import { agruparBoletimPorAluno } from 'src/app/utils/agruparBoletimPorAluno';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  turmas: ITurma[] = [];
  disciplinas: IDisciplina[] = [];
  boletins: IBoletim[] = [];
  boletimAgrupado: IBoletimAlunoView[] = [];

  turmaSelecionadaId: number | null = null;
  disciplinaSelecionadaId: number | null = null;

  constructor(
    private getAllTurmasService: GetAllTurmasServiceService,
    private getAllDisciplinas: GetAllDisciplinasServiceService,
    private getAllBoletimService: GetAllBoletimServiceService
  ) { }

  ngOnInit(): void {
    this.getTurmas();
    this.getDisciplinas();
  }

  getTurmas(): void {
    this.getAllTurmasService.getAll().subscribe((turmas) => (this.turmas = turmas));
  }

  getDisciplinas(): void {
    this.getAllDisciplinas.getAll().subscribe((disciplinas) => (this.disciplinas = disciplinas));
  }

  onTurmaChange(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    this.turmaSelecionadaId = +selectElement.value;
  }

  onDisciplinaChange(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    this.disciplinaSelecionadaId = +selectElement.value;
  }

  carregarBoletim(): void {
    if (this.turmaSelecionadaId && this.disciplinaSelecionadaId) {
      this.getAllBoletimService
        .getAll(this.turmaSelecionadaId, this.disciplinaSelecionadaId)
        .subscribe(dados => {
          this.boletimAgrupado = agruparBoletimPorAluno(dados);
        });
    }
  }


}
