package com.denisson.backend.domain.useCases.turmas;

import com.denisson.backend.domain.repositories.ITurmaRepository;

public class DeleteTurmaUseCase {

    private final ITurmaRepository repository;

    public DeleteTurmaUseCase(ITurmaRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.delete(id);
    }
}
