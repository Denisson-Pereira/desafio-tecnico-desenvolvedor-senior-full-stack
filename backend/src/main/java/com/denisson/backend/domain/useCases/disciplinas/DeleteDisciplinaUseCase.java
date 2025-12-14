package com.denisson.backend.domain.useCases.disciplinas;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.repositories.IDisciplinaRepository;

public class DeleteDisciplinaUseCase {

    private final IDisciplinaRepository repository;

    public DeleteDisciplinaUseCase(IDisciplinaRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        Disciplina existsDisciplina = repository.findById(id);
        if (existsDisciplina == null) {
            throw new NotFoundException("Disciplina não encontrada com id " + id);
        }

        repository.delete(id);
    }
}
