package com.denisson.backend.domain.useCases.alunos;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.repositories.IAlunoRepository;

public class DeleteAlunoUseCase {
    private final IAlunoRepository repository;

    public DeleteAlunoUseCase(IAlunoRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        Aluno existsAluno = repository.findById(id);
        if (existsAluno == null) {
            throw new NotFoundException("Aluno não encontrado com id " + id);
        }
        repository.delete(id);
    }
}
