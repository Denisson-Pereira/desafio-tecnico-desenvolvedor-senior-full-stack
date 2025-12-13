export interface UpdateNotasDTO {
  notas: {
    alunoId: number;
    avaliacaoId: number;
    valor: number | null;
  }[];
}