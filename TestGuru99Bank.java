package guru99Bank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGuru99Bank {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\bin\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/V4/");
		driver.findElement(By.name("uid")).sendKeys("mngr367013");
		driver.findElement(By.name("password")).sendKeys("vYgetAm");
		driver.findElement(By.name("btnLogin")).click();
		System.out.println("Success!");
		driver.quit();
	}
}
