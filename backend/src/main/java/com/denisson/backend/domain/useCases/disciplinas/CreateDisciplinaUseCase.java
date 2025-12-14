package com.denisson.backend.domain.useCases.disciplinas;

import com.denisson.backend.domain.exceptions.BadRequestException;
import com.denisson.backend.domain.exceptions.DatabaseException;
import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.repositories.IDisciplinaRepository;

public class CreateDisciplinaUseCase {
    private final IDisciplinaRepository repository;

    public CreateDisciplinaUseCase(IDisciplinaRepository repository) {
        this.repository = repository;
    }

    public Disciplina execute(Disciplina disciplina) {
        Disciplina existente = repository.findByNome(disciplina.getNome());

        if (existente != null) {
            throw new BadRequestException("Já existe uma Disciplina com este nome.");
        }

        try {
            return repository.save(disciplina);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar disciplina", e);
        }
    }
}
