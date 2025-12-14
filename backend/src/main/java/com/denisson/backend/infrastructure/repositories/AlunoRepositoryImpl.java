package com.denisson.backend.infrastructure.repositories;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.denisson.backend.domain.exceptions.DatabaseException;
import com.denisson.backend.domain.models.Aluno;
import com.denisson.backend.domain.repositories.IAlunoRepository;

@Repository
public class AlunoRepositoryImpl implements IAlunoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlunoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Aluno> findAll() {
        String sql = "SELECT * FROM aluno";
        return jdbcTemplate.query(sql, mapAluno());
    }

    @Override
    public Aluno findById(Long id) {
        try {
            String sql = "SELECT * FROM aluno WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[] { id }, mapAluno());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar turma id=" + id, e);
        }
    }

    @Override
    public List<Aluno> findByTurmaId(Long turmaId) {
        String sql = "SELECT * FROM aluno WHERE turma_id = ?";
        return jdbcTemplate.query(sql, new Object[] { turmaId }, mapAluno());
    }

    @Override
    public Aluno save(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, turma_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, aluno.getNome(), aluno.getTurmaId());
        return aluno;
    }

    @Override
    public Aluno update(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, turma_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, aluno.getNome(), aluno.getTurmaId(), aluno.getId());
        return aluno;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM aluno WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Aluno> mapAluno() {
        return (ResultSet rs, int rowNum) -> {
            Aluno aluno = new Aluno();
            aluno.setId(rs.getLong("id"));
            aluno.setNome(rs.getString("nome"));
            aluno.setTurmaId(rs.getLong("turma_id"));
            return aluno;
        };
    }

    @Override
    public Aluno findByNome(String nome) {
        try {
            String sql = "SELECT * FROM aluno WHERE nome = ?";
            return jdbcTemplate.queryForObject(sql, new Object[] { nome }, (rs, rowNum) -> {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("id"));
                aluno.setNome(rs.getString("nome"));
                return aluno;
            });
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar Alunos pelo nome", e);
        }
    }
}
