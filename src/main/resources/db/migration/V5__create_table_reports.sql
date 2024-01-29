CREATE TABLE report (
    id 			 			 BIGINT AUTO_INCREMENT NOT NULL,
    source        			 VARCHAR(255) NOT NULL,
    name         			 VARCHAR(255) NOT NULL,
    identifier   		     VARCHAR(255) NOT NULL,
    CONSTRAINT report_pkey PRIMARY KEY (id)
) CHARACTER SET utf8mb4,  COLLATE utf8mb4_unicode_ci;