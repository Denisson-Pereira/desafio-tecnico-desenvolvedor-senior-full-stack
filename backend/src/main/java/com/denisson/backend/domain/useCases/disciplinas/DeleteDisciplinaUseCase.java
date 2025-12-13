package com.denisson.backend.domain.useCases.disciplinas;

import com.denisson.backend.domain.repositories.IDisciplinaRepository;

public class DeleteDisciplinaUseCase {

    private final IDisciplinaRepository repository;

    public DeleteDisciplinaUseCase(IDisciplinaRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.delete(id);
    }
}
