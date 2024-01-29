CREATE TABLE customer (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     cpf BIGINT NOT NULL,
     rg VARCHAR(20),
     email VARCHAR(255) NOT NULL,
     phone BIGINT,
     address VARCHAR(255) NOT NULL,
     number VARCHAR(10) NOT NULL,
     neighborhood VARCHAR(255) NOT NULL,
     city VARCHAR(255) NOT NULL,
     state VARCHAR(2),
     cep BIGINT NOT NULL
);

CREATE TABLE room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE material (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10,2) NOT NULL
);

CREATE TABLE service (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10,2) NOT NULL
);

CREATE TABLE budget (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    `system` VARCHAR(255)  NOT NULL,
    quantity_glass_sheets VARCHAR(255)  NOT NULL,
    total_price NUMERIC(10,2)  NOT NULL,
    note TEXT
);

CREATE TABLE budget_materials (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    budget_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    material_id BIGINT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    note TEXT
);

CREATE TABLE budget_services (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    budget_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    service_id BIGINT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    note TEXT
);

CREATE TABLE work_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    `system` VARCHAR(255) NOT NULL,
    quantity_glass_sheets VARCHAR(255) NOT NULL,
    total_price NUMERIC(10,2) NOT NULL,
    note TEXT
);

CREATE TABLE work_order_materials (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    budget_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    material_id BIGINT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    note TEXT
);

CREATE TABLE work_order_services (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    budget_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    service_id BIGINT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    note TEXT
);

CREATE TABLE done (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    `system` VARCHAR(255) NOT NULL,
    quantity_glass_sheets VARCHAR(255) NOT NULL,
    total_price NUMERIC(10,2) NOT NULL,
    note TEXT
);

CREATE TABLE done_materials (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    budget_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    material_id BIGINT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    note TEXT
);

CREATE TABLE done_services (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    budget_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    service_id BIGINT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    note TEXT
);

CREATE TABLE `group` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE permission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    main_group_id BIGINT NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE user_group (
    user_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, group_id)
);

CREATE TABLE group_permission (
    group_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
PRIMARY KEY (group_id, permission_id)
);
