package com.denisson.backend.domain.useCases.notas;

import java.util.List;

import com.denisson.backend.domain.dtos.AlunoAvaliacaoNotaDTO;
import com.denisson.backend.domain.repositories.INotaRepository;

public class FindAlunosWithAvaliacoesNotasAndMediaUseCase {

    private final INotaRepository repository;

    public FindAlunosWithAvaliacoesNotasAndMediaUseCase(INotaRepository repository) {
        this.repository = repository;
    }

    public List<AlunoAvaliacaoNotaDTO> execute(Long turmaId, Long disciplinaId) {
        return repository.findAlunosWithAvaliacoesNotasAndMedia(turmaId, disciplinaId);
    }
}
