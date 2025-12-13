package com.denisson.backend.domain.useCases.alunos;

import java.util.List;

import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.repositories.IAlunoRepository;

public class FindAlunosByTurmaUseCase {
    private final IAlunoRepository repository;

    public FindAlunosByTurmaUseCase(IAlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> execute(Long turmaId) {
        return repository.findByTurmaId(turmaId);
    }
}
