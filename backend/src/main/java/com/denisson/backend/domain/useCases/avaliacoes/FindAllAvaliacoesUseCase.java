package com.denisson.backend.domain.useCases.avaliacoes;

import java.util.List;

import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.repositories.IAvaliacaoRepository;

public class FindAllAvaliacoesUseCase {
    private final IAvaliacaoRepository repository;

    public FindAllAvaliacoesUseCase(IAvaliacaoRepository repository) {
        this.repository = repository;
    }

    public List<Avaliacao> execute() {
        return repository.findAll();
    }
}
