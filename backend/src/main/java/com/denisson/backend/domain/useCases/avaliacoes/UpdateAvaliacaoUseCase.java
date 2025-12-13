package com.denisson.backend.domain.useCases.avaliacoes;

import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.repositories.IAvaliacaoRepository;

public class UpdateAvaliacaoUseCase {
    private final IAvaliacaoRepository repository;

    public UpdateAvaliacaoUseCase(IAvaliacaoRepository repository) {
        this.repository = repository;
    }

    public Avaliacao execute(Long id, Avaliacao avaliacao) {
        avaliacao.setId(id);
        return repository.update(avaliacao);
    }
}
