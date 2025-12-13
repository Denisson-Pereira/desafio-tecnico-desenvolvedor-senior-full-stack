package com.denisson.backend.domain.useCases.avaliacoes;

import java.util.List;

import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.repositories.IAvaliacaoRepository;


public class FindAvaliacoesByDisciplinaUseCase {
    private final IAvaliacaoRepository repository;

    public FindAvaliacoesByDisciplinaUseCase(IAvaliacaoRepository repository) {
        this.repository = repository;
    }

    public List<Avaliacao> execute(Long disciplinaId) {
        return repository.findByDisciplinaId(disciplinaId);
    }
}
