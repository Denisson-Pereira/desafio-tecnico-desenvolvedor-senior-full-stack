package com.denisson.backend.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.denisson.backend.domain.repositories.INotaRepository;
import com.denisson.backend.domain.useCases.notas.CreateOrUpdateNotaUseCase;
import com.denisson.backend.domain.useCases.notas.DeleteNotaUseCase;
import com.denisson.backend.domain.useCases.notas.FindAllNotasUseCase;
import com.denisson.backend.domain.useCases.notas.FindAlunosWithAvaliacoesNotasAndMediaUseCase;
import com.denisson.backend.domain.useCases.notas.FindNotasByAlunoUseCase;
import com.denisson.backend.domain.useCases.notas.FindNotasByAvaliacaoUseCase;
import com.denisson.backend.domain.useCases.notas.UpdateNotasUseCase;

@Configuration
public class NotaConf {

    private final INotaRepository notaInterface;

    public NotaConf(INotaRepository notaInterface) {
        this.notaInterface = notaInterface;
    }

    @Bean
    public FindAllNotasUseCase findAllNotasUseCase() {
        return new FindAllNotasUseCase(notaInterface);
    }

    @Bean
    public FindNotasByAlunoUseCase findNotasByAlunoUseCase() {
        return new FindNotasByAlunoUseCase(notaInterface);
    }

    @Bean
    public FindNotasByAvaliacaoUseCase findNotasByAvaliacaoUseCase() {
        return new FindNotasByAvaliacaoUseCase(notaInterface);
    }

    @Bean
    public CreateOrUpdateNotaUseCase createOrUpdateNotaUseCase() {
        return new CreateOrUpdateNotaUseCase(notaInterface);
    }

    @Bean
    public DeleteNotaUseCase deleteNotaUseCase() {
        return new DeleteNotaUseCase(notaInterface);
    }

    @Bean
    public FindAlunosWithAvaliacoesNotasAndMediaUseCase findAlunosWithAvaliacoesNotasAndMediaUseCase() {
        return new FindAlunosWithAvaliacoesNotasAndMediaUseCase(notaInterface);
    }

    @Bean
    public UpdateNotasUseCase updateNotasUseCase() {
        return new UpdateNotasUseCase(notaInterface);
    }
}
