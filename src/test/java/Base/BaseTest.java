package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
	
	
	
	public WebDriver driver;
	 public WebDriverWait wait;
	 private String username = "bagwanshirina";
	 private String accessKey = "LT_BNbvSUVqPWPe8J2PUpCpVqzjJlwkQKev2OebtNvoAXz2I1a";

	 @BeforeMethod
	 @Parameters({"browser", "version", "platform", "URL"})
	 public void setUp(String browser, String version, String platform, String siteUrl) throws Exception {
	   DesiredCapabilities caps = new DesiredCapabilities();
	   caps.setCapability("browserName", browser);
	   caps.setCapability("browserVersion", version);
	   HashMap<String, Object> lt = new HashMap<>();
	   lt.put("username", username);
	   lt.put("accessKey", accessKey);
	   lt.put("platformName", platform);
	   lt.put("project", "TestNG Assignment");
	   lt.put("build", "Selenium Advance TESTMU AITest");
	   lt.put("network", true);
	   lt.put("video", true);
	   lt.put("visual", true);
	   lt.put("console", true);
	   lt.put("screenshot", true); 
	   lt.put("w3c", true);
	   lt.put("plugin", "java-testNG");
	   lt.put("accessibility", true);
	   lt.put("name", browser + "-test");
	   caps.setCapability("LT:Options", lt);

	   driver = new RemoteWebDriver(new URL("http://hub.lambdatest.com/wd/hub"), caps);
	   wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   driver.manage().window().maximize();
	   driver.get(siteUrl);
	 }

	 @AfterMethod
	 public void tearDown() {
	   if (driver != null) driver.quit();
	 }
}





