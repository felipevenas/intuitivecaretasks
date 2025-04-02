package org.example.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookiesService {

    public static void acceptCookies(WebDriverWait wait) {
        try {

            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/div/div/div/div/div[2]/button[3]")));
            acceptCookies.click();
            System.out.println("Cookies aceitos!");

        } catch (Exception e) {
            System.out.println("Botão de aceitar cookies não encontrado!");
        }
    }

}
