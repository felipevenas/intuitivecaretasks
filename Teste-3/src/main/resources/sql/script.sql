CREATE DATABASE IF NOT EXISTS automation_db;

USE automation_db;

CREATE TABLE IF NOT EXISTS operadoras_ativas
(
    registro_ans           VARCHAR(20) PRIMARY KEY,
    cnpj                   VARCHAR(18),
    razao_social           VARCHAR(255),
    nome_fantasia          VARCHAR(255),
    modalidade             VARCHAR(100),
    logradouro             VARCHAR(255),
    numero                 VARCHAR(20),
    complemento            VARCHAR(100),
    bairro                 VARCHAR(100),
    cidade                 VARCHAR(100),
    uf                     VARCHAR(2),
    cep                    VARCHAR(20),
    ddd                    VARCHAR(5),
    telefone               VARCHAR(20),
    fax                    VARCHAR(20),
    endereco_eletronico    VARCHAR(255),
    representante          VARCHAR(255),
    cargo_representante    VARCHAR(255),
    regiao_comercializacao VARCHAR(2),
    data_registro          VARCHAR(55)
);

CREATE TABLE IF NOT EXISTS demonstrativos_contabeis
(
    data           VARCHAR(55),
    reg_ans        VARCHAR(20),
    conta_contabil VARCHAR(20),
    descricao      VARCHAR(255),
    saldo_inicial  VARCHAR(50),
    saldo_final    VARCHAR(50),
    FOREIGN KEY (reg_ans) REFERENCES operadoras_ativas (registro_ans)
);

CREATE TEMPORARY TABLE IF NOT EXISTS temp_demonstrativos LIKE demonstrativos_contabeis;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/operadoras_ativas.csv'
    REPLACE INTO TABLE operadoras_ativas
    FIELDS TERMINATED BY ';'
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES
    (registro_ans, cnpj, razao_social, nome_fantasia, modalidade, logradouro, numero, complemento, bairro, cidade, uf,
     cep, ddd, telefone, fax, endereco_eletronico, representante, cargo_representante, regiao_comercializacao,
     data_registro);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/4T2024.csv'
    REPLACE INTO TABLE temp_demonstrativos
    FIELDS TERMINATED BY ';'
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES
    (data, reg_ans, conta_contabil, descricao, saldo_inicial, saldo_final);

INSERT INTO demonstrativos_contabeis (data, reg_ans, conta_contabil, descricao, saldo_inicial, saldo_final)
SELECT td.data, td.reg_ans, td.conta_contabil, td.descricao, td.saldo_inicial, td.saldo_final
FROM temp_demonstrativos td INNER JOIN operadoras_ativas oa ON td.reg_ans = oa.registro_ans;
