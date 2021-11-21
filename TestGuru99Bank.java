package guru99Bank;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import frameworks.poi.ReadData;

public class TestGuru99Bank {

	static WebDriver driver;

	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", Params.CHROME_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=" + Params.PROFILE_PATH);
//		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get(Params.HOME_URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

	public static void main(String[] args) throws IOException {
		setUp();

		String filePath = System.getProperty("user.dir");
		List<String> values = ReadData.readExcel(filePath, "bank_users.xlsx", "userData");
//		System.out.println(values);

		for (int i = 0; i < values.size(); i++) {
			loginFromExcel(driver, i, values);
			if (i == 1) {
				String act = driver.getTitle();
				String exp = Params.DASHBOARD_TITLE;
				System.out.println(exp.equals(act) ? "Login Successful!" : "exp Value is Wrong!");
				driver.navigate().back();
			}
			if (i > 1 && i % 2 != 0) {
				String txt = driver.switchTo().alert().getText();
				System.out.println(
					"SS" + (i/2 + 1) + ": " + (txt.equals("User or Password is not valid") ? "Pass" : "Fail"));
				driver.switchTo().alert().accept();
			}
		}
		driver.quit();
	}
}
