package com.denisson.backend.domain.repositories;

import java.util.List;

import com.denisson.backend.domain.models.Aluno;

public interface IAlunoRepository {
    List<Aluno> findAll();

    Aluno findById(Long id);

    List<Aluno> findByTurmaId(Long turmaId);

    Aluno save(Aluno aluno);

    Aluno update(Aluno aluno);

    void delete(Long id);

    Aluno findByNome(String nome);

}
