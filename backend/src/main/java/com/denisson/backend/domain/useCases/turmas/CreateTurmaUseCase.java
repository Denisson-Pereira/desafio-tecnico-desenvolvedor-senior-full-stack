package com.denisson.backend.domain.useCases.turmas;

import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.repositories.ITurmaRepository;

public class CreateTurmaUseCase {
    private final ITurmaRepository repository;

    public CreateTurmaUseCase(ITurmaRepository repository) {
        this.repository = repository;
    }

    public Turma execute(Turma turma) {
        return repository.save(turma);
    }
}
