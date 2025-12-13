package com.denisson.backend.domain.useCases.turmas;

import java.util.List;

import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.repositories.ITurmaRepository;


public class FindAllTurmasUseCase {

    private final ITurmaRepository repository;

    public FindAllTurmasUseCase(ITurmaRepository repository) {
        this.repository = repository;
    }
        
    public List<Turma> execute() {
        return repository.findAll();
    }

    
}
