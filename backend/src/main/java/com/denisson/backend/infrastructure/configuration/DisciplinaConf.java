package com.denisson.backend.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.denisson.backend.domain.repositories.IDisciplinaRepository;
import com.denisson.backend.domain.useCases.disciplinas.CreateDisciplinaUseCase;
import com.denisson.backend.domain.useCases.disciplinas.DeleteDisciplinaUseCase;
import com.denisson.backend.domain.useCases.disciplinas.FindAllDisciplinasUseCase;
import com.denisson.backend.domain.useCases.disciplinas.FindDisciplinaByIdUseCase;
import com.denisson.backend.domain.useCases.disciplinas.UpdateDisciplinaUseCase;

@Configuration
public class DisciplinaConf {

    private final IDisciplinaRepository disciplinaInterface;

    public DisciplinaConf(IDisciplinaRepository disciplinaInterface) {
        this.disciplinaInterface = disciplinaInterface;
    }

    @Bean
    public FindAllDisciplinasUseCase findAllDisciplinasUseCase() {
        return new FindAllDisciplinasUseCase(disciplinaInterface);
    }

    @Bean
    public FindDisciplinaByIdUseCase findDisciplinaByIdUseCase() {
        return new FindDisciplinaByIdUseCase(disciplinaInterface);
    }

    @Bean
    public CreateDisciplinaUseCase createDisciplinaUseCase() {
        return new CreateDisciplinaUseCase(disciplinaInterface);
    }

    @Bean
    public UpdateDisciplinaUseCase updateDisciplinaUseCase() {
        return new UpdateDisciplinaUseCase(disciplinaInterface);
    }

    @Bean
    public DeleteDisciplinaUseCase deleteDisciplinaUseCase() {
        return new DeleteDisciplinaUseCase(disciplinaInterface);
    }
}
