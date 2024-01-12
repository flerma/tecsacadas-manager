CREATE TABLE report (
    id 			 			 BIGINT AUTO_INCREMENT NOT NULL,
    source        			 VARCHAR(255) NOT NULL,
    name         			 VARCHAR(255) NOT NULL,
    identifier   		     VARCHAR(255) NOT NULL,
    CONSTRAINT relatorio_pkey PRIMARY KEY (id)
);