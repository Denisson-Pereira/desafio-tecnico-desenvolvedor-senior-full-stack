package com.denisson.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denisson.backend.domain.dtos.AlunoAvaliacaoNotaDTO;
import com.denisson.backend.domain.useCases.notas.FindAlunosWithAvaliacoesNotasAndMediaUseCase;

@RestController
@RequestMapping("turmas/{turmaId}/disciplinas/{disciplinaId}/notas-com-media")
public class AlunoAvaliacaoNotaMediaController {

    private final FindAlunosWithAvaliacoesNotasAndMediaUseCase useCase;

    public AlunoAvaliacaoNotaMediaController(FindAlunosWithAvaliacoesNotasAndMediaUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<AlunoAvaliacaoNotaDTO>> getAlunosWithNotasEMedia(
            @PathVariable Long turmaId,
            @PathVariable Long disciplinaId) {
        return ResponseEntity.ok(useCase.execute(turmaId, disciplinaId));
    }
}
