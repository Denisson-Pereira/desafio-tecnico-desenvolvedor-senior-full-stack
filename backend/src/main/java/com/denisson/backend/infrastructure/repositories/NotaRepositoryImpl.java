package com.denisson.backend.infrastructure.repositories;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.denisson.backend.domain.dtos.AlunoAvaliacaoNotaDTO;
import com.denisson.backend.domain.models.Nota;
import com.denisson.backend.domain.repositories.INotaRepository;


@Repository
public class NotaRepositoryImpl implements INotaRepository {

    private final JdbcTemplate jdbcTemplate;

    public NotaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Nota> mapNota() {
        return (ResultSet rs, int rowNum) -> {
            Nota nota = new Nota();
            nota.setId(rs.getLong("id"));
            nota.setAlunoId(rs.getLong("aluno_id"));
            nota.setAvaliacaoId(rs.getLong("avaliacao_id"));
            nota.setValor(rs.getBigDecimal("valor"));
            nota.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return nota;
        };
    }

    @Override
    public List<Nota> findAll() {
        String sql = "SELECT * FROM nota";
        return jdbcTemplate.query(sql, mapNota());
    }

    @Override
    public Nota findById(Long id) {
        String sql = "SELECT * FROM nota WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, mapNota());
    }

    @Override
    public List<Nota> findByAlunoId(Long alunoId) {
        String sql = "SELECT * FROM nota WHERE aluno_id = ?";
        return jdbcTemplate.query(sql, new Object[] { alunoId }, mapNota());
    }

    @Override
    public List<Nota> findByAvaliacaoId(Long avaliacaoId) {
        String sql = "SELECT * FROM nota WHERE avaliacao_id = ?";
        return jdbcTemplate.query(sql, new Object[] { avaliacaoId }, mapNota());
    }

    @Override
    public Nota save(Nota nota) {
        String sql = "INSERT INTO nota (aluno_id, avaliacao_id, valor) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, nota.getAlunoId(), nota.getAvaliacaoId(), nota.getValor());
        return nota;
    }

    @Override
    public Nota update(Nota nota) {
        String sql = "UPDATE nota SET valor = ?, updated_at = CURRENT_TIMESTAMP WHERE aluno_id = ? AND avaliacao_id = ?";
        jdbcTemplate.update(sql, nota.getValor(), nota.getAlunoId(), nota.getAvaliacaoId());
        return nota;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM nota WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteByAlunoIdAndAvaliacaoId(Long alunoId, Long avaliacaoId) {
        String sql = "DELETE FROM nota WHERE aluno_id = ? AND avaliacao_id = ?";
        jdbcTemplate.update(sql, alunoId, avaliacaoId);
    }

    @Override
    public List<AlunoAvaliacaoNotaDTO> findAlunosWithAvaliacoesNotasAndMedia(Long turmaId, Long disciplinaId) {
        String sql = """
                    SELECT
                        a.id AS aluno_id,
                        a.nome AS aluno_nome,
                        av.id AS avaliacao_id,
                        av.titulo AS avaliacao_titulo,
                        av.peso AS avaliacao_peso,
                        n.valor AS nota_valor
                    FROM aluno a
                    CROSS JOIN avaliacao av
                    LEFT JOIN nota n
                        ON n.aluno_id = a.id AND n.avaliacao_id = av.id
                    WHERE a.turma_id = ? AND av.disciplina_id = ?
                    ORDER BY a.nome, av.id
                """;

        List<AlunoAvaliacaoNotaDTO> lista = jdbcTemplate.query(sql, new Object[] { turmaId, disciplinaId },
                (rs, rowNum) -> {
                    AlunoAvaliacaoNotaDTO dto = new AlunoAvaliacaoNotaDTO();
                    dto.setAlunoId(rs.getLong("aluno_id"));
                    dto.setAlunoNome(rs.getString("aluno_nome"));
                    dto.setAvaliacaoId(rs.getLong("avaliacao_id"));
                    dto.setAvaliacaoTitulo(rs.getString("avaliacao_titulo"));
                    dto.setAvaliacaoPeso(rs.getInt("avaliacao_peso"));
                    dto.setNotaValor(rs.getBigDecimal("nota_valor"));
                    return dto;
                });

        Map<Long, BigDecimal> medias = new HashMap<>();
        Map<Long, Integer> somaPesos = new HashMap<>();
        Map<Long, BigDecimal> somaNotas = new HashMap<>();

        for (AlunoAvaliacaoNotaDTO dto : lista) {
            if (dto.getNotaValor() != null) {
                somaNotas.put(dto.getAlunoId(),
                        somaNotas.getOrDefault(dto.getAlunoId(), BigDecimal.ZERO).add(
                                dto.getNotaValor().multiply(BigDecimal.valueOf(dto.getAvaliacaoPeso()))));
                somaPesos.put(dto.getAlunoId(),
                        somaPesos.getOrDefault(dto.getAlunoId(), 0) + dto.getAvaliacaoPeso());
            }
        }

        for (AlunoAvaliacaoNotaDTO dto : lista) {
            BigDecimal totalNotas = somaNotas.get(dto.getAlunoId());
            Integer totalPesos = somaPesos.get(dto.getAlunoId());
            if (totalNotas != null && totalPesos != null && totalPesos > 0) {
                dto.setMediaPonderada(totalNotas.divide(BigDecimal.valueOf(totalPesos), 2, BigDecimal.ROUND_HALF_UP));
            } else {
                dto.setMediaPonderada(null); 
            }
        }

        return lista;
    }

}
