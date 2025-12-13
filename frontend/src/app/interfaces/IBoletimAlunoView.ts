export interface IBoletimAlunoView {
  alunoId: number;
  alunoNome: string;
  mediaPonderada: number;
  avaliacoes: {
    avaliacaoId: number;
    titulo: string;
    peso: number;
    nota: number;
  }[];
}
