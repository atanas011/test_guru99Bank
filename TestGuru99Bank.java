package guru99Bank;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestGuru99Bank {

	static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", Params.CHROME_PATH);

		ChromeOptions testProfile = new ChromeOptions();
		// https://www.partitionwizard.com/partitionmagic/disable-software-reporter-tool.html
		testProfile.addArguments("user-data-dir=" + Params.PROFILE_PATH);
//		options.addArguments("--start-maximized");
		driver = new ChromeDriver(testProfile);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testLogin() {
		driver.get(Params.HOME_URL);
		Login.login(driver);
		String act = driver.getTitle();
		String exp = Params.DASHBOARD_TITLE;
		Assert.assertEquals(act, exp);
	}

	@Test
	public void testLoginFromExcel() throws IOException, InterruptedException {
		driver.get(Params.HOME_URL);
		SoftAssert sa = new SoftAssert();
		String filePath = System.getProperty("user.dir");
		List<String> values = ReadData.readExcel(filePath, "bank_users.xlsx", "userData");

		for (int i = 0; i < values.size(); i++) {
			Login.loginFromExcel(driver, i, values);
			if (i == 1) {
				String act = driver.getTitle();
				String exp = Params.DASHBOARD_TITLE;
				sa.assertEquals(act, exp);
				driver.navigate().back();
			}
			if (i > 1 && i % 2 != 0) {
				String txt = driver.switchTo().alert().getText();
				sa.assertEquals(txt, Params.ALLERT_TXT);
				driver.switchTo().alert().accept();
			}
		}
//		sa.assertAll();
	}

	@AfterClass
	public void endSession() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
