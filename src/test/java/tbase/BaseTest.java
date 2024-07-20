package tbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DriverManager;
import utilities.ExcelReader;

public class BaseTest {

	public WebDriver driver;
	public static final Logger log = LogManager.getLogger();
	public Properties Config = new Properties();
	public FileInputStream fis, results;
	public ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	public WebDriverWait wait;
	static WebElement dropdown;
	public static SoftAssert sa;

	public void setUp(String browserName) throws InterruptedException {	

		Configurator.initialize(null, ".\\src\\test\\resources\\properties\\log4j2.properties");

		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\Config.properties");
			results = new FileInputStream(".\\src\\test\\resources\\properties\\ExpectedResults.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			Config.load(results);
			log.debug("Config & Expected Results Properties loaded");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sa = new SoftAssert();

		if (browserName.equals("chrome")) {

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			DriverManager.tdriver.set(driver);
			log.info("Started test in Chrome");

		} else if (browserName.equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Started test in firefox.");

		} else if (browserName.equals("iexplorer")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new InternetExplorerDriver();
			log.info("Started test in internet explorer.");

		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			String userDataDir = "C:\\Users\\rkumar\\AppData\\Local\\Microsoft\\Edge\\User Data\\Work";
			options.addArguments("user-data-dir=" + userDataDir );
			options.addArguments("--start-maximized");
			driver = new EdgeDriver(options);
			Thread.sleep(3000);
			log.info("Started test in microsoft edge.");
		}
		try {
		driver.get(Config.getProperty("TEST_URL"));
		log.info("Navigating to the URL : " + Config.getProperty("TEST_URL"));
		Thread.sleep(3000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(Config.getProperty("IMPLICIT_WAIT"))));
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Config.getProperty("EXPLICIT_WAIT"))));
		log.debug("waiting for page to load after browser launched");
		}catch(Exception e) {
			
		}
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		log.info("Test Execution completed, quitting");
		driver.quit();
	}

	public static Logger getLog() {
		return log;
	}

	public void verifyTableEntities(WebElement table, String[] Expected_Val) {

		List<WebElement> trows = table.findElements(By.tagName("tr"));
		Map<String, String> valueStore = new HashMap<String, String>();
		for (int i = 0; i < trows.size(); i++) {
			List<WebElement> temp = trows.get(i).findElements(By.tagName("td"));
			String[] temps = new String[2];
			for (int j = 0; j < temp.size(); j++) {
				temps[j] = temp.get(j).getText();
				valueStore.put(temps[0], temps[1]);
			}
		}
		for (int k = 0; k < Expected_Val.length; k++) {
			log.info("Validating available pets in the given corresponding tables");
			sa.assertEquals(valueStore.get(valueStore.keySet().toArray()[k]), Expected_Val[k]);
		}

	}
}
