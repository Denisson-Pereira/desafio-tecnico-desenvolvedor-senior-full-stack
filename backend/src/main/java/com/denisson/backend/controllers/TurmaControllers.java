package com.denisson.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.useCases.turmas.CreateTurmaUseCase;
import com.denisson.backend.domain.useCases.turmas.DeleteTurmaUseCase;
import com.denisson.backend.domain.useCases.turmas.FindAllTurmasUseCase;
import com.denisson.backend.domain.useCases.turmas.FindTurmaByIdUseCase;
import com.denisson.backend.domain.useCases.turmas.UpdateTurmaUseCase;

@RestController
@RequestMapping("turmas")
public class TurmaControllers {

    private final FindAllTurmasUseCase findAllTurmasUseCase;
    private final CreateTurmaUseCase createTurmaUseCase;
    private final DeleteTurmaUseCase deleteTurmaUseCase;
    private final FindTurmaByIdUseCase findTurmaByIdUseCase;
    private final UpdateTurmaUseCase updateTurmaUseCase;

    public TurmaControllers(FindAllTurmasUseCase findAllTurmasUseCase, CreateTurmaUseCase createTurmaUseCase,
            DeleteTurmaUseCase deleteTurmaUseCase, FindTurmaByIdUseCase findTurmaByIdUseCase,
            UpdateTurmaUseCase updateTurmaUseCase) {
        this.findAllTurmasUseCase = findAllTurmasUseCase;
        this.createTurmaUseCase = createTurmaUseCase;
        this.deleteTurmaUseCase = deleteTurmaUseCase;
        this.findTurmaByIdUseCase = findTurmaByIdUseCase;
        this.updateTurmaUseCase = updateTurmaUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<Turma>> getAll() {
        return ResponseEntity.ok(findAllTurmasUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> getById(@PathVariable Long id) {
        Turma turma = findTurmaByIdUseCase.execute(id);
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> create(@RequestBody Turma turma) {
        return ResponseEntity.ok(createTurmaUseCase.execute(turma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Long id, @RequestBody Turma turma) {
        return ResponseEntity.ok(updateTurmaUseCase.execute(id, turma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteTurmaUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
