package br.com.caelum.teste;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class LeiloesPage {

    private WebDriver driver;

    public LeiloesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void visita() {
        driver.get("http://localhost:8080/leiloes");
    }

    public NovoLeilaoPage novo() {
        // clica no link de novo leilao
        driver.findElement(By.linkText("Novo Leil�o")).click();
        // retorna a classe que representa a nova pagina
        return new NovoLeilaoPage(driver);
    }

    public boolean existe(String produto, double valor, String usuario,
            boolean usado) {

        return driver.getPageSource().contains(produto) && 
                driver.getPageSource().contains(String.valueOf(valor)) &&
                driver.getPageSource().contains(usado ? "Sim" : "N�o");

    }
}