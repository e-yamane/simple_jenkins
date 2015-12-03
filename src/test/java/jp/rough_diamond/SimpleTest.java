package jp.rough_diamond;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=App.class)
@WebAppConfiguration
@IntegrationTest("server:port:0")
public class SimpleTest {
	private WebDriver driver;
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}

	@Value("${local.server.port}")
	int portNo;
	
	@Test
	public void test() {
		String url = "http://localhost:" + portNo + "/";
		System.out.println(url);
		driver.get(url);
		takeShot("初期表示");
	}

	protected void takeShot(String name) {
		try {
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile, new File(name + ".png"));
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
