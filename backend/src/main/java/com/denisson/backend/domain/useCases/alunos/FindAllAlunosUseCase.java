package com.denisson.backend.domain.useCases.alunos;

import java.util.List;

import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.repositories.IAlunoRepository;


public class FindAllAlunosUseCase {
    private final IAlunoRepository repository;

    public FindAllAlunosUseCase(IAlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> execute() {
        return repository.findAll();
    }
}
