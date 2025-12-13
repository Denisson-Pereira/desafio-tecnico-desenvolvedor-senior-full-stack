package com.denisson.backend.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.useCases.alunos.CreateAlunoUseCase;
import com.denisson.backend.domain.useCases.alunos.DeleteAlunoUseCase;
import com.denisson.backend.domain.useCases.alunos.FindAllAlunosUseCase;
import com.denisson.backend.domain.useCases.alunos.FindAlunoByIdUseCase;
import com.denisson.backend.domain.useCases.alunos.FindAlunosByTurmaUseCase;
import com.denisson.backend.domain.useCases.alunos.UpdateAlunoUseCase;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    private final FindAllAlunosUseCase findAllAlunosUseCase;
    private final FindAlunoByIdUseCase findAlunoByIdUseCase;
    private final FindAlunosByTurmaUseCase findAlunosByTurmaUseCase;
    private final CreateAlunoUseCase createAlunoUseCase;
    private final UpdateAlunoUseCase updateAlunoUseCase;
    private final DeleteAlunoUseCase deleteAlunoUseCase;

    public AlunoController(
            FindAllAlunosUseCase findAllAlunosUseCase,
            FindAlunoByIdUseCase findAlunoByIdUseCase,
            FindAlunosByTurmaUseCase findAlunosByTurmaUseCase,
            CreateAlunoUseCase createAlunoUseCase,
            UpdateAlunoUseCase updateAlunoUseCase,
            DeleteAlunoUseCase deleteAlunoUseCase) {
        this.findAllAlunosUseCase = findAllAlunosUseCase;
        this.findAlunoByIdUseCase = findAlunoByIdUseCase;
        this.findAlunosByTurmaUseCase = findAlunosByTurmaUseCase;
        this.createAlunoUseCase = createAlunoUseCase;
        this.updateAlunoUseCase = updateAlunoUseCase;
        this.deleteAlunoUseCase = deleteAlunoUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        return ResponseEntity.ok(findAllAlunosUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) {
        return ResponseEntity.ok(findAlunoByIdUseCase.execute(id));
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<Aluno>> getByTurma(@PathVariable Long turmaId) {
        return ResponseEntity.ok(findAlunosByTurmaUseCase.execute(turmaId));
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        return ResponseEntity.ok(createAlunoUseCase.execute(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno) {
        return ResponseEntity.ok(updateAlunoUseCase.execute(id, aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteAlunoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
