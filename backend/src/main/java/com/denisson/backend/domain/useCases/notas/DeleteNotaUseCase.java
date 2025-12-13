package com.denisson.backend.domain.useCases.notas;

import com.denisson.backend.domain.repositories.INotaRepository;

public class DeleteNotaUseCase {
    private final INotaRepository repository;

    public DeleteNotaUseCase(INotaRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.delete(id);
    }

    public void executeByAlunoAndAvaliacao(Long alunoId, Long avaliacaoId) {
        repository.deleteByAlunoIdAndAvaliacaoId(alunoId, avaliacaoId);
    }
}
