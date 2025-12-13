package com.denisson.backend.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.useCases.disciplinas.CreateDisciplinaUseCase;
import com.denisson.backend.domain.useCases.disciplinas.DeleteDisciplinaUseCase;
import com.denisson.backend.domain.useCases.disciplinas.FindAllDisciplinasUseCase;
import com.denisson.backend.domain.useCases.disciplinas.FindDisciplinaByIdUseCase;
import com.denisson.backend.domain.useCases.disciplinas.UpdateDisciplinaUseCase;

@RestController
@RequestMapping("disciplinas")
public class DisciplinaController {

    private final FindAllDisciplinasUseCase findAllDisciplinasUseCase;
    private final FindDisciplinaByIdUseCase findDisciplinaByIdUseCase;
    private final CreateDisciplinaUseCase createDisciplinaUseCase;
    private final UpdateDisciplinaUseCase updateDisciplinaUseCase;
    private final DeleteDisciplinaUseCase deleteDisciplinaUseCase;

    public DisciplinaController(
            FindAllDisciplinasUseCase findAllDisciplinasUseCase,
            FindDisciplinaByIdUseCase findDisciplinaByIdUseCase,
            CreateDisciplinaUseCase createDisciplinaUseCase,
            UpdateDisciplinaUseCase updateDisciplinaUseCase,
            DeleteDisciplinaUseCase deleteDisciplinaUseCase) {
        this.findAllDisciplinasUseCase = findAllDisciplinasUseCase;
        this.findDisciplinaByIdUseCase = findDisciplinaByIdUseCase;
        this.createDisciplinaUseCase = createDisciplinaUseCase;
        this.updateDisciplinaUseCase = updateDisciplinaUseCase;
        this.deleteDisciplinaUseCase = deleteDisciplinaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> getAll() {
        return ResponseEntity.ok(findAllDisciplinasUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getById(@PathVariable Long id) {
        return ResponseEntity.ok(findDisciplinaByIdUseCase.execute(id));
    }

    @PostMapping
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina) {
        return ResponseEntity.ok(createDisciplinaUseCase.execute(disciplina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        return ResponseEntity.ok(updateDisciplinaUseCase.execute(id, disciplina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteDisciplinaUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
