package com.denisson.backend.domain.dtos;

import java.math.BigDecimal;
import java.util.List;

public class UpdateNotasDTO {

    private List<NotaDTO> notas;

    public List<NotaDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaDTO> notas) {
        this.notas = notas;
    }

    public static class NotaDTO {
        private Long alunoId;
        private Long avaliacaoId;
        private BigDecimal valor;

        public Long getAlunoId() {
            return alunoId;
        }

        public void setAlunoId(Long alunoId) {
            this.alunoId = alunoId;
        }

        public Long getAvaliacaoId() {
            return avaliacaoId;
        }

        public void setAvaliacaoId(Long avaliacaoId) {
            this.avaliacaoId = avaliacaoId;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }
    }
}
