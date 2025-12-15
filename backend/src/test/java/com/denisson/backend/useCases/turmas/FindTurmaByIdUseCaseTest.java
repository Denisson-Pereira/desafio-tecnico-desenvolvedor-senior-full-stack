package com.denisson.backend.useCases.turmas;

import com.denisson.backend.domain.exceptions.NotFoundException;
import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.repositories.ITurmaRepository;
import com.denisson.backend.domain.useCases.turmas.FindTurmaByIdUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindTurmaByIdUseCaseTest {

    private ITurmaRepository repository;
    private FindTurmaByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(ITurmaRepository.class);
        useCase = new FindTurmaByIdUseCase(repository);
    }

    @Test
    void deveRetornarTurmaQuandoExistir() {
        Turma turma = new Turma();
        turma.setId(1L);
        turma.setNome("Turma A");

        when(repository.findById(1L)).thenReturn(turma);

        Turma resultado = useCase.execute(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Turma A", resultado.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarNotFoundExceptionQuandoTurmaNaoExistir() {
        when(repository.findById(2L)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            useCase.execute(2L);
        });

        assertEquals("Turma não encontrada com id 2", exception.getMessage());
        verify(repository, times(1)).findById(2L);
    }
}
