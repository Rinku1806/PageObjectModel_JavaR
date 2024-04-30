package testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.Birds;
import pages.Cats;
import pages.DashBoard;
import pages.Dogs;
import pages.Fish;
import pages.PetStoreHome;
import pages.Reptiles;
import pages.StoreEntry;
import tbase.BaseTest;
import utilities.DataUtil;

public class DashBoardTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void verifyAvailablePets(String browserName, String runmode) {
		if (runmode.equals("N")) {
			log.info("Test Skipped");
			throw new SkipException("Skipping the test as the run mode is NO");
		} else {
			try {
				setUp(browserName);
				PetStoreHome psh = new PetStoreHome(driver);
				StoreEntry se = psh.enterStore();
				se.click(se.SignIn);
				DashBoard db = se.logontoDashBoard("j2ee", "j2ee");
				sa.assertEquals(db.birds.getAttribute("href").split("=")[2], "BIRDS");
				log.info("Validation of birds in the store completed");
				sa.assertEquals(db.cats.getAttribute("href").split("=")[2], "CATS");
				log.info("Validation of cats in the store completed");
				sa.assertEquals(db.dogs.getAttribute("href").split("=")[2], "DOGS");
				log.info("Validation of dogs in the store completed");
				sa.assertEquals(db.fish.getAttribute("href").split("=")[2], "FISH");
				log.info("Validation of fish in the store completed");
				sa.assertEquals(db.reptiles.getAttribute("href").split("=")[2], "REPTILES");
				log.info("Validation of reptiles in the store completed");
				sa.assertAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void verifyAllPets(String browserName, String runmode) throws InterruptedException {

		if (runmode.equals("N")) {
			log.info("Test skipped");
			throw new SkipException("Skipping the test as the run mode is NO");
		} else {

			setUp(browserName);
			PetStoreHome psh = new PetStoreHome(driver);
			StoreEntry se = psh.enterStore();
			se.click(se.SignIn);
			Thread.sleep(3000);
			DashBoard db = se.logontoDashBoard("j2ee", "j2ee");
			Birds birds = db.gotoBirds();
			verifyTableEntities(birds.birdtable, Config.getProperty("EXPECTED_PET_BIRDS").split(","));
			db = birds.returnMainMenu();
			log.info("Validation of all breeds of birds in the store completed");
			Cats cats = db.gotoCats();
			verifyTableEntities(cats.cattable, Config.getProperty("EXPECTED_PET_CATS").split(","));
			db = cats.returnMainMenu();
			log.info("Validation of all breeds of cats in the store completed");
			Dogs dogs = db.gotoDogs();
			verifyTableEntities(dogs.dogtable, Config.getProperty("EXPECTED_PET_DOGS").split(","));
			db = dogs.returnMainMenu();
			log.info("Validation of all breeds of dogs in the store completed");
			Reptiles reps = db.gotoReptiles();
			verifyTableEntities(reps.repstable, Config.getProperty("EXPECTED_PET_REPTILES").split(","));
			db = reps.returnMainMenu();
			log.info("Validation of all breeds of reptiles in the store completed");
			Fish fish = db.gotoFish();
			verifyTableEntities(fish.fishtable, Config.getProperty("EXPECTED_PET_FISH").split(","));
			db = fish.returnMainMenu();
			log.info("Validation of all breeds of fish in the store completed");
		}
		sa.assertAll();
	}
}
