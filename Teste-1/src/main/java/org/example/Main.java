package org.example;

import org.example.services.CookiesService;
import org.example.services.DownloadPdfService;
import org.example.services.ZipService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920x1080");

        WebDriver webDriver = new ChromeDriver(options);

        try {

            String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/" +
                    "atualizacao-do-rol-de-procedimentos";
            webDriver.get(url);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1));

            CookiesService.acceptCookies(wait);

            System.out.println();

            WebElement pdfLink = wait.until(ExpectedConditions
                    .elementToBeClickable
                            (By.xpath("//*[@id=\"cfec435d-6921-461f-b85a-b425bc3cb4a5\"]/div/ol/li[1]/a[1]")
                            ));

            String pdfUrl = pdfLink.getAttribute("href");
            System.out.println("Arquivo encontrado: " + pdfUrl);

            DownloadPdfService.downloadPdf(pdfUrl, "Anexo-1.pdf");

            System.out.println();

            WebElement pdfLink2 = wait.until(ExpectedConditions
                    .elementToBeClickable
                            (By.xpath("//*[@id=\"cfec435d-6921-461f-b85a-b425bc3cb4a5\"]/div/ol/li[2]/a")
                            ));

            String pdfUrl2 = pdfLink2.getAttribute("href");
            System.out.println("Arquivo encontrado: " + pdfUrl2);

            DownloadPdfService.downloadPdf(pdfUrl2, "Anexo-2.pdf");

            System.out.println();

            ZipService.zipDownloadedFiles();

        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        } finally {
            webDriver.quit();
        }
    }
}
