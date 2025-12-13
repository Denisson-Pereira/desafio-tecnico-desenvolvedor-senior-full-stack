package com.denisson.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.denisson.backend.domain.dtos.UpdateNotasDTO;
import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.useCases.notas.CreateOrUpdateNotaUseCase;
import com.denisson.backend.domain.useCases.notas.DeleteNotaUseCase;
import com.denisson.backend.domain.useCases.notas.FindAllNotasUseCase;
import com.denisson.backend.domain.useCases.notas.FindNotasByAlunoUseCase;
import com.denisson.backend.domain.useCases.notas.FindNotasByAvaliacaoUseCase;
import com.denisson.backend.domain.useCases.notas.UpdateNotasUseCase;

@RestController
@RequestMapping("notas")
public class NotaController {

    private final FindAllNotasUseCase findAllNotasUseCase;
    private final FindNotasByAlunoUseCase findNotasByAlunoUseCase;
    private final FindNotasByAvaliacaoUseCase findNotasByAvaliacaoUseCase;
    private final CreateOrUpdateNotaUseCase createOrUpdateNotaUseCase;
    private final DeleteNotaUseCase deleteNotaUseCase;
    private final UpdateNotasUseCase updateNotasUseCase;

    public NotaController(
            FindAllNotasUseCase findAllNotasUseCase,
            FindNotasByAlunoUseCase findNotasByAlunoUseCase,
            FindNotasByAvaliacaoUseCase findNotasByAvaliacaoUseCase,
            CreateOrUpdateNotaUseCase createOrUpdateNotaUseCase,
            DeleteNotaUseCase deleteNotaUseCase) {
        this.findAllNotasUseCase = findAllNotasUseCase;
        this.findNotasByAlunoUseCase = findNotasByAlunoUseCase;
        this.findNotasByAvaliacaoUseCase = findNotasByAvaliacaoUseCase;
        this.createOrUpdateNotaUseCase = createOrUpdateNotaUseCase;
        this.deleteNotaUseCase = deleteNotaUseCase;
        this.updateNotasUseCase = null;
    }

    @GetMapping
    public ResponseEntity<List<Nota>> getAll() {
        return ResponseEntity.ok(findAllNotasUseCase.execute());
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Nota>> getByAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(findNotasByAlunoUseCase.execute(alunoId));
    }

    @GetMapping("/avaliacao/{avaliacaoId}")
    public ResponseEntity<List<Nota>> getByAvaliacao(@PathVariable Long avaliacaoId) {
        return ResponseEntity.ok(findNotasByAvaliacaoUseCase.execute(avaliacaoId));
    }

    @PostMapping
    public ResponseEntity<Nota> createOrUpdate(@RequestBody Nota nota) {
        return ResponseEntity.ok(createOrUpdateNotaUseCase.execute(nota));
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> createOrUpdateBatch(@RequestBody List<Nota> notas) {
        createOrUpdateNotaUseCase.saveBatch(notas);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteNotaUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/aluno/{alunoId}/avaliacao/{avaliacaoId}")
    public ResponseEntity<Void> deleteByAlunoAndAvaliacao(@PathVariable Long alunoId, @PathVariable Long avaliacaoId) {
        deleteNotaUseCase.executeByAlunoAndAvaliacao(alunoId, avaliacaoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateNotas(
            @PathVariable Long turmaId,
            @PathVariable Long disciplinaId,
            @RequestBody UpdateNotasDTO updateNotasDTO) {
        updateNotasUseCase.execute(updateNotasDTO);
        return ResponseEntity.ok().build();
    }
}
