package com.denisson.backend.domain.useCases.notas;

import java.util.List;

import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.repositories.INotaRepository;

public class FindNotasByAlunoUseCase {
    private final INotaRepository repository;

    public FindNotasByAlunoUseCase(INotaRepository repository) {
        this.repository = repository;
    }

    public List<Nota> execute(Long alunoId) {
        return repository.findByAlunoId(alunoId);
    }
}
