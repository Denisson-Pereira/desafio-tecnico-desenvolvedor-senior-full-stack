package com.denisson.backend.domain.useCases.notas;

import java.math.BigDecimal;
import java.util.List;

import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.repositories.INotaRepository;

public class CreateOrUpdateNotaUseCase {
    private final INotaRepository repository;

    public CreateOrUpdateNotaUseCase(INotaRepository repository) {
        this.repository = repository;
    }

    public Nota execute(Nota nota) {
        if (nota.getValor() == null || nota.getValor().compareTo(BigDecimal.ZERO) < 0
                || nota.getValor().compareTo(BigDecimal.TEN) > 0) {
            throw new IllegalArgumentException("O valor da nota deve estar entre 0 e 10");
        }

        List<Nota> existentes = repository.findByAlunoId(nota.getAlunoId());
        boolean existe = existentes.stream()
                .anyMatch(n -> n.getAvaliacaoId().equals(nota.getAvaliacaoId()));
        if (existe) {
            return repository.update(nota);
        } else {
            return repository.save(nota);
        }
    }

    public void saveBatch(List<Nota> notas) {
        for (Nota nota : notas) {
            execute(nota);
        }
    }
}
