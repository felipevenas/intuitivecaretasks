package com.example.demo.services;

import com.example.demo.controllers.OperatorResource;
import com.example.demo.domain.entities.Operator;
import com.example.demo.repositories.OperatorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    public List<Operator> findAll() {
        return operatorRepository.findAll();
    }

    public Optional<Operator> findOperatorById(String registro_ans) {
        return operatorRepository.findById(Long.valueOf(registro_ans));
    }

    @Transactional
    public void importCSV(String caminhoArquivo) {
        try (InputStream inputStream = new ClassPathResource(caminhoArquivo).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            reader.readLine(); // Ignora o cabeçalho
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";", -1); // Mantém valores vazios

                if (data.length < 20) { // ID não conta, precisa ter 20 colunas
                    System.out.println("Linha ignorada (colunas insuficientes): " + line);
                    continue;
                }

                Operator operator = new Operator(
                        parseOrNull(data[0]),  parseOrNull(data[1]),  parseOrNull(data[2]),
                        parseOrNull(data[3]),  parseOrNull(data[4]),  parseOrNull(data[5]),
                        parseOrNull(data[6]),  parseOrNull(data[7]),  parseOrNull(data[8]),
                        parseOrNull(data[9]),  parseOrNull(data[10]), parseOrNull(data[11]),
                        parseOrNull(data[12]), parseOrNull(data[13]), parseOrNull(data[14]),
                        parseOrNull(data[15]), parseOrNull(data[16]), parseOrNull(data[17]),
                        parseOrNull(data[18]), parseOrNull(data[19]) // O ID é ignorado aqui
                );

                try {
                    operatorRepository.save(operator);
                    System.out.println("Registro salvo: " + operator.getRazao_social());
                } catch (Exception e) {
                    System.err.println("Erro ao salvar registro: " + operator.getRazao_social() + " - " + e.getMessage());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar CSV: " + e.getMessage(), e);
        }
    }





    private String parseOrNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }

}
