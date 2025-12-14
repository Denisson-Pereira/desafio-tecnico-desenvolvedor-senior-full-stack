INSERT INTO turma (id, nome) VALUES 
  (1, '3º Ano A'),
  (2, '3º Ano B');

INSERT INTO disciplina (id, nome) VALUES 
  (1, 'Física'),
  (2, 'Matemática'),
  (3, 'Química'),
  (4, 'Biologia');

INSERT INTO aluno (id, nome, turma_id) VALUES 
  (1, 'João Silva', 1),
  (2, 'Maria Souza', 1),
  (3, 'Pedro Lima', 1),
  (4, 'Ana Ribeiro', 2),
  (5, 'Lucas Mendes', 2),
  (6, 'Camila Rocha', 2);

INSERT INTO avaliacao (id, titulo, peso, disciplina_id) VALUES 
  (1, 'Prova 1', 3, 1),
  (2, 'Trabalho', 2, 1),
  (3, 'Prova Final', 5, 1),

  (4, 'Prova 1', 3, 2),
  (5, 'Lista de Exercícios', 1, 2),
  (6, 'Prova Final', 5, 2),

  (7, 'Prova 1', 4, 3),
  (8, 'Experimento', 1, 3),
  (9, 'Prova Final', 5, 3),

  (10, 'Prova 1', 3, 4),
  (11, 'Seminário', 2, 4),
  (12, 'Prova Final', 5, 4);

INSERT INTO nota (aluno_id, avaliacao_id, valor) VALUES
  (1, 1, 8.5), (1, 2, 9.0), (1, 3, 7.8),
  (2, 1, 7.2), (2, 2, 8.0), (2, 3, 8.5),
  (3, 1, 6.0), (3, 2, 7.5), (3, 3, 7.0),

  (1, 4, 9.1), (1, 5, 8.0), (1, 6, 8.7),
  (2, 4, 7.8), (2, 5, 7.0), (2, 6, 8.2),
  (3, 4, 5.5), (3, 5, 6.5), (3, 6, 6.8),

  (4, 7, 8.8), (4, 8, 9.5), (4, 9, 8.9),
  (5, 7, 7.0), (5, 8, 7.8), (5, 9, 7.2),
  (6, 7, 6.8), (6, 8, 7.0), (6, 9, 6.5),

  (4, 10, 9.3), (4, 11, 9.0), (4, 12, 9.7),
  (5, 10, 7.5), (5, 11, 7.0), (5, 12, 8.1),
  (6, 10, 6.3), (6, 11, 7.2), (6, 12, 6.8);

ALTER TABLE avaliacao ALTER COLUMN id RESTART WITH 13; 

ALTER TABLE turma ALTER COLUMN id RESTART WITH 3;
ALTER TABLE disciplina ALTER COLUMN id RESTART WITH 5;
ALTER TABLE aluno ALTER COLUMN id RESTART WITH 7;