package org.example.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipService {

    private static final String downloadFolder = ".";
    private static final String zipFile = "src/main/resources/Anexo-Formatado-Csv.zip";

    public static void zipDownloadedFiles() {

        File directory = new File(downloadFolder);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("A pasta resources não existe ou não é um diretório.");
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            File[] files = directory.listFiles((dir, name) -> name.endsWith(".csv"));
            if (files == null || files.length == 0) {
                System.out.println("Nenhum arquivo CSV encontrado para compactar.");
                return;
            }

            for (File file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, bytesRead);
                    }

                    zos.closeEntry();
                    System.out.println("Arquivo adicionado ao ZIP: " + file.getName());
                }
            }

            System.out.println("Arquivo compactado com sucesso!");
            System.out.println("Local: " + zipFile);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao compactar arquivos: " + e.getMessage());
        }
    }
}
