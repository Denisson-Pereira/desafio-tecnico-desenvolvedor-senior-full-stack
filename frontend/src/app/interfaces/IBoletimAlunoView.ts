export interface IBoletimAlunoView {
  alunoId: number;
  alunoNome: string;
  mediaPonderada: number | null;
  avaliacoes: {
    avaliacaoId: number;
    titulo: string;
    peso: number;
    nota: number | null;
  }[];
}
