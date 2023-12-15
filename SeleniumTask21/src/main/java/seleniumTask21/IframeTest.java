package seleniumTask21;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IframeTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Initialize Chrome browser
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
    }
    

    @Test
    public void testIframe() throws InterruptedException {
        // Navigate to the URL
        driver.get("https://the-internet.herokuapp.com/iframe");

        // Switch to the iframe using its index (index starts from 0)
        driver.switchTo().frame(0); // Assuming there's only one iframe on the page

        // Locate the <p> tag inside the iframe and write the text "Hello People"
        WebElement paragraph = driver.findElement(By.tagName("p"));
        paragraph.clear(); // Clear existing text if any
        paragraph.sendKeys("Hello People");
        Thread.sleep(3000);
    }

    @AfterTest
    public void tearDown() {
        // Close the browser instance
        if (driver != null) {
            driver.quit();
        }
    }
}
