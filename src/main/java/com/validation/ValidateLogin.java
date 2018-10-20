package com.validation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.common.Constants;

import org.junit.Assert;

/*
 * This Class will validate the website login test cases
 * @version 0.1
 * @author Aditi
 */

public class ValidateLogin {
	private WebDriver driver;
	private String baseUrl;
	static Logger log = Logger.getLogger(ValidateLogin.class);
	static Properties prop = new Properties();
	static {
		BasicConfigurator.configure();
		log.info("Inside Static Block");
		InputStream input = null;
		String workspace = System.getProperty("user.dir");
		try {
			input = new FileInputStream(workspace + "/src/main/resources/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException in ValidateLogin:", e);
		} catch (IOException e) {
			log.error("IOException in ValidateLogin:", e);
		}

	}

	/*
	 * This Setup() method will set the driver and navigate to url -
	 * url- "https://www.virgingames.com"
	 */
	@Before
	public void setUp() throws Exception {
		log.info("Inside setUp()");
		driver = new ChromeDriver();
		baseUrl = prop.getProperty("baseUrl");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/*
	 * This Method will close or quit the driver
	 */
	@After
	public void tearDown() throws Exception {
		log.info("Inside tearDown() closing the driver");
		driver.close();
		driver.quit();
		log.info("Driver closed successfully");
	}

	/*
	 * This Method will Return void and assert true When expected error message is
	 * received by providing invalid format for username and password 
	 * Expected error - "Your username/email must be 4 to 60 characters long" 
	 * username - abc
	 * password - abc
	 */
	@Test
	public void shouldReturnErrorWhenInvalidFormatIsProvided() throws IOException {
		log.info("Validating shouldReturnErrorWhenInvalidFormatIsProvided()");
		// driver navigate to url
		driver.navigate().to(baseUrl);
		// search the login button and click
		WebElement webElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/header/div/span/a[1]"));
		webElement.click();

		// input username
		driver.findElement(By.xpath("//*[@id=\"field-username\"]")).sendKeys(prop.getProperty("invalidformatusername"));
		// input password
		driver.findElement(By.xpath("//*[@id=\"field-password\"]")).sendKeys(prop.getProperty("invalidformatpwd"));
		// click on login button
		driver.findElement(
				By.xpath("//*[@id=\"app-main\"]/div/div/div[1]/div/main/div/div/div/form/fieldset/ol/li[4]/button"))
				.click();

		// expected message
		String ExpectedString = Constants.INVALIDFORMATEXPECTEDSTRING;

		// find the error message displayed
		WebElement webError = driver
				.findElement(By.xpath("//*[@id=\"app-main\"]/div/div/div[1]/div/main/div/div/div/form/div[3]/div"));

		// Assert the error text message with expected condition
		Assert.assertTrue(webError.getText().equals(ExpectedString));
		log.info("Validated shouldReturnErrorWhenInvalidFormatIsProvided()");
	}

	/*
	 * This Method will Return void and assert true When expected error message is
	 * received with no password 
	 * Expected error - "Both your username and password are required" 
	 * username - abc 
	 * password - <blank>
	 */
	@Test
	public void shouldReturnErrorWhenNoPasswordProvided() throws IOException {
		log.info("Validating shouldReturnErrorWhenNoPasswordProvided()");
		driver.navigate().to(baseUrl);
		WebElement webElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/header/div/span/a[1]"));
		webElement.click();
		driver.findElement(By.xpath("//*[@id=\"field-username\"]")).sendKeys(prop.getProperty("invalidformatusername"));
		driver.findElement(
				By.xpath("//*[@id=\"app-main\"]/div/div/div[1]/div/main/div/div/div/form/fieldset/ol/li[4]/button"))
				.click();
		String ExpectedString = Constants.NOPWDEXPECTEDSTRING;
		WebElement webError = driver
				.findElement(By.xpath("//*[@id=\"app-main\"]/div/div/div[1]/div/main/div/div/div/form/div[3]/div"));
		Assert.assertTrue(webError.getText().equals(ExpectedString));
		log.info("Validated shouldReturnErrorWhenNoPasswordProvided()");
	}

	/*
	 * This Method will Return void and assert true When expected error message is
	 * received by providing invalid credentials 
	 * Expected error - "Please try again, your username/email or password has not been recognised"
	 * username - gargaditi0120@gmail.com 
	 * password - 12345678
	 */
	@Test
	public void shouldReturnErrorWhenInvalidCredentialsProvided() throws IOException {
		log.info("Validating shouldReturnErrorWhenInvalidCredentialsProvided()");
		driver.navigate().to(baseUrl);
		WebElement webElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/header/div/span/a[1]"));
		webElement.click();
		driver.findElement(By.xpath("//*[@id=\"field-username\"]")).sendKeys(prop.getProperty("invalidcredusername"));
		driver.findElement(By.xpath("//*[@id=\"field-password\"]")).sendKeys(prop.getProperty("invalidcredpwd"));
		driver.findElement(
				By.xpath("//*[@id=\"app-main\"]/div/div/div[1]/div/main/div/div/div/form/fieldset/ol/li[4]/button"))
				.click();
		String ExpectedString = Constants.INVALIDCREDEXPECTEDSTRING;
		WebElement webError = driver
				.findElement(By.xpath("//*[@id=\"app-main\"]/div/div/div[1]/div/main/div/div/div/form/div[3]/div"));
		Assert.assertTrue(webError.getText().equals(ExpectedString));
		log.info("Validated shouldReturnErrorWhenInvalidCredentialsProvided()");
	}

	/*
	 * This Method will Return void and assert true When expected error message is
	 * received by providing invalid password length 
	 * Expected error - "Your password must be 6 to 16 characters long" 
	 * username - gargaditi0120@gmail.com 
	 * password - 123
	 */
	@Test
	public void shouldReturnErrorWhenPwdLengthNotExpected() throws IOException {
		log.info("Validating shouldReturnErrorWhenPwdLengthNotExpected()");
		driver.navigate().to(baseUrl);
		WebElement webElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/header/div/span/a[1]"));
		webElement.click();
		driver.findElement(By.xpath("//*[@id=\"field-username\"]")).sendKeys(prop.getProperty("invalidcredusername"));
		driver.findElement(By.xpath("//*[@id=\"field-password\"]")).sendKeys(prop.getProperty("invalidlengthpwd"));
		driver.findElement(
				By.xpath("//*[@id=\"app-main\"]/div/div/div[1]/div/main/div/div/div/form/fieldset/ol/li[4]/button"))
				.click();
		String ExpectedString = Constants.PWDLENGTHEXPECTEDSTRING;
		WebElement webError = driver
				.findElement(By.xpath("//*[@id=\"app-main\"]/div/div/div[1]/div/main/div/div/div/form/div[3]/div"));
		Assert.assertTrue(webError.getText().equals(ExpectedString));
		log.info("Validated shouldReturnErrorWhenPwdLengthNotExpected()");
	}
}
