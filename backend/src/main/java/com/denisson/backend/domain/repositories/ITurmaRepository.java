package com.denisson.backend.domain.repositories;

import java.util.List;

import com.denisson.backend.domain.models.Turma;

public interface ITurmaRepository {
    List<Turma> findAll();

    Turma findById(Long id);

    Turma save(Turma turma);

    Turma update(Turma turma);

    void delete(Long id);
}
