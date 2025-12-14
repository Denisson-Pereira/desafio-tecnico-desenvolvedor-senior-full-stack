package com.denisson.backend.domain.useCases.turmas;

import com.denisson.backend.domain.exceptions.BadRequestException;
import com.denisson.backend.domain.exceptions.DatabaseException;
import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.repositories.ITurmaRepository;

public class CreateTurmaUseCase {
    private final ITurmaRepository repository;

    public CreateTurmaUseCase(ITurmaRepository repository) {
        this.repository = repository;
    }

    public Turma execute(Turma turma) {

        Turma existente = repository.findByNome(turma.getNome());
        if (existente != null) {
            throw new BadRequestException("Já existe uma turma com este nome.");
        }

        try {
            return repository.save(turma);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar turma", e);
        }
    }
}
