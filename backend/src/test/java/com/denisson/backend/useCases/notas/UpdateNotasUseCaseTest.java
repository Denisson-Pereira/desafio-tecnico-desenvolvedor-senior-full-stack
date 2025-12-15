package com.denisson.backend.useCases.notas;


import com.denisson.backend.domain.dtos.UpdateNotasDTO;
import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.repositories.INotaRepository;
import com.denisson.backend.domain.useCases.notas.UpdateNotasUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateNotasUseCaseTest {

    private INotaRepository notaRepository;
    private UpdateNotasUseCase updateNotasUseCase;

    @BeforeEach
    void setUp() {
        notaRepository = mock(INotaRepository.class);
        updateNotasUseCase = new UpdateNotasUseCase(notaRepository);
    }

    @Test
    void deveSalvarNotasValidas() {
        UpdateNotasDTO.NotaDTO nota1 = new UpdateNotasDTO.NotaDTO();
        nota1.setAlunoId(1L);
        nota1.setAvaliacaoId(1L);
        nota1.setValor(BigDecimal.valueOf(8.5));

        UpdateNotasDTO.NotaDTO nota2 = new UpdateNotasDTO.NotaDTO();
        nota2.setAlunoId(2L);
        nota2.setAvaliacaoId(1L);
        nota2.setValor(BigDecimal.valueOf(7.0));

        UpdateNotasDTO dto = new UpdateNotasDTO();
        dto.setNotas(List.of(nota1, nota2));

        updateNotasUseCase.execute(dto);

        ArgumentCaptor<List<Nota>> captor = ArgumentCaptor.forClass(List.class);
        verify(notaRepository, times(1)).saveAll(captor.capture());

        List<Nota> notasSalvas = captor.getValue();
        assertEquals(2, notasSalvas.size());
        assertEquals(BigDecimal.valueOf(8.5), notasSalvas.get(0).getValor());
        assertEquals(1L, notasSalvas.get(0).getAlunoId());
    }

    @Test
    void deveLancarExceptionSeDTOForNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            updateNotasUseCase.execute(null);
        });

        assertEquals("Nenhuma nota fornecida.", exception.getMessage());
    }

    @Test
    void deveLancarExceptionSeNotaForNula() {
        UpdateNotasDTO.NotaDTO nota = new UpdateNotasDTO.NotaDTO();
        nota.setAlunoId(1L);
        nota.setAvaliacaoId(1L);
        nota.setValor(null);

        UpdateNotasDTO dto = new UpdateNotasDTO();
        dto.setNotas(List.of(nota));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            updateNotasUseCase.execute(dto);
        });

        assertTrue(exception.getMessage().contains("Nota nula"));
    }

    @Test
    void deveLancarExceptionSeNotaForaDoIntervalo() {
        UpdateNotasDTO.NotaDTO nota = new UpdateNotasDTO.NotaDTO();
        nota.setAlunoId(1L);
        nota.setAvaliacaoId(1L);
        nota.setValor(BigDecimal.valueOf(11));

        UpdateNotasDTO dto = new UpdateNotasDTO();
        dto.setNotas(List.of(nota));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            updateNotasUseCase.execute(dto);
        });

        assertTrue(exception.getMessage().contains("Nota fora do intervalo permitido"));
    }
}
