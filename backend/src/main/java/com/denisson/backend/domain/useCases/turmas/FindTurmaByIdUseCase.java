package com.denisson.backend.domain.useCases.turmas;

import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.repositories.ITurmaRepository;

public class FindTurmaByIdUseCase {

    private final ITurmaRepository repository;

    public FindTurmaByIdUseCase(ITurmaRepository repository) {
        this.repository = repository;
    }

    public Turma execute(Long id) {
        return repository.findById(id);
    }
}
