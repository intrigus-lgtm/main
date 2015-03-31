package functional.org.ojbc.web.portal.controllers;

import static functional.org.ojbc.web.portal.controllers.TestBase.getUrl;
import static functional.org.ojbc.web.portal.controllers.TestBase.waitForElement;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AdvanceSearchFunctionalTest {

	private WebDriver driver;

	@Before
	public void setup() {
		driver = new FirefoxDriver();
	}

	@After
	public void teardown() {
		driver.close();
	}

	@Test
	public void advanceSearchReturnsErrorWhenEmpty() throws InterruptedException {
		driver.get(getUrl("/portal/index"));
		assertThat(driver.getTitle(), is("OJBC Search"));

		waitForElement(By.linkText("ADVANCED SEARCH"), driver);
		driver.findElement(By.linkText("ADVANCED SEARCH")).click();

		driver.findElement(By.id("advanceSearch.personGivenName")).sendKeys("Steven");

		driver.findElement(By.id("advanceSearchSubmitButton")).click();
		waitForElement(By.className("error"), driver);
		assertThat(
		        driver.getPageSource(),
		        Matchers.containsString("Search must have either a last name or an identifier (SSN, SID, DL, or FBI Number)"));
	}

	@Test
	public void advanceSearchSuccess() throws InterruptedException {
		driver.get(getUrl("/portal/index"));
		assertThat(driver.getTitle(), is("OJBC Search"));

		waitForElement(By.linkText("ADVANCED SEARCH"), driver);
		driver.findElement(By.linkText("ADVANCED SEARCH")).click();

		driver.findElement(By.id("advanceSearch.personSurName")).sendKeys("Scott");
		driver.findElement(By.cssSelector("input[value='{http://ojbc.org/Services/WSDL/Person_Search_Request_Service/Criminal_History/1.0}Submit-Person-Search---Criminal-History']")).click();

		driver.findElement(By.id("advanceSearchSubmitButton")).click();

		waitForElement(By.id("search-results-title"), driver);

		assertThat(driver.getPageSource(), containsString("<b>Scott, Michael Gary</b>"));
	}

	

}
