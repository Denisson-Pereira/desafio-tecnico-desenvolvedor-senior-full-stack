package com.denisson.backend.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.denisson.backend.domain.repositories.IAvaliacaoRepository;
import com.denisson.backend.domain.useCases.avaliacoes.CreateAvaliacaoUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.DeleteAvaliacaoUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.FindAllAvaliacoesUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.FindAvaliacaoByIdUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.FindAvaliacoesByDisciplinaUseCase;
import com.denisson.backend.domain.useCases.avaliacoes.UpdateAvaliacaoUseCase;


@Configuration
public class AvaliacaoConf {

    private final IAvaliacaoRepository avaliacaoInterface;

    public AvaliacaoConf(IAvaliacaoRepository avaliacaoInterface) {
        this.avaliacaoInterface = avaliacaoInterface;
    }

    @Bean
    public FindAllAvaliacoesUseCase findAllAvaliacoesUseCase() {
        return new FindAllAvaliacoesUseCase(avaliacaoInterface);
    }

    @Bean
    public FindAvaliacaoByIdUseCase findAvaliacaoByIdUseCase() {
        return new FindAvaliacaoByIdUseCase(avaliacaoInterface);
    }

    @Bean
    public FindAvaliacoesByDisciplinaUseCase findAvaliacoesByDisciplinaUseCase() {
        return new FindAvaliacoesByDisciplinaUseCase(avaliacaoInterface);
    }

    @Bean
    public CreateAvaliacaoUseCase createAvaliacaoUseCase() {
        return new CreateAvaliacaoUseCase(avaliacaoInterface);
    }

    @Bean
    public UpdateAvaliacaoUseCase updateAvaliacaoUseCase() {
        return new UpdateAvaliacaoUseCase(avaliacaoInterface);
    }

    @Bean
    public DeleteAvaliacaoUseCase deleteAvaliacaoUseCase() {
        return new DeleteAvaliacaoUseCase(avaliacaoInterface);
    }
}
