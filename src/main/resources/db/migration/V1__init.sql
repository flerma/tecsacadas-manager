CREATE TABLE cliente (
     id BIGINT PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     cpf BIGINT NOT NULL,
     rg VARCHAR(20) NOT NULL,
     email VARCHAR(255) NOT NULL,
     telefone BIGINT,
     endereco VARCHAR(255) NOT NULL,
     numero VARCHAR(10) NOT NULL,
     bairro VARCHAR(255) NOT NULL,
     cidade VARCHAR(255) NOT NULL,
     estado VARCHAR(2) NOT NULL,
     cep BIGINT
);

CREATE TABLE ambiente (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE material (
  id BIGINT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  valor NUMERIC(10,2) NOT NULL
);

CREATE TABLE servico (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor NUMERIC(10,2) NOT NULL
);

CREATE TABLE orcamento (
    id BIGINT PRIMARY KEY,
    cliente_id BIGINT,
    sistema VARCHAR(255),
    quantidade_folhas VARCHAR(255),
    valor_total NUMERIC(10,2),
    observacao TEXT
);

CREATE TABLE orcamento_materiais (
    id BIGINT PRIMARY KEY,
    orcamento_id BIGINT,
    quantidade INTEGER,
    material_id BIGINT,
    valor NUMERIC(10,2),
    observacao TEXT
);

CREATE TABLE orcamento_servicos (
    id BIGINT PRIMARY KEY,
    orcamento_id BIGINT,
    quantidade INTEGER,
    servico_id BIGINT,
    valor NUMERIC(10,2),
    observacao TEXT
);

CREATE TABLE "orderm_servico" (
    id BIGINT PRIMARY KEY,
    cliente_id BIGINT,
    sistema VARCHAR(255),
    quantidade_folhas VARCHAR(255),
    valor_total NUMERIC(10,2),
    observacao TEXT
);

CREATE TABLE "orderm_servico_materiais" (
    id BIGINT PRIMARY KEY,
    orcamento_id BIGINT,
    quantidade INTEGER,
    material_id BIGINT,
    valor NUMERIC(10,2),
    observacao TEXT
);

CREATE TABLE orderm_servico_servicos (
    id BIGINT PRIMARY KEY,
    orcamento_id BIGINT,
    quantidade INTEGER,
    servico_id BIGINT,
    valor NUMERIC(10,2),
    observacao TEXT
);

CREATE TABLE realizado (
    id BIGINT PRIMARY KEY,
    cliente_id BIGINT,
    sistema VARCHAR(255),
    quantidade_folhas VARCHAR(255),
    valor_total NUMERIC(10,2),
    observacao TEXT
);

CREATE TABLE realizado_materiais (
    id BIGINT PRIMARY KEY,
    orcamento_id BIGINT,
    quantidade INTEGER,
    material_id BIGINT,
    valor NUMERIC(10,2),
    observacao TEXT
);

CREATE TABLE realizado_servicos (
    id BIGINT PRIMARY KEY,
    orcamento_id BIGINT,
    quantidade INTEGER,
    servico_id BIGINT,
    valor NUMERIC(10,2),
    observacao TEXT
);