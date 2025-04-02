package org.example.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class DownloadPdfService {

    private static final String downloadFolder = "downloads/";

    public static void downloadPdf(String pdfUrl, String fileName) {
        try {
            File directory = new File(downloadFolder);
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Pasta de downloads criada: " + downloadFolder);
            }

            String filePath = downloadFolder + fileName;

            try (InputStream in = new URL(pdfUrl).openStream();
                 FileOutputStream out = new FileOutputStream(filePath)) {

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                System.out.println("Download concluído!");
                System.out.println("Local: " + filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao baixar o PDF: " + e.getMessage());
        }
    }

}
