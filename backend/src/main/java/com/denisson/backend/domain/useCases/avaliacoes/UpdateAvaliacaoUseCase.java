package com.denisson.backend.domain.useCases.avaliacoes;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.repositories.IAvaliacaoRepository;

public class UpdateAvaliacaoUseCase {
    private final IAvaliacaoRepository repository;

    public UpdateAvaliacaoUseCase(IAvaliacaoRepository repository) {
        this.repository = repository;
    }

    public Avaliacao execute(Long id, Avaliacao avaliacao) {
        Avaliacao existsAvaliacao = repository.findById(id);

        if (existsAvaliacao == null) {
            throw new NotFoundException("Avaliacao não encontrada com id " + id);
        }
        
        avaliacao.setId(id);
        return repository.update(avaliacao);
    }
}
