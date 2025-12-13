package com.denisson.backend.infrastructure.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.denisson.backend.domain.models.Turma;
import com.denisson.backend.domain.repositories.ITurmaRepository;


@Repository
public class TurmaRepositoryImpl implements ITurmaRepository {

    private final JdbcTemplate jdbcTemplate;

    public TurmaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Turma> findAll() {
        String sql = "SELECT * FROM turma";
        return jdbcTemplate.query(sql, new RowMapper<Turma>() {
            @Override
            public Turma mapRow(ResultSet rs, int rowNum) throws SQLException {
                Turma turma = new Turma();
                turma.setId(rs.getLong("id"));
                turma.setNome(rs.getString("nome"));
                return turma;
            }
        });
    }

    @Override
    public Turma findById(Long id) {
        String sql = "SELECT * FROM turma WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
            Turma turma = new Turma();
            turma.setId(rs.getLong("id"));
            turma.setNome(rs.getString("nome"));
            return turma;
        });
    }

    @Override
    public Turma save(Turma turma) {
        String sql = "INSERT INTO turma (id, nome) VALUES (?, ?)";
        jdbcTemplate.update(sql, turma.getId(), turma.getNome());
        return turma;
    }

    @Override
    public Turma update(Turma turma) {
        String sql = "UPDATE turma SET nome = ? WHERE id = ?";
        jdbcTemplate.update(sql, turma.getNome(), turma.getId());
        return turma;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM turma WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
