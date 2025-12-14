package com.denisson.backend.infrastructure.repositories;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.denisson.backend.domain.exceptions.DatabaseException;
import com.denisson.backend.domain.models.Avaliacao;
import com.denisson.backend.domain.repositories.IAvaliacaoRepository;

@Repository
public class AvaliacaoRepositoryImpl implements IAvaliacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AvaliacaoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Avaliacao> mapAvaliacao() {
        return (ResultSet rs, int rowNum) -> {
            Avaliacao a = new Avaliacao();
            a.setId(rs.getLong("id"));
            a.setTitulo(rs.getString("titulo"));
            a.setPeso(rs.getInt("peso"));
            a.setDisciplinaId(rs.getLong("disciplina_id"));
            return a;
        };
    }

    @Override
    public List<Avaliacao> findAll() {
        String sql = "SELECT * FROM avaliacao";
        return jdbcTemplate.query(sql, mapAvaliacao());
    }

    @Override
    public Avaliacao findById(Long id) {
        try {
            String sql = "SELECT * FROM avaliacao WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[] { id }, mapAvaliacao());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar turma id=" + id, e);
        }
    }

    @Override
    public List<Avaliacao> findByDisciplinaId(Long disciplinaId) {
        String sql = "SELECT * FROM avaliacao WHERE disciplina_id = ?";
        return jdbcTemplate.query(sql, new Object[] { disciplinaId }, mapAvaliacao());
    }

    @Override
    public Avaliacao save(Avaliacao avaliacao) {
        String checkSql = "SELECT COUNT(*) FROM avaliacao WHERE titulo = ? AND disciplina_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class,
                avaliacao.getTitulo(), avaliacao.getDisciplinaId());

        if (count != null && count > 0) {
            throw new IllegalArgumentException(
                    "Já existe uma avaliação com este título nesta disciplina.");
        }

        String sql = "INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, avaliacao.getTitulo(), avaliacao.getPeso(), avaliacao.getDisciplinaId());

        return avaliacao;
    }

    @Override
    public Avaliacao update(Avaliacao avaliacao) {
        String sql = "UPDATE avaliacao SET titulo = ?, peso = ?, disciplina_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, avaliacao.getTitulo(), avaliacao.getPeso(), avaliacao.getDisciplinaId(),
                avaliacao.getId());
        return avaliacao;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM avaliacao WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
