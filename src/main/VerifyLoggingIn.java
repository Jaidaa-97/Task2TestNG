package main;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyLoggingIn {
	private WebDriver driver;
	private final String baseUrl = "https://devwcs.ballarddesigns.com/";

	@BeforeClass
	public void setup() {
		// Set the path to the ChromeDriver executable

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");

		// Initialize ChromeDriver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver(options);

		// Maximize the browser window
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void checkingLogo() {
		// Open the website
		driver.get(baseUrl);

		try {
			// Find the logo element by its class name
			WebElement logoElement = driver.findElement(By.className("logo-anchor"));

			// Check if the logo is displayed
			if (logoElement.isDisplayed()) {
				System.out.println("Logo is displayed.");
			} else {
				System.out.println("Logo is not displayed.");
			}
		} catch (NoSuchElementException e) {
			// Handle the case where the logo element with the provided class name is not
			// found
			System.out.println("Logo element with class name 'c-logo' not found.");
		}
	}

	@Test(priority = 2)
	public void login() throws InterruptedException {
		// Open the website
		driver.get(baseUrl);


		try {
			// Find the login
			WebElement loginLink = driver.findElement(By.cssSelector("#login"));

			// Click the login link
			loginLink.click();

		} catch (Exception e) {
			// Handle the case where the login link is not found
			System.out.println("Login link not found: " + e.getMessage());
		}
		
		// Verify if the user navigate to the sign in page
		WebElement SignInPage = driver.findElement(By.cssSelector("#BDLoginMessageNormal"));
		
		// Check if the user navigate to the log in page
		if (SignInPage.isDisplayed()) {
			System.out.println("Welcome to the sign in page");
			SignInPage.click();
		} else {
			System.out.println("Retry again.");
		}

		// Pass the email and password then click on the login button

		WebElement emailInput = driver.findElement(By.cssSelector("#logonId"));
		emailInput.click();
		emailInput.sendKeys("hs@hs.com");

		WebElement passwordInput = driver.findElement(By.cssSelector("#logonPassword"));
		passwordInput.click();
		passwordInput.sendKeys("123456he");

		// Click on the Log in button
		WebElement LogInButton = driver.findElement(By.cssSelector("#logonButton"));

		// Click on the Log in button
		LogInButton.click();
	}

	@Test(priority = 3)
	public void CheckLink() throws InterruptedException {
		driver.get(baseUrl);

		try {
			// Find the "SignIn/Register" span element
			WebElement spanLogIn = driver.findElement(By.cssSelector("#login a span"));

			// Get the text value of the <span> element
			String loginText = spanLogIn.getText();

			// Verify that the link text is not equal to "SignIn/Register"
			Assert.assertNotEquals(loginText, "SignIn/Register", "Link text should not be 'SignIn/Register'.");

			// Find the <span> element by its CSS selector
			WebElement myAccountAnchor = driver.findElement(By.cssSelector("a[title='My Account']"));

			// Check if the "My Account" span is displayed
			boolean isMyAccountDisplayed = myAccountAnchor.isDisplayed();
			

			// Check both conditions and print the appropriate message
			if (isMyAccountDisplayed && !loginText.equals("SignIn/Register")) {
				System.out.println("You are signed in successfully");
			}
		} catch (Exception e) {
			// Handle the case where the elements are not found
			System.out.println("Error: " + e.getMessage());
		};
	}
	
	@Test(priority = 4)
	public void CheckFirstName() throws InterruptedException {
		driver.get(baseUrl);
		// Locate the "My Account" link by its CSS selector
		WebElement myAccountLink = driver.findElement(By.cssSelector("#myAccount a[title='My Account']"));

		// Click on the "My Account" link
		myAccountLink.click();
		
		// Click on the "Address Book" link
		WebElement adressBookLink = driver.findElement(By.linkText("Address Book"));
		adressBookLink.click();
		
		Thread.sleep(10000);
		
		// Fetch the First name
		WebElement firstName = driver.findElement(By.cssSelector("#gwt_address_display_panel > div > div.gwt-addrbk-addrlist > div > div.gwt-addrbk-addrpanel > div:nth-child(2)"));

		// Get the text from the element
		String firstNameText = firstName.getText();
		
		// Fetch the Welcome text
		WebElement welcomeName = driver.findElement(By.cssSelector("#welcome"));

		// Get the text from the element
		String welcomeNameText = welcomeName.getText();
		
		// Save the expected welcome text 
		String expectedWelcomeText = "Welcome" + "," + " " + firstNameText + "!";
		
		// Check if welcomeNameText equals the expected welcome text
		if (welcomeNameText.equals(expectedWelcomeText)) {
		    System.out.println("The First name is correct");
		} else {
		    System.out.println("The First name is incorrect. Expected: " + expectedWelcomeText + ", Actual: " + welcomeNameText);
		}

		
	}
	@AfterClass
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}
