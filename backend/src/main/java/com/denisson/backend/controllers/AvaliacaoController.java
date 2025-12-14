package com.denisson.backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.useCases.avaliacoes.CreateAvaliacaoUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.DeleteAvaliacaoUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.FindAllAvaliacoesUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.FindAvaliacaoByIdUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.FindAvaliacoesByDisciplinaUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.UpdateAvaliacaoUseCase;

@RestController
@RequestMapping("avaliacoes")
public class AvaliacaoController {

    private final FindAllAvaliacoesUseCase findAllAvaliacoesUseCase;
    private final FindAvaliacaoByIdUseCase findAvaliacaoByIdUseCase;
    private final FindAvaliacoesByDisciplinaUseCase findAvaliacoesByDisciplinaUseCase;
    private final CreateAvaliacaoUseCase createAvaliacaoUseCase;
    private final UpdateAvaliacaoUseCase updateAvaliacaoUseCase;
    private final DeleteAvaliacaoUseCase deleteAvaliacaoUseCase;

    public AvaliacaoController(
            FindAllAvaliacoesUseCase findAllAvaliacoesUseCase,
            FindAvaliacaoByIdUseCase findAvaliacaoByIdUseCase,
            FindAvaliacoesByDisciplinaUseCase findAvaliacoesByDisciplinaUseCase,
            CreateAvaliacaoUseCase createAvaliacaoUseCase,
            UpdateAvaliacaoUseCase updateAvaliacaoUseCase,
            DeleteAvaliacaoUseCase deleteAvaliacaoUseCase) {
        this.findAllAvaliacoesUseCase = findAllAvaliacoesUseCase;
        this.findAvaliacaoByIdUseCase = findAvaliacaoByIdUseCase;
        this.findAvaliacoesByDisciplinaUseCase = findAvaliacoesByDisciplinaUseCase;
        this.createAvaliacaoUseCase = createAvaliacaoUseCase;
        this.updateAvaliacaoUseCase = updateAvaliacaoUseCase;
        this.deleteAvaliacaoUseCase = deleteAvaliacaoUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> getAll() {
        return ResponseEntity.ok(findAllAvaliacoesUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getById(@PathVariable Long id) {
        return ResponseEntity.ok(findAvaliacaoByIdUseCase.execute(id));
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Avaliacao>> getByDisciplina(@PathVariable Long disciplinaId) {
        return ResponseEntity.ok(findAvaliacoesByDisciplinaUseCase.execute(disciplinaId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Avaliacao avaliacao) {
        try {
            Avaliacao created = createAvaliacaoUseCase.execute(avaliacao);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(Map.of("error", "Erro interno do servidor"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> update(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(updateAvaliacaoUseCase.execute(id, avaliacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteAvaliacaoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
