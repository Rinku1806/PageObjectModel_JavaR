package testcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.PetStoreHome;
import tbase.BaseTest;
import utilities.DataUtil;

public class HomePageTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void VerifyHomePageTitle(String browserName, String runmode) throws InterruptedException {
		if (runmode.equals("N")) {
			log.info("Test Skipped");
			throw new SkipException("Skipping the test as the run mode is NO");
		} else {
			try {
				setUp(browserName);
				PetStoreHome psh = new PetStoreHome(driver);
				Assert.assertEquals(psh.getPageTitle(), Config.getProperty("EXPECTED_HOMEPAGE_TITLE"));
				log.info("Validated the home page title of Petstore");
			} catch (InterruptedException e) {
				e.printStackTrace();

			}

		}
	}
}
