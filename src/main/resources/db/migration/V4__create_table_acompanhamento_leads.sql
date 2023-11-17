CREATE TABLE public.acompanhamento_lead (
    id 			 			 SERIAL NOT NULL,
    data_contato 			 DATE NOT NULL,
    local_origem 			 VARCHAR(255) NOT NULL,
    procurou     			 VARCHAR(255) NOT NULL,
    tipo_servico 			 VARCHAR(255) NULL,
    cliente_potencial 		 VARCHAR(255) NULL,
    motivo_nao_ser_potencial VARCHAR(255) NULL,
    forma_contato 			 VARCHAR(255) NULL,
    status 					 VARCHAR(255) NULL,
    observacao 				 VARCHAR(255) NULL,
    CONSTRAINT acompanhamento_lead_pkey PRIMARY KEY (id)
);