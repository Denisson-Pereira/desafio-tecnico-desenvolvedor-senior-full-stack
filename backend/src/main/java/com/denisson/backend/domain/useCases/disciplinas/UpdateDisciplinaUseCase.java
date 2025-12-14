package com.denisson.backend.domain.useCases.disciplinas;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.repositories.IDisciplinaRepository;

public class UpdateDisciplinaUseCase {

    private final IDisciplinaRepository repository;

    public UpdateDisciplinaUseCase(IDisciplinaRepository repository) {
        this.repository = repository;
    }

    public Disciplina execute(Long id, Disciplina disciplina) {
        Disciplina existsDisciplina = repository.findById(id);

        if (existsDisciplina == null) {
            throw new NotFoundException("Disciplina não encontrada com id " + id);
        }
        
        disciplina.setId(id);
        return repository.update(disciplina);
    }
}
