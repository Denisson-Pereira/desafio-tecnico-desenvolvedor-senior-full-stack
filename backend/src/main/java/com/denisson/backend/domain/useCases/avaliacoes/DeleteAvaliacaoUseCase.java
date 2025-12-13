package com.denisson.backend.domain.useCases.avaliacoes;

import com.denisson.backend.domain.repositories.IAvaliacaoRepository;

public class DeleteAvaliacaoUseCase {
    private final IAvaliacaoRepository repository;

    public DeleteAvaliacaoUseCase(IAvaliacaoRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.delete(id);
    }
}
