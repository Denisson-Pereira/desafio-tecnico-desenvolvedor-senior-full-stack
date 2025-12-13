package com.denisson.backend.domain.useCases.notas;

import java.util.List;

import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.repositories.INotaRepository;

public class FindAllNotasUseCase {
    private final INotaRepository repository;

    public FindAllNotasUseCase(INotaRepository repository) {
        this.repository = repository;
    }

    public List<Nota> execute() {
        return repository.findAll();
    }
}
