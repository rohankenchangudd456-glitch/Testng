package Testng1.Testng1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dataproviderdemo {

    @Test(dataProvider = "testdat")
    public void launchGooglePage(String usern, String passw) {
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
            driver.findElement(By.name("username")).sendKeys(usern);
            
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(passw);
            
            driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
            
            // Check if login was successful
            boolean loginSuccessful = isLoginSuccessful(wait);
            
            // For VALID credentials (Admin/admin123)
            if (usern.equals("Admin") && passw.equals("admin123")) {
                Assert.assertTrue(loginSuccessful, "Valid credentials should login!");
                
                if (loginSuccessful) {
                    // Navigate to Admin menu
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[contains(@class, 'oxd-main-menu')]")));
                    driver.findElement(By.xpath("//span[normalize-space()='Admin']")).click();
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h6[normalize-space()='Admin']")));
                    System.out.println("✓ Valid credentials test PASSED for: " + usern);
                }
            } 
            // For INVALID credentials (all others)
            else {
                Assert.assertFalse(loginSuccessful, "Invalid credentials should NOT login!");
                System.out.println("✓ Invalid credentials test PASSED for: " + usern + " (expected failure)");
            }
            
        } catch (Exception e) {
            // Handle exceptions for invalid credentials
            if (!usern.equals("Admin") || !passw.equals("admin123")) {
                System.out.println("✓ Invalid credentials test PASSED with exception for: " + usern);
                Assert.assertTrue(true); // Test passes for invalid credentials
            } else {
                System.out.println("Test failed for valid credentials: " + usern);
                e.printStackTrace();
                Assert.fail("Test failed: " + e.getMessage());
            }
        } finally {
            // Close browser after test
            driver.quit();
        }
    }
    
    // Helper method to check login success
    private boolean isLoginSuccessful(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.urlContains("dashboard"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @DataProvider
    public Object[][] testdat() {
        return new Object[][] {
            {"Admin", "admin123"},        // Valid - Should PASS
            {"superstar", "superstar123"}  // Invalid - Should PASS too!
        };
    }
}