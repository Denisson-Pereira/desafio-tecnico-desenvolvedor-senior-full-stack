package com.denisson.backend.domain.useCases.avaliacoes;

import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.repositories.IAvaliacaoRepository;

public class CreateAvaliacaoUseCase {
    private final IAvaliacaoRepository repository;

    public CreateAvaliacaoUseCase(IAvaliacaoRepository repository) {
        this.repository = repository;
    }

    public Avaliacao execute(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }
}
