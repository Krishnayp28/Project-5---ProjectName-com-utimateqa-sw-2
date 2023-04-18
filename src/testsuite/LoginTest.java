package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //Click on sign in link
        WebElement signInLink = driver.findElement(By.xpath("//a[@href ='/users/sign_in']"));
        signInLink.click();
        String expectMessage = "Welcome Back!";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[@class = 'page__heading']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("welcome back message not displayed", expectMessage, actualMessage);
    }

    @Test
    public void verifyTheErrorMessage() {
        //Click on sign in link
        WebElement signInLink = driver.findElement(By.xpath("//a[@href ='/users/sign_in']"));
        signInLink.click();
        // Find the username field element
        WebElement usernameField = driver.findElement(By.id("user[email]"));
        // Type the invalid username to username field element
        usernameField.sendKeys("jignap@yahoo.co.uk");
        //Find the password field element and sent invalid password on password field
        driver.findElement(By.id("user[password]")).sendKeys("secret_sauce");
        // Find the login button element and click
        WebElement loginButton = driver.findElement(By.xpath("//button[@type= 'submit']"));
        loginButton.click();

        String expectedMessage = "Invalid email or password."; // Expected message from requirements
        String actualMessage = driver.findElement(By.xpath("//div[@id='notice']")).getText();// Find the text element and get the text
        Assert.assertEquals("Error Message was not displayed.", expectedMessage, actualMessage);// Validating actual and expected text
    }
    @After
    public void close(){
        closeBrowser();
    }
}
