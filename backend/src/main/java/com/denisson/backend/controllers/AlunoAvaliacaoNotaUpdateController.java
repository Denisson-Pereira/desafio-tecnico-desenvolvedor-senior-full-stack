package com.denisson.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denisson.backend.domain.dtos.UpdateNotasDTO;
import com.denisson.backend.domain.useCases.notas.UpdateNotasUseCase;


@RestController
@RequestMapping("turmas/{turmaId}/disciplinas/{disciplinaId}/notas")
public class AlunoAvaliacaoNotaUpdateController {

    private final UpdateNotasUseCase updateNotasUseCase;

    public AlunoAvaliacaoNotaUpdateController(UpdateNotasUseCase updateNotasUseCase) {
        this.updateNotasUseCase = updateNotasUseCase;
    }

    @PatchMapping
    public ResponseEntity<Void> updateNotas(
            @PathVariable Long turmaId,
            @PathVariable Long disciplinaId,
            @RequestBody UpdateNotasDTO updateNotasDTO
    ) {
        updateNotasUseCase.execute(updateNotasDTO); 
        return ResponseEntity.ok().build();
    }
}