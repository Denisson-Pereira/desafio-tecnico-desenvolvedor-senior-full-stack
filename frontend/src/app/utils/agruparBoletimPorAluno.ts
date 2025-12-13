import { IBoletim } from 'src/app/interfaces/IBoletim';
import { IBoletimAlunoView } from '../interfaces/IBoletimAlunoView';

export function agruparBoletimPorAluno(
  dados: IBoletim[]
): IBoletimAlunoView[] {

  const mapa = new Map<number, IBoletimAlunoView>();

  dados.forEach(item => {
    if (!mapa.has(item.alunoId)) {
      mapa.set(item.alunoId, {
        alunoId: item.alunoId,
        alunoNome: item.alunoNome,
        mediaPonderada: item.mediaPonderada,
        avaliacoes: []
      });
    }

    mapa.get(item.alunoId)!.avaliacoes.push({
      avaliacaoId: item.avaliacaoId,
      titulo: item.avaliacaoTitulo,
      peso: item.avaliacaoPeso,
      nota: item.notaValor
    });
  });

  return Array.from(mapa.values());
}
