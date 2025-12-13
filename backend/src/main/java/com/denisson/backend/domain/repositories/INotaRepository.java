package com.denisson.backend.domain.repositories;

import java.util.List;

import com.denisson.backend.domain.dtos.AlunoAvaliacaoNotaDTO;
import com.denisson.backend.domain.models.Nota;

public interface INotaRepository {
    List<Nota> findAll();

    Nota findById(Long id);

    List<Nota> findByAlunoId(Long alunoId);

    List<Nota> findByAvaliacaoId(Long avaliacaoId);

    Nota save(Nota nota);

    Nota update(Nota nota);

    void delete(Long id);

    void deleteByAlunoIdAndAvaliacaoId(Long alunoId, Long avaliacaoId);

    List<AlunoAvaliacaoNotaDTO> findAlunosWithAvaliacoesNotasAndMedia(Long turmaId, Long disciplinaId);
}
