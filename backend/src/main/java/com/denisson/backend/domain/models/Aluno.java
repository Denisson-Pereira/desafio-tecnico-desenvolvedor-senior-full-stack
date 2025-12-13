package com.denisson.backend.domain.models;

public class Aluno {
    private Long id;
    private String nome;
    private Long turmaId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getTurmaId() {
        return turmaId;
    }
    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }
}
