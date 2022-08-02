package com.transferencia.demo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.ls.LSOutput;

public class TestCaseMontoTransferido {

	String resourceFolder = "src/main/resources";

	@Test
	// Declaración de variables generales
	public void test() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", resourceFolder + "/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.testfire.net/login.jsp");

		driver.manage().window().maximize();
		//Thread.sleep(1000);

		// Login
		driver.findElement(By.id("uid")).sendKeys("jsmith");
		driver.findElement(By.id("passw")).sendKeys("Demo1234");
		driver.findElement(By.name("btnSubmit")).click();

		// Ingresar a tranferencias
		driver.findElement(By.id("MenuHyperLink3")).click();
		

		// Seleccionar cuenta de origen
		WebElement checking = driver.findElement(By.id("fromAccount"));
		Select ddChecking = new Select(checking);
		ddChecking.selectByIndex(0);

		// Seleccionar la cuenta de destino
		driver.findElement(By.id("MenuHyperLink3")).click();
		//Thread.sleep(1000);
		WebElement credit = driver.findElement(By.id("fromAccount"));
		Select ddCredit = new Select(credit);
		ddCredit.selectByIndex(2);

		// Ingreso del monto al transferir y submit
		driver.findElement(By.id("transferAmount")).sendKeys("100");

		driver.findElement(By.id("transfer")).click();

		// Validar que el monto sea 100
		String texto = driver.findElement(By.xpath("//*[@id=\"_ctl0__ctl0_Content_Main_postResp\"]/span")).getText();
		//System.out.println(texto);
		//Thread.sleep(1000);
		if (texto.contains("100")) {
			System.out.println("Test case passed.");
		} else {
			System.out.println("Test case failed.");
			fail("Test case failed.");
		}


		// Cerrar sesión
		driver.findElement(By.id("LoginLink")).click();
		//Thread.sleep(1000);
		driver.quit();
	}

}
