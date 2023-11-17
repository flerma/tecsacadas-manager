CREATE TABLE cliente (
     id SERIAL PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     cpf BIGINT NOT NULL,
     rg VARCHAR(20),
     email VARCHAR(255) NOT NULL,
     telefone BIGINT,
     endereco VARCHAR(255) NOT NULL,
     numero VARCHAR(10) NOT NULL,
     bairro VARCHAR(255) NOT NULL,
     cidade VARCHAR(255) NOT NULL,
     estado VARCHAR(2),
     cep BIGINT NOT NULL
);

CREATE TABLE ambiente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE material (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  valor NUMERIC(10,2) NOT NULL
);

CREATE TABLE servico (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor NUMERIC(10,2) NOT NULL
);

CREATE TABLE orcamento (
    id SERIAL PRIMARY KEY,
    cliente_id SERIAL  NOT NULL,
    sistema VARCHAR(255)  NOT NULL,
    quantidade_folhas VARCHAR(255)  NOT NULL,
    valor_total NUMERIC(10,2)  NOT NULL,
    observacao TEXT
);

CREATE TABLE orcamento_materiais (
    id SERIAL PRIMARY KEY,
    orcamento_id SERIAL NOT NULL,
    quantidade INTEGER NOT NULL,
    material_id SERIAL NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    observacao TEXT
);

CREATE TABLE orcamento_servicos (
    id SERIAL PRIMARY KEY,
    orcamento_id SERIAL NOT NULL,
    quantidade INTEGER NOT NULL,
    servico_id SERIAL NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    observacao TEXT
);

CREATE TABLE orderm_servico (
    id SERIAL PRIMARY KEY,
    cliente_id SERIAL NOT NULL,
    sistema VARCHAR(255) NOT NULL,
    quantidade_folhas VARCHAR(255) NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    observacao TEXT
);

CREATE TABLE orderm_servico_materiais (
    id SERIAL PRIMARY KEY,
    orcamento_id SERIAL NOT NULL,
    quantidade INTEGER NOT NULL,
    material_id SERIAL NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    observacao TEXT
);

CREATE TABLE orderm_servico_servicos (
    id SERIAL PRIMARY KEY,
    orcamento_id SERIAL NOT NULL,
    quantidade INTEGER NOT NULL,
    servico_id SERIAL NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    observacao TEXT
);

CREATE TABLE realizado (
    id SERIAL PRIMARY KEY,
    cliente_id SERIAL NOT NULL,
    sistema VARCHAR(255) NOT NULL,
    quantidade_folhas VARCHAR(255) NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    observacao TEXT
);

CREATE TABLE realizado_materiais (
    id SERIAL PRIMARY KEY,
    orcamento_id SERIAL NOT NULL,
    quantidade INTEGER NOT NULL,
    material_id SERIAL NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    observacao TEXT
);

CREATE TABLE realizado_servicos (
    id SERIAL PRIMARY KEY,
    orcamento_id SERIAL NOT NULL,
    quantidade INTEGER NOT NULL,
    servico_id SERIAL NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    observacao TEXT
);

CREATE TABLE grupo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE permissao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL
);

CREATE TABLE usuario_grupo (
    usuario_id BIGINT NOT NULL,
    grupo_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, grupo_id)
);

CREATE TABLE grupo_permissao (
    grupo_id BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,
PRIMARY KEY (grupo_id, permissao_id)
);
