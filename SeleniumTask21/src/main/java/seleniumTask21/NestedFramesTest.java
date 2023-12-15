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

public class NestedFramesTest {
    private WebDriver driver;
    private String baseUrl = "http://the-internet.herokuapp.com/nested_frames";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFrameContent() {
        driver.get(baseUrl);

        // Switch to the top frame
        driver.switchTo().frame("frame-top");

        // Verify there are three frames on the page
        int frameCount = driver.findElements(By.xpath("//frame")).size();
        Assert.assertEquals(frameCount, 3, "Expected 3 frames on the page");

        // Switch to the left frame
        driver.switchTo().frame(0); // Index 0 for left frame
        WebElement leftFrameText = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(leftFrameText.getText().contains("LEFT"), "Left frame text is not as expected");

        // Switch back to the top frame
        driver.switchTo().defaultContent();

        // Switch to the middle frame
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame(1); // Index 1 for middle frame
        WebElement middleFrameText = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(middleFrameText.getText().contains("MIDDLE"), "Middle frame text is not as expected");

        // Switch back to the top frame
        driver.switchTo().defaultContent();

        // Switch to the right frame
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame(2); // Index 2 for right frame
        WebElement rightFrameText = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(rightFrameText.getText().contains("RIGHT"), "Right frame text is not as expected");

        // Switch back to the top frame
        driver.switchTo().defaultContent();

        // Switch to the bottom frame
        driver.switchTo().frame("frame-bottom"); // If available
        WebElement bottomFrameText = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(bottomFrameText.getText().contains("BOTTOM"), "Bottom frame text is not as expected");

        // Switch back to the top frame
        driver.switchTo().defaultContent();

       // Verify that the page title is "Frames"
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "", "Page title is not as expected");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
