package com.denisson.backend.domain.useCases.alunos;

import com.denisson.backend.domain.repositories.IAlunoRepository;

public class DeleteAlunoUseCase {
    private final IAlunoRepository repository;

    public DeleteAlunoUseCase(IAlunoRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.delete(id);
    }
}
