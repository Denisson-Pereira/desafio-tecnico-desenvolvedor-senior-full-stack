package com.denisson.backend.domain.useCases.alunos;

import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.repositories.IAlunoRepository;

public class CreateAlunoUseCase {
    private final IAlunoRepository repository;

    public CreateAlunoUseCase(IAlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno execute(Aluno aluno) {
        return repository.save(aluno);
    }
}
