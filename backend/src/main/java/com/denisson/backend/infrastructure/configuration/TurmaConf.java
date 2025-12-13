package com.denisson.backend.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.denisson.backend.domain.repositories.ITurmaRepository;
import com.denisson.backend.domain.useCases.turmas.CreateTurmaUseCase;
import com.denisson.backend.domain.useCases.turmas.DeleteTurmaUseCase;
import com.denisson.backend.domain.useCases.turmas.FindAllTurmasUseCase;
import com.denisson.backend.domain.useCases.turmas.FindTurmaByIdUseCase;
import com.denisson.backend.domain.useCases.turmas.UpdateTurmaUseCase;



@Configuration
public class TurmaConf {

    private final ITurmaRepository turmaInterface;

    public TurmaConf(ITurmaRepository turmaInterface) {
        this.turmaInterface = turmaInterface;
    }

    @Bean
    public FindAllTurmasUseCase findAllTurmasUseCase() {
        return new FindAllTurmasUseCase(turmaInterface);
    }

    @Bean
    public FindTurmaByIdUseCase findTurmaByIdUseCase() {
        return new FindTurmaByIdUseCase(turmaInterface);
    }

    @Bean
    public CreateTurmaUseCase createTurmaUseCase() {
        return new CreateTurmaUseCase(turmaInterface);
    }

    @Bean
    public UpdateTurmaUseCase updateTurmaUseCase() {
        return new UpdateTurmaUseCase(turmaInterface);
    }

    @Bean
    public DeleteTurmaUseCase deleteTurmaUseCase() {
        return new DeleteTurmaUseCase(turmaInterface);
    }

}
