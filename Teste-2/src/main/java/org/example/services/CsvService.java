package org.example.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvService {

    public static void saveToCsv(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Teste_Felipe_Venas.csv"))) {
            String[] lines = data.split("\n");
            List<String> campos = new ArrayList<>();

            for (String line : lines) {
                line = line.trim().replaceAll(",", " - ").replaceAll("\s+", " ");

                if (line.isEmpty()) continue;

                String[] partes = line.split("\s{2,}");
                for (String parte : partes) {
                    if (!parte.isEmpty()) {
                        campos.add(parte);
                    }
                }

                if (campos.size() >= 5) {
                    writer.write(String.join(",", campos));
                    writer.newLine();
                    campos.clear();
                }
            }

            if (!campos.isEmpty()) {
                writer.write(String.join(",", campos));
                writer.newLine();
            }

            System.out.println("Arquivo gerado com sucesso!");
        }
    }

}