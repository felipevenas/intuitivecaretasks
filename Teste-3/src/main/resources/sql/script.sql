CREATE DATABASE automation_db;

USE automation_db;

CREATE TABLE IF NOT EXISTS operadoras_ativas
(
    registro_ans        VARCHAR(20) PRIMARY KEY,
    cnpj                VARCHAR(18),
    razao_social        VARCHAR(255),
    nome_fantasia       VARCHAR(255),
    modalidade          VARCHAR(100),
    logradouro          VARCHAR(255),
    numero              VARCHAR(10),
    complemento         VARCHAR(100),
    bairro              VARCHAR(100),
    cidade              VARCHAR(100),
    uf                  VARCHAR(2),
    cep                 VARCHAR(20),
    ddd                 VARCHAR(5),
    telefone            VARCHAR(20),
    endereco_eletronico VARCHAR(255),
    representante       VARCHAR(255),
    cargo_representante VARCHAR(255),
    data_registro       DATE
);

CREATE TABLE IF NOT EXISTS demonstrativos_contabeis
(
    registro_ans VARCHAR(20),
    ano          INT,
    trimestre    INT,
    categoria    VARCHAR(255),
    valor        DECIMAL(18, 2),
    FOREIGN KEY (registro_ans) REFERENCES operadoras_ativas (registro_ans)
);

