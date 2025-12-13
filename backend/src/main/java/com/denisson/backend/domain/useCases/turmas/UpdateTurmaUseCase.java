package com.denisson.backend.domain.useCases.turmas;

import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.repositories.ITurmaRepository;

public class UpdateTurmaUseCase {

    private final ITurmaRepository repository;

    public UpdateTurmaUseCase(ITurmaRepository repository) {
        this.repository = repository;
    }

    public Turma execute(Long id, Turma turma) {
        turma.setId(id);
        return repository.update(turma);
    }
}
