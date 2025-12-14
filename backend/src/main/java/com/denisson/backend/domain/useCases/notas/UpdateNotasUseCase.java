package com.denisson.backend.domain.useCases.notas;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.denisson.backend.domain.dtos.UpdateNotasDTO;
import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.repositories.INotaRepository;

public class UpdateNotasUseCase {

    private final INotaRepository notaRepository;

    public UpdateNotasUseCase(INotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public void execute(UpdateNotasDTO updateNotasDTO) {
        if (updateNotasDTO == null || updateNotasDTO.getNotas() == null) {
            throw new IllegalArgumentException("Nenhuma nota fornecida.");
        }

        List<Nota> notas = updateNotasDTO.getNotas().stream().map(dto -> {
            if (dto.getValor() == null) {
                throw new IllegalArgumentException(
                        "Nota nula para alunoId=" + dto.getAlunoId() + ", avaliacaoId=" + dto.getAvaliacaoId());
            }

            if (dto.getValor().compareTo(BigDecimal.ZERO) < 0 || dto.getValor().compareTo(BigDecimal.valueOf(10)) > 0) {
                throw new IllegalArgumentException(
                        "Nota fora do intervalo permitido (0-10) para alunoId=" + dto.getAlunoId() +
                                ", avaliacaoId=" + dto.getAvaliacaoId() + ": " + dto.getValor());
            }

            Nota nota = new Nota();
            nota.setAlunoId(dto.getAlunoId());
            nota.setAvaliacaoId(dto.getAvaliacaoId());
            nota.setValor(dto.getValor());
            return nota;
        }).collect(Collectors.toList());

        notaRepository.saveAll(notas);
    }

}