ALTER TABLE orcamento
    ADD CONSTRAINT fk_orcamento_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE orcamento_materiais
    ADD CONSTRAINT fk_orcamento_materiais_orcamento FOREIGN KEY (orcamento_id) REFERENCES orcamento (id);

ALTER TABLE orcamento_materiais
    ADD CONSTRAINT fk_orcamento_materiais_material FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE orcamento_servicos
    ADD CONSTRAINT fk_orcamento_servicos_orcamento FOREIGN KEY (orcamento_id) REFERENCES orcamento (id);

ALTER TABLE orcamento_servicos
    ADD CONSTRAINT fk_orcamento_servicos_servico FOREIGN KEY (servico_id) REFERENCES servico (id);

ALTER TABLE orderm_servico
    ADD CONSTRAINT fk_orderm_servico_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE orderm_servico_materiais
    ADD CONSTRAINT fk_orderm_servico_materiais_orcamento FOREIGN KEY (orcamento_id) REFERENCES orcamento (id);

ALTER TABLE orderm_servico_materiais
    ADD CONSTRAINT fk_orderm_servico_materiais_material FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE orderm_servico_servicos
    ADD CONSTRAINT fk_orderm_servico_servicos_orcamento FOREIGN KEY (orcamento_id) REFERENCES orcamento (id);

ALTER TABLE orderm_servico_servicos
    ADD CONSTRAINT fk_orderm_servico_servicos_servico FOREIGN KEY (servico_id) REFERENCES servico (id);

ALTER TABLE realizado
    ADD CONSTRAINT fk_realizado_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE realizado_materiais
    ADD CONSTRAINT fk_realizado_materiais_orcamento FOREIGN KEY (orcamento_id) REFERENCES orcamento (id);

ALTER TABLE realizado_materiais
    ADD CONSTRAINT fk_realizado_materiais_material FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE realizado_servicos
    ADD CONSTRAINT fk_realizado_servicos_orcamento FOREIGN KEY (orcamento_id) REFERENCES orcamento (id);

ALTER TABLE realizado_servicos
    ADD CONSTRAINT fk_realizado_servicos_servico FOREIGN KEY (servico_id) REFERENCES servico (id);