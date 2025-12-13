package com.denisson.backend.domain.useCases.disciplinas;

import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.repositories.IDisciplinaRepository;

public class FindDisciplinaByIdUseCase {

    private final IDisciplinaRepository repository;

    public FindDisciplinaByIdUseCase(IDisciplinaRepository repository) {
        this.repository = repository;
    }

    public Disciplina execute(Long id) {
        return repository.findById(id);
    }
}
