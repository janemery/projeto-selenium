package pos.exercicio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PesquisaLivrosTest {

	WebDriver driver;

	@Before
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://biblioteca.7setembro.com.br/fa7/");
	}
	
	@Test	
	public void deveriaPesquisarPorTitulo() {
		WebElement formularioPesquisa = driver.findElement(By.xpath("/html/frameset/frame[3]"));
		driver.switchTo().frame(formularioPesquisa);

		WebElement campoTitulo = driver.findElement(By.xpath("/html/body/form/div/center/table/tbody/tr[4]/td[2]/p/input"));
		campoTitulo.sendKeys("Use a cabeça");
		
		WebElement botaoPesquisa = driver.findElement(By.name("cmdPes1"));
		botaoPesquisa.click();
		
		boolean seAchouTitulo = driver.getPageSource().contains("Use a cabeça");

		Assert.assertTrue(seAchouTitulo);
		
		driver.close();
	}

	@Test	
	public void deveriaValidarTituloNaoEncontrado() {
		WebElement formularioPesquisa = driver.findElement(By.xpath("/html/frameset/frame[3]"));
		driver.switchTo().frame(formularioPesquisa);
		
		WebElement campoTitulo = driver.findElement(By.xpath("/html/body/form/div/center/table/tbody/tr[4]/td[2]/p/input"));
		campoTitulo.sendKeys("TITULO QUALQUER");
		
		WebElement botaoPesquisa = driver.findElement(By.name("cmdPes1"));
		botaoPesquisa.click();
		
		boolean seNaoAchouTitulo = driver.getPageSource().contains("Nenhum registro encontrado!");
		
		Assert.assertTrue(seNaoAchouTitulo);
		
		driver.close();
	}

	@Test	
	public void deveriaValidarNenhumTituloInformado() {
		WebElement formularioPesquisa = driver.findElement(By.xpath("/html/frameset/frame[3]"));
		driver.switchTo().frame(formularioPesquisa);
		
		WebElement campoTitulo = driver.findElement(By.xpath("/html/body/form/div/center/table/tbody/tr[4]/td[2]/p/input"));
		campoTitulo.sendKeys("");
		
		WebElement botaoPesquisa = driver.findElement(By.name("cmdPes1"));
		botaoPesquisa.click();
		
		boolean seNaoAchouTitulo = driver.getPageSource().contains("Nenhuma opção de pesquisa definida!");
		
		Assert.assertTrue(seNaoAchouTitulo);
		
		driver.close();
	}
}
