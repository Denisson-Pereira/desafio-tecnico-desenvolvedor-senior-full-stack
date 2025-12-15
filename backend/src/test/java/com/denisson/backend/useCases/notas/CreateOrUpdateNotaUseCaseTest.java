package com.denisson.backend.useCases.notas;

import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.repositories.INotaRepository;
import com.denisson.backend.domain.useCases.notas.CreateOrUpdateNotaUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateOrUpdateNotaUseCaseTest {

    private INotaRepository repository;
    private CreateOrUpdateNotaUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(INotaRepository.class);
        useCase = new CreateOrUpdateNotaUseCase(repository);
    }

    @Test
    void deveSalvarNotaNova() {
        Nota nota = new Nota();
        nota.setAlunoId(1L);
        nota.setAvaliacaoId(1L);
        nota.setValor(BigDecimal.valueOf(8));

        when(repository.findByAlunoId(1L)).thenReturn(List.of());
        when(repository.save(nota)).thenReturn(nota);

        Nota resultado = useCase.execute(nota);

        verify(repository, times(1)).save(nota);
        verify(repository, never()).update(nota);

        assertEquals(BigDecimal.valueOf(8), resultado.getValor());
    }

    @Test
    void deveAtualizarNotaExistente() {
        Nota nota = new Nota();
        nota.setAlunoId(1L);
        nota.setAvaliacaoId(1L);
        nota.setValor(BigDecimal.valueOf(9));

        Nota existente = new Nota();
        existente.setAlunoId(1L);
        existente.setAvaliacaoId(1L);
        existente.setValor(BigDecimal.valueOf(7));

        when(repository.findByAlunoId(1L)).thenReturn(List.of(existente));
        when(repository.update(nota)).thenReturn(nota);

        Nota resultado = useCase.execute(nota);

        verify(repository, times(1)).update(nota);
        verify(repository, never()).save(nota);

        assertEquals(BigDecimal.valueOf(9), resultado.getValor());
    }

    @Test
    void deveLancarExceptionSeNotaForaDoIntervalo() {
        Nota nota = new Nota();
        nota.setAlunoId(1L);
        nota.setAvaliacaoId(1L);
        nota.setValor(BigDecimal.valueOf(11));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(nota);
        });

        assertEquals("O valor da nota deve estar entre 0 e 10", exception.getMessage());
    }

    @Test
    void deveSalvarNotasEmBatch() {
        Nota nota1 = new Nota();
        nota1.setAlunoId(1L);
        nota1.setAvaliacaoId(1L);
        nota1.setValor(BigDecimal.valueOf(5));

        Nota nota2 = new Nota();
        nota2.setAlunoId(2L);
        nota2.setAvaliacaoId(2L);
        nota2.setValor(BigDecimal.valueOf(7));

        when(repository.findByAlunoId(1L)).thenReturn(List.of());
        when(repository.findByAlunoId(2L)).thenReturn(List.of());

        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        useCase.saveBatch(List.of(nota1, nota2));

        verify(repository, times(1)).save(nota1);
        verify(repository, times(1)).save(nota2);
    }
}
