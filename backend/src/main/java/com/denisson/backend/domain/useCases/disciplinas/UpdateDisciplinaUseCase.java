package com.denisson.backend.domain.useCases.disciplinas;

import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.repositories.IDisciplinaRepository;

public class UpdateDisciplinaUseCase {

    private final IDisciplinaRepository repository;

    public UpdateDisciplinaUseCase(IDisciplinaRepository repository) {
        this.repository = repository;
    }

    public Disciplina execute(Long id, Disciplina disciplina) {
        disciplina.setId(id);
        return repository.update(disciplina);
    }
}
