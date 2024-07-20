package testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.DashBoard;
import pages.PetStoreHome;
import pages.StoreEntry;
import tbase.BaseTest;
import utilities.DataUtil;

public class StoreEntryTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void VerifyStoreEntryTitle(String browserName, String runmode) throws InterruptedException {
		if (runmode.equals("N")) {
			log.info("Test Skipped");
			throw new SkipException("Skipping the test as the run mode is NO");
		} else {
			try {
				setUp(browserName);
				PetStoreHome psh = new PetStoreHome(driver);
				StoreEntry se = psh.enterStore();
				log.info("Validation of landing to Dashboard completed without");
				sa.assertEquals(se.getPageTitle(), Config.getProperty("EXPECTED_STOREENTRY_TITLE"));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sa.assertAll();
		}

	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void VerifySignInButtonPresence(String browserName, String runmode) throws InterruptedException {

		if (runmode.equals("N")) {
			log.info("Test Skipped");
			throw new SkipException("Skipping the test as the run mode is NO");
		} else {
			try {
				setUp(browserName);
				PetStoreHome psh = new PetStoreHome(driver);
				StoreEntry se = psh.enterStore();
				log.info("Validation of SignIn button availability at StoreEntry page");
				sa.assertEquals(se.SignIn.getText(), Config.getProperty("EXPECTED_SIGN_IN_TEXT"));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sa.assertAll();
		}
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void Verifylogin(String browserName, String runmode, String username, String pwd) {

		if (runmode.equals("N")) {
			log.info("Test Skipped");
			throw new SkipException("Skipping the test as the run mode is NO");

		} else {
			try {

				setUp(browserName);
				PetStoreHome psh = new PetStoreHome(driver);
				StoreEntry se = psh.enterStore();
				sa.assertEquals(se.SignIn.getText(), Config.getProperty("EXPECTED_SIGN_IN_TEXT"));
				se.click(se.SignIn);	
				Thread.sleep(3000);
				DashBoard dashboard = se.logontoDashBoard(username, pwd);
				Thread.sleep(3000);
				sa.assertEquals(dashboard.getPageTitle(), Config.getProperty("EXPECTED_DASHBOARD_TITLE"));
				sa.assertEquals(dashboard.getWelcomeContent(), Config.getProperty("EXPECTED_DASHBOARD_WELCOME_CONTENT"));
				log.info("Validation of landing to Dashboard after login");
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
			sa.assertAll();

		}

	}

}
