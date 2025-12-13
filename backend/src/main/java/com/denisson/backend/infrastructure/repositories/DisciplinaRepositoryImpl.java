package com.denisson.backend.infrastructure.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.denisson.backend.domain.models.Disciplina;
import com.denisson.backend.domain.repositories.IDisciplinaRepository;


@Repository
public class DisciplinaRepositoryImpl implements IDisciplinaRepository {

    private final JdbcTemplate jdbcTemplate;

    public DisciplinaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Disciplina> findAll() {
        String sql = "SELECT * FROM disciplina";
        return jdbcTemplate.query(sql, new RowMapper<Disciplina>() {
            @Override
            public Disciplina mapRow(ResultSet rs, int rowNum) throws SQLException {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getLong("id"));
                disciplina.setNome(rs.getString("nome"));
                return disciplina;
            }
        });
    }

    @Override
    public Disciplina findById(Long id) {
        String sql = "SELECT * FROM disciplina WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Disciplina disciplina = new Disciplina();
            disciplina.setId(rs.getLong("id"));
            disciplina.setNome(rs.getString("nome"));
            return disciplina;
        });
    }

    @Override
    public Disciplina save(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina (id, nome) VALUES (?, ?)";
        jdbcTemplate.update(sql, disciplina.getId(), disciplina.getNome());
        return disciplina;
    }

    @Override
    public Disciplina update(Disciplina disciplina) {
        String sql = "UPDATE disciplina SET nome = ? WHERE id = ?";
        jdbcTemplate.update(sql, disciplina.getNome(), disciplina.getId());
        return disciplina;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM disciplina WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
