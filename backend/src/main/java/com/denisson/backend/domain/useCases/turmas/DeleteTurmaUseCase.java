package com.denisson.backend.domain.useCases.turmas;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.repositories.ITurmaRepository;

public class DeleteTurmaUseCase {

    private final ITurmaRepository repository;

    public DeleteTurmaUseCase(ITurmaRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        Turma existsTurma = repository.findById(id);
        if (existsTurma == null) {
            throw new NotFoundException("Turma não encontrada com id " + id);
        }
        repository.delete(id);
    }
}
