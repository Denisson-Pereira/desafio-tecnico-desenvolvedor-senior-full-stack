CREATE TABLE turma (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL
);

CREATE TABLE disciplina (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL
);

CREATE TABLE aluno (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  turma_id BIGINT NOT NULL,
  CONSTRAINT fk_aluno_turma FOREIGN KEY (turma_id)
      REFERENCES turma(id)
      ON DELETE CASCADE
);

CREATE TABLE avaliacao (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  peso INT NOT NULL CHECK (peso >= 1 AND peso <= 5),
  disciplina_id BIGINT NOT NULL,
  CONSTRAINT fk_avaliacao_disciplina FOREIGN KEY (disciplina_id)
      REFERENCES disciplina(id)
      ON DELETE CASCADE
);

CREATE TABLE nota (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  aluno_id BIGINT NOT NULL,
  avaliacao_id BIGINT NOT NULL,
  valor DECIMAL(4,2) NOT NULL CHECK (valor >= 0 AND valor <= 10),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT uq_nota_aluno_avaliacao UNIQUE (aluno_id, avaliacao_id),
  CONSTRAINT fk_nota_aluno FOREIGN KEY (aluno_id)
      REFERENCES aluno(id)
      ON DELETE CASCADE,
  CONSTRAINT fk_nota_avaliacao FOREIGN KEY (avaliacao_id)
      REFERENCES avaliacao(id)
      ON DELETE CASCADE
);
