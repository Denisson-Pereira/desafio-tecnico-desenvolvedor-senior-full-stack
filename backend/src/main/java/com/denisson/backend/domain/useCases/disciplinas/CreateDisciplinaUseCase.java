package com.denisson.backend.domain.useCases.disciplinas;

import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.repositories.IDisciplinaRepository;

public class CreateDisciplinaUseCase {
    private final IDisciplinaRepository repository;

    public CreateDisciplinaUseCase(IDisciplinaRepository repository) {
        this.repository = repository;
    }

    public Disciplina execute(Disciplina disciplina) {
        return repository.save(disciplina);
    }
}
