package com.denisson.backend.domain.repositories;

import java.util.List;

import com.denisson.backend.domain.models.Disciplina;

public interface IDisciplinaRepository {
    List<Disciplina> findAll();

    Disciplina findById(Long id);

    Disciplina save(Disciplina disciplina);

    Disciplina update(Disciplina disciplina);

    void delete(Long id);

    Disciplina findByNome(String nome);
}
