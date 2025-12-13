package com.denisson.backend.domain.useCases.notas;

import java.util.List;

import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.repositories.INotaRepository;

public class FindNotasByAvaliacaoUseCase {
    private final INotaRepository repository;

    public FindNotasByAvaliacaoUseCase(INotaRepository repository) {
        this.repository = repository;
    }

    public List<Nota> execute(Long avaliacaoId) {
        return repository.findByAvaliacaoId(avaliacaoId);
    }
}
