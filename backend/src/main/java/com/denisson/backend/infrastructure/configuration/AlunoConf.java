package com.denisson.backend.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.denisson.backend.domain.repositories.IAlunoRepository;
import com.denisson.backend.domain.useCases.alunos.CreateAlunoUseCase;
import com.denisson.backend.domain.useCases.alunos.DeleteAlunoUseCase;
import com.denisson.backend.domain.useCases.alunos.FindAllAlunosUseCase;
import com.denisson.backend.domain.useCases.alunos.FindAlunoByIdUseCase;
import com.denisson.backend.domain.useCases.alunos.FindAlunosByTurmaUseCase;
import com.denisson.backend.domain.useCases.alunos.UpdateAlunoUseCase;

@Configuration
public class AlunoConf {

    private final IAlunoRepository alunoInterface;

    public AlunoConf(IAlunoRepository alunoInterface) {
        this.alunoInterface = alunoInterface;
    }

    @Bean
    public FindAllAlunosUseCase findAllAlunosUseCase() {
        return new FindAllAlunosUseCase(alunoInterface);
    }

    @Bean
    public FindAlunoByIdUseCase findAlunoByIdUseCase() {
        return new FindAlunoByIdUseCase(alunoInterface);
    }

    @Bean
    public FindAlunosByTurmaUseCase findAlunosByTurmaUseCase() {
        return new FindAlunosByTurmaUseCase(alunoInterface);
    }

    @Bean
    public CreateAlunoUseCase createAlunoUseCase() {
        return new CreateAlunoUseCase(alunoInterface);
    }

    @Bean
    public UpdateAlunoUseCase updateAlunoUseCase() {
        return new UpdateAlunoUseCase(alunoInterface);
    }

    @Bean
    public DeleteAlunoUseCase deleteAlunoUseCase() {
        return new DeleteAlunoUseCase(alunoInterface);
    }
}
