import { Component } from '@angular/core';
import { IDisciplina } from 'src/app/interfaces/IDisciplina';
import { ITurma } from 'src/app/interfaces/ITurma';
import { GetAllDisciplinasServiceService } from 'src/app/services/disciplina/get-all-disciplinas.service';
import { GetAllTurmasServiceService } from 'src/app/services/turma/get-all-turmas.service';
import { GetAllBoletimServiceService } from 'src/app/services/boletim/get-all-boletim.service';
import { IBoletim } from 'src/app/interfaces/IBoletim';
import { IBoletimAlunoView } from 'src/app/interfaces/IBoletimAlunoView';
import { agruparBoletimPorAluno } from 'src/app/utils/agruparBoletimPorAluno';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UpdateNotasDTO } from 'src/app/dtos/UpdateNotasDTO';
import { CreateAvaliacaoService } from 'src/app/services/avaliacoes/create-avaliacao.service';
import { CreateAvaliacaoDTO } from 'src/app/dtos/CreateAvaliacaoDTO';

@Component({
  selector: 'app-home-page',
  standalone: true,
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  imports: [
    CommonModule,
    FormsModule
  ],
})

export class HomePageComponent {
  turmas: ITurma[] = [];
  disciplinas: IDisciplina[] = [];
  boletins: IBoletim[] = [];
  boletimAgrupado: IBoletimAlunoView[] = [];

  modoEdicao = false;
  boletimBackup: any[] = [];

  turmaSelecionadaId: number | null = null;
  disciplinaSelecionadaId: number | null = null;

  mostrarFormularioNovaAvaliacao = false;
  novaAvaliacaoNome = '';
  novaAvaliacaoPeso: number = 1;

  constructor(
    private getAllTurmasService: GetAllTurmasServiceService,
    private getAllDisciplinas: GetAllDisciplinasServiceService,
    private getAllBoletimService: GetAllBoletimServiceService,
    private createAvaliacoesService: CreateAvaliacaoService
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

  ativarEdicao() {
    this.boletimBackup = JSON.parse(JSON.stringify(this.boletimAgrupado));
    this.modoEdicao = true;
  }

  cancelarEdicao() {
    this.boletimAgrupado = JSON.parse(JSON.stringify(this.boletimBackup));
    this.modoEdicao = false;
  }

  recalcularMedia(aluno: any) {
    let somaPesos = 0;
    let somaNotas = 0;

    aluno.avaliacoes.forEach((av: any) => {
      if (av.nota !== null && av.nota !== undefined) {
        somaNotas += av.nota * av.peso;
        somaPesos += av.peso;
      }
    });

    aluno.mediaPonderada = somaPesos > 0
      ? +(somaNotas / somaPesos).toFixed(2)
      : null;
  }

  salvarBoletim() {
    if (!this.turmaSelecionadaId || !this.disciplinaSelecionadaId) return;

    const payload: UpdateNotasDTO = {
      notas: this.boletimAgrupado.flatMap(aluno =>
        aluno.avaliacoes.map(av => ({
          alunoId: aluno.alunoId,
          avaliacaoId: av.avaliacaoId,
          valor: av.nota
        }))
      )
    };

    console.log(payload);

    this.getAllBoletimService
      .updateNotas(this.turmaSelecionadaId, this.disciplinaSelecionadaId, payload)
      .subscribe({
        next: () => {
          this.modoEdicao = false;
          console.log('Notas salvas com sucesso!');
          alert('Notas salvas com sucesso!');
        },
        error: (err) => {
          console.error('Erro ao salvar notas:', err);

          if (err.status === 400 && err.error) {
            if (err.error.message) {
              alert('Erro de validação: ' + err.error.message);
            } else {
              alert('Erro de validação: ' + JSON.stringify(err.error));
            }
          } else {
            alert('Erro ao salvar notas. Tente novamente.');
          }
        }

      });
  }

  criarAvaliacao(titulo: string, peso: number) {
    if (!this.disciplinaSelecionadaId) {
      alert('Selecione uma disciplina antes de criar uma avaliação.');
      return;
    }

    if (peso < 1 || peso > 5) {
      alert('O peso da avaliação deve ser entre 1 e 5.');
      return;
    }

    if (titulo.length == 0) {
      alert('A avaliação deve ter um título.')
    }

    const payload: CreateAvaliacaoDTO = {
      titulo: titulo,
      peso: peso,
      disciplinaId: this.disciplinaSelecionadaId
    };

    this.createAvaliacoesService.create(payload).subscribe({
      next: (novaAvaliacao) => {
        alert(`Avaliação "${novaAvaliacao.titulo}" criada com sucesso!`);
        this.carregarBoletim();
        this.mostrarFormularioNovaAvaliacao = false;
      },
      error: (err) => {
        console.error('Erro ao criar avaliação:', err);

        if (err.status === 400 && err.error?.error) {
          alert('Erro de validação: ' + err.error.error);
        } else {
          alert('Erro ao criar avaliação. Tente novamente.');
        }
      }

    });
  }


}
