package br.com.caelum.teste;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.GetPageSource;

public class LeiloesSystemTest {

	private WebDriver driver;
	private LeiloesPage leiloes;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		this.driver = new ChromeDriver();
		leiloes = new LeiloesPage(driver);
		driver.get("http://localhost:8080/apenas-teste/limpa");
		UsuarioPage usuarios = new UsuarioPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Paulo Henrique", "paulo@henrique.com");
	}

	@Test
	public void deveCadastrarUmLeilao() {

		leiloes.visita();
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.preenche("Geladeira", 123, "Paulo Henrique", true);

		assertTrue(leiloes.existe("Geladeira", 123, "Paulo Henrique", true));

	}

	@Test
	public void VerificaSeAsMensagensDeNomeObrigatorioEValorInicialMaiorQueZeroAparecem() {

		leiloes.visita();
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.preenche("", 0, "Paulo Henrique", true);

		assertTrue(novoLeilao.mensagemNomeObrigatorioApareceu() && novoLeilao.mensagemOValorInicialDeveSerMaiorQueZeroApareceu());
	}
}