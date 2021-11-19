package guru99Bank;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestGuru99Bank {

	static WebDriver driver;

	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", Params.CHROME_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=" + Params.PROFILE_PATH);
//		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
	}

	public static void main(String[] args) {
		setUp();
		driver.get(Params.HOME_URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("uid")).sendKeys(Params.USERNAME);
		driver.findElement(By.name("password")).sendKeys(Params.PASSWORD);
		driver.findElement(By.name("btnLogin")).click();

		String act = driver.getTitle();
		String exp = Params.DASHBOARD_TITLE;
		System.out.println(exp.equals(act) ? "Passed" : "exp Value is Wrong!");

		driver.quit();
	}
}
