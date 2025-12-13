package com.denisson.backend.domain.useCases.notas;

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
        List<Nota> notas = updateNotasDTO.getNotas().stream().map(dto -> {
            Nota nota = new Nota();
            nota.setAlunoId(dto.getAlunoId());
            nota.setAvaliacaoId(dto.getAvaliacaoId());
            nota.setValor(dto.getValor());
            return nota;
        }).collect(Collectors.toList());

        notaRepository.saveAll(notas);
    }
}