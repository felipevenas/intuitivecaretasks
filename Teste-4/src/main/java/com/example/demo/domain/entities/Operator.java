package com.example.demo.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "operadoras_ativas")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Operator {

    @Id
    private String registro_ans;

    private String cnpj;

    @Column(name = "razao_social")
    private String razao_social;

    @Column(name = "nome_fantasia")
    private String nome_fantasia;

    private String modalidade;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String ddd;
    private String telefone;
    private String fax;

    @Column(name = "endereco_eletronico")
    private String endereco_eletronico;

    private String representante;

    @Column(name = "cargo_representante")
    private String cargo_representante;

    @Column(name = "regiao_comercializacao")
    private String regiao_comercializacao;

    @Column(name = "data_registro")
    private String data_registro;

    public Operator(String registro_ans, String cnpj, String razao_social, String nome_fantasia, String modalidade, String logradouro, String numero, String complemento, String bairro, String cidade, String uf, String cep, String ddd, String telefone, String fax, String endereco_eletronico, String representante, String cargo_representante, String regiao_comercializacao, String data_registro) {
        this.registro_ans = registro_ans;
        this.cnpj = cnpj;
        this.razao_social = razao_social;
        this.nome_fantasia = nome_fantasia;
        this.modalidade = modalidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.ddd = ddd;
        this.telefone = telefone;
        this.fax = fax;
        this.endereco_eletronico = endereco_eletronico;
        this.representante = representante;
        this.cargo_representante = cargo_representante;
        this.regiao_comercializacao = regiao_comercializacao;
        this.data_registro = data_registro;
    }
}
