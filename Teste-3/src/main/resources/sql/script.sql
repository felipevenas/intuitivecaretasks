CREATE TABLE operadoras (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cnpj VARCHAR(14),
);

CREATE TABLE demontracao_contabeis (
    id SERIAL PRIMARY KEY,
    operadora_id INT,
    ano INT,
    mes INT,
    evento VARCHAR(255),
    valor DECIMAL(15, 2),
    FOREIGN KEY (operadora_id) REFERENCES operadoras(id)
);

COPY operadoras(nome, cnpj, status)
FROM './data/Relatorio_cadop.csv'