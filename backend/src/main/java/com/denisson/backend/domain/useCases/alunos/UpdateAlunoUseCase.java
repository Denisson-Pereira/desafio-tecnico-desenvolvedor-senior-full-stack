package com.denisson.backend.domain.useCases.alunos;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.repositories.IAlunoRepository;

public class UpdateAlunoUseCase {
    private final IAlunoRepository repository;

    public UpdateAlunoUseCase(IAlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno execute(Long id, Aluno aluno) {
        Aluno existsAluno = repository.findById(id);
        if (existsAluno == null) {
            throw new NotFoundException("Aluno não encontrado com id " + id);
        }

        aluno.setId(id);
        return repository.update(aluno);
    }
}
