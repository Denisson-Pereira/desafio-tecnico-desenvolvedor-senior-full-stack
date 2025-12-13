package com.denisson.backend.domain.repositories;

import java.util.List;

import com.denisson.backend.domain.models.Avaliacao;

public interface IAvaliacaoRepository {
    List<Avaliacao> findAll();

    Avaliacao findById(Long id);

    List<Avaliacao> findByDisciplinaId(Long disciplinaId);

    Avaliacao save(Avaliacao avaliacao);

    Avaliacao update(Avaliacao avaliacao);

    void delete(Long id);
}
