package com.denisson.backend.domain.useCases.disciplinas;

import java.util.List;

import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.repositories.IDisciplinaRepository;


public class FindAllDisciplinasUseCase {

    private final IDisciplinaRepository repository;

    public FindAllDisciplinasUseCase(IDisciplinaRepository repository) {
        this.repository = repository;
    }

    public List<Disciplina> execute() {
        return repository.findAll();
    }
}
