package com.denisson.backend.domain.models;

public class Avaliacao {
    private Long id;
    private String titulo;
    private Integer peso;
    private Long disciplinaId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getPeso() {
        return peso;
    }
    public void setPeso(Integer peso) {
        this.peso = peso;
    }
    public Long getDisciplinaId() {
        return disciplinaId;
    }
    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}
