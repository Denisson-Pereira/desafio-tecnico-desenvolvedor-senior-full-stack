package com.denisson.backend.domain.useCases.avaliacoes;

import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.repositories.IAvaliacaoRepository;

public class FindAvaliacaoByIdUseCase {
    private final IAvaliacaoRepository repository;

    public FindAvaliacaoByIdUseCase(IAvaliacaoRepository repository) {
        this.repository = repository;
    }

    public Avaliacao execute(Long id) {
        return repository.findById(id);
    }
}
