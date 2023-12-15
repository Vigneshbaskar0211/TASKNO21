package seleniumTask21;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowHandlingTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Setting up Chrome WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testWindowHandling() throws InterruptedException {
        // Navigate to the URL
        driver.get("https://the-internet.herokuapp.com/windows");

        // Click the "Click Here" button to open a new window
        WebElement clickHereButton = driver.findElement(By.linkText("Click Here"));
        clickHereButton.click();

        // Switch to the newly opened window
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Verify that the text "New Window" is present on the page
        String newWindowText = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(newWindowText, "New Window", "New Window text is not present");
        
        Thread.sleep(2000);

        // Close the new window
        driver.close();

        // Switch back to the original window
        driver.switchTo().window(driver.getWindowHandles().iterator().next());

        // Verify that the original window is active
        Assert.assertTrue(driver.getTitle().contains("The Internet"), "Original window is not active");
        
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser instance
        if (driver != null) {
            driver.quit();
        }
    }
}
