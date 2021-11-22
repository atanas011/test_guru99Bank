package guru99Bank;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	public static void login(WebDriver driver) {
		driver.findElement(By.name("uid")).sendKeys(Params.USERNAME);
		driver.findElement(By.name("password")).sendKeys(Params.PASSWORD);
		driver.findElement(By.name("btnLogin")).click();
	}

	public static void loginFromExcel(WebDriver driver, int i, List<String> values) throws IOException {
		if (i % 2 == 0) {
			driver.findElement(By.name("uid")).clear();
			driver.findElement(By.name("uid")).sendKeys(values.get(i));
		} else {
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys(values.get(i));
			driver.findElement(By.name("btnLogin")).click();
		}
	}
}
