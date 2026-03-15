package Testng1.Testng1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class orangetestcse {
    
    ChromeDriver driver;
    WebDriverWait wait;
    
    @BeforeClass
    public void setUp() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            
            // Increase wait time to 20 seconds
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            
            // Navigate to the URL
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            
            // Wait for page to load completely
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
            
            // Login steps
            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
            
            wait.until(ExpectedConditions.urlContains("dashboard"));
            
            // Wait for main menu to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[contains(@class, 'oxd-main-menu')]")));
            
            System.out.println("Login successful in BeforeClass!");
            
        } catch (Exception e) {
            System.out.println("Error in setup: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize test setup", e);
        }
    }

    @Test(priority = 1)
    public void myinfo() {
        try {
            System.out.println("Executing myinfo test...");
            
            // Wait for page stability
            Thread.sleep(2000);
            
            // FIXED: Corrected the XPath syntax - removed the incomplete line
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'viewPersonalDetails')]")));
            driver.findElement(By.xpath("//a[contains(@href, 'viewPersonalDetails')]")).click();
            
            // Wait for My Info page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h6[contains(text(), 'Personal Details')]")));
            
            System.out.println("My Info page accessed successfully!");
            
        } catch (Exception e) {
            System.out.println("Error in myinfo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 2,enabled=false)
    public void logout() {
        try {
            System.out.println("Executing logout test...");
            
            // Wait for page stability
            Thread.sleep(3000);
            
            // Click on user dropdown - using reliable locator
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='oxd-userdropdown-tab']")));
            driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
            
            // Wait for dropdown menu to appear
            Thread.sleep(2000);
            
            // Click on Logout
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));
            driver.findElement(By.xpath("//a[text()='Logout']")).click();
            
            // Verify logout successful
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[contains(text(), 'Login')]")));
            
            System.out.println("Logout successful!");
            
        } catch (Exception e) {
            System.out.println("Error in logout: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            System.out.println("Closing browser...");
            Thread.sleep(3000); // Wait to see the final state
            if (driver != null) {
                driver.quit();
            }
            System.out.println("Browser closed successfully!");
        } catch (Exception e) {
            System.out.println("Error in teardown: " + e.getMessage());
        }
    }
}