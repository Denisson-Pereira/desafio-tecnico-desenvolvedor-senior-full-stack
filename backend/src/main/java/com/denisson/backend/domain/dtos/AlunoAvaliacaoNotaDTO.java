package com.denisson.backend.domain.dtos;

import java.math.BigDecimal;

public class AlunoAvaliacaoNotaDTO {
    private Long alunoId;
    private String alunoNome;
    private Long avaliacaoId;
    private String avaliacaoTitulo;
    private Integer avaliacaoPeso;
    private BigDecimal notaValor;
    private BigDecimal mediaPonderada;

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public Long getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public String getAvaliacaoTitulo() {
        return avaliacaoTitulo;
    }

    public void setAvaliacaoTitulo(String avaliacaoTitulo) {
        this.avaliacaoTitulo = avaliacaoTitulo;
    }

    public Integer getAvaliacaoPeso() {
        return avaliacaoPeso;
    }

    public void setAvaliacaoPeso(Integer avaliacaoPeso) {
        this.avaliacaoPeso = avaliacaoPeso;
    }

    public BigDecimal getNotaValor() {
        return notaValor;
    }

    public void setNotaValor(BigDecimal notaValor) {
        this.notaValor = notaValor;
    }

    public BigDecimal getMediaPonderada() {
        return mediaPonderada;
    }

    public void setMediaPonderada(BigDecimal mediaPonderada) {
        this.mediaPonderada = mediaPonderada;
    }
}
