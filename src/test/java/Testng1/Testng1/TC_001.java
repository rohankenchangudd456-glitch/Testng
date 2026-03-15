package Testng1.Testng1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class TC_001 {
    
    @Test
    public void launchGooglePage() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        
        try {
            // Navigate to the OrangeHRM site
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.manage().window().maximize();
            
            // Explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Login steps
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
            driver.findElement(By.name("username")).sendKeys("Admin");
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
            
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Login']")));
            driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
            
            // Wait for dashboard to load - using URL check
            wait.until(ExpectedConditions.urlContains("dashboard"));
            
            // Wait for main menu to be present (FIX FOR YOUR ERROR)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[contains(@class, 'oxd-main-menu')]")));
            
            // Click on Admin menu
            driver.findElement(By.xpath("//span[normalize-space()='Admin']")).click();
            
            // Wait for Admin page header
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h6[normalize-space()='Admin']")));
            
            System.out.println("Successfully navigated to Admin page!");
            
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        } finally {
            // Close browser after test
            driver.quit();
        }}
        
        @Test
        public void test12()
        {
        	System.out.println(" needed");
        }
    
}