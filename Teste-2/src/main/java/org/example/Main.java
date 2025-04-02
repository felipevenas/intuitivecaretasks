package org.example;

import org.example.services.CsvService;
import org.example.services.PdfReaderService;
import org.example.services.ZipService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        PdfReaderService pdfReaderService = new PdfReaderService();

        try {
            String pdfFile = ("src/main/resources/Anexo-1.pdf");
            if (pdfFile == null) {
                System.out.println("Arquivo não encontrado: " + pdfFile);
                return;
            }

            String data = pdfReaderService.readPdf(pdfFile);
            CsvService.saveToCsv(data);

            System.out.println();

            ZipService.zipDownloadedFiles();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
