package com.denisson.backend.domain.useCases.alunos;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.repositories.IAlunoRepository;

public class FindAlunoByIdUseCase {
    private final IAlunoRepository repository;

    public FindAlunoByIdUseCase(IAlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno execute(Long id) {
        Aluno aluno = repository.findById(id);

        if (aluno == null) {
            throw new NotFoundException("Aluno não encontrado com id " + id);
        }
        return aluno;
    }
}
