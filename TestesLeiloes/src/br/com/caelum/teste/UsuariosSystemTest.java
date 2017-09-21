package br.com.caelum.teste;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UsuariosSystemTest {

	private ChromeDriver driver;
	private UsuarioPage usuarios;

	// configuração do driver do browser antes de iniciar os testes
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		driver = new ChromeDriver();
		this.usuarios = new UsuarioPage(driver);
		usuarios.visita();
		driver.get("http://localhost:8080/apenas-teste/limpa");
	}

	// fecha browser depois de finalizar os testes
	@After
	public void finaliza() {
		driver.close();
	}

	@Test
	public void deveAdicionarUmUsuario() {

		usuarios.novo().cadastra("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br");
		assertTrue(usuarios.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"));

	}

	@Test
	public void NaoDeveAdicionarUmUsuarioSemNome() {

		usuarios.novo().cadastra("", "ronaldo2009@terra.com.br");
		assertTrue(new NovoUsuarioPage(driver).validacaoDeNomeObrigatorio());
	}

	@Test
	public void identificaSeAMensagemDeNomeEEmailObrigatoriosAparece() {

		usuarios.novo().cadastra("", "");
		assertTrue(driver.getPageSource().contains("Nome obrigatorio!")
				&& driver.getPageSource().contains("E-mail obrigatorio!"));

	}

	@Test
	public void deveDeletarUmUsuario() {

		usuarios.novo().cadastra("Usuario A Ser Deletado", "usuario2009@terra.com.br");
		assertTrue(usuarios.existeNaListagem("Usuario A Ser Deletado", "usuario2009@terra.com.br"));

		usuarios.deletaUsuarioNaPosicao(3);

		assertFalse(usuarios.existeNaListagem("Usuario A Ser Deletado", "usuario2009@terra.com.br"));
	}
	@Test
	public void deveEditarUmUsuario() {
		usuarios.visita();
		usuarios.editarUmUsuarioNaPosicao(2, "Joao Jose", "joaojose@joao.com.br");
		assertTrue(driver.getPageSource().contains("Joao Jose")
				&& driver.getPageSource().contains("joaojose@joao.com.br"));
	}
}
