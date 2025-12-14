package com.denisson.backend.domain.useCases.avaliacoes;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.repositories.IAvaliacaoRepository;

public class FindAvaliacaoByIdUseCase {
    private final IAvaliacaoRepository repository;

    public FindAvaliacaoByIdUseCase(IAvaliacaoRepository repository) {
        this.repository = repository;
    }

    public Avaliacao execute(Long id) {
        Avaliacao avaliacao = repository.findById(id);

        if (avaliacao == null) {
            throw new NotFoundException("Avaliacao não encontrada com id " + id);
        }
        return avaliacao;
    }
}
