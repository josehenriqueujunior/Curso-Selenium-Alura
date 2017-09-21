package br.com.caelum.teste;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UsuarioPage {
	
	private WebDriver driver;

    public UsuarioPage(WebDriver driver) {
        this.driver = driver;
    }

    public void visita() {
        driver.get("localhost:8080/usuarios");
    }

    public NovoUsuarioPage novo() {
        // clica no link de novo usuario
        driver.findElement(By.linkText("Novo Usu�rio")).click();
        // retorna a classe que representa a nova pagina
        return new NovoUsuarioPage(driver);
    }

    public boolean existeNaListagem(String nome, String email) {
        // verifica se ambos existem na listagem
        return driver.getPageSource().contains(nome) && 
                driver.getPageSource().contains(email);
    }
    public void deletaUsuarioNaPosicao(int posicao) {
    driver.findElements(By.tagName("button")).get(posicao-1).click();
    Alert alert = driver.switchTo().alert();
    alert.accept();
    }
    public void editarUmUsuarioNaPosicao(int posicao, String nome, String email) {
    	driver.findElements(By.linkText("editar")).get(posicao-1).click();
    	WebElement txtNome = driver.findElement(By.name("usuario.nome"));
        WebElement txtEmail = driver.findElement(By.name("usuario.email"));
        txtNome.sendKeys(nome);
        txtEmail.sendKeys(email);

        txtNome.submit();

    }

}

