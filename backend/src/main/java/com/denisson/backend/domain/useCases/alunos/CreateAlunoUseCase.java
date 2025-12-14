package com.denisson.backend.domain.useCases.alunos;

import com.denisson.backend.domain.exceptions.BadRequestException;
import com.denisson.backend.domain.exceptions.DatabaseException;
import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.repositories.IAlunoRepository;

public class CreateAlunoUseCase {
    private final IAlunoRepository repository;

    public CreateAlunoUseCase(IAlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno execute(Aluno aluno) {
        Aluno existente = repository.findByNome(aluno.getNome());

        if (existente != null) {
            throw new BadRequestException("Já existe um aluno com este nome.");
        }

        try {
            return repository.save(aluno);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar aluno", e);
        }
    }
}
