package Testng1.Testng1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class Assertfalse {
    
    @Test
    public void testLoginAndNavigateToMyInfo() {
        ChromeDriver driver = null;
        
        try {
            // Setup WebDriver
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            
            // Navigate to OrangeHRM
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            
            // Explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            
            // LOGIN STEPS
            System.out.println("Logging in...");
            
            // Enter username
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys("Admin");
            
            // Enter password
            driver.findElement(By.xpath("//input[@placeholder='Password']"))
                .sendKeys("admin123");
            
            // Click login button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Login']")));
            loginButton.click();
            
            // Wait for dashboard to load
            wait.until(ExpectedConditions.urlContains("dashboard"));
            System.out.println("Login successful! Dashboard loaded.");
            
            // NAVIGATE TO MY INFO PAGE
            System.out.println("Navigating to My Info page...");
            
            // Wait for the main menu to be visible
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//ul[contains(@class, 'oxd-main-menu')]")));
            
            // Click on "My Info" menu item - Using text-based selector (most reliable)
            WebElement myInfoMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='My Info']")));
            myInfoMenu.click();
            
            // Alternative locators if the above doesn't work (uncomment if needed):
            // driver.findElement(By.cssSelector(".oxd-main-menu-item.active")).click();
            // driver.findElement(By.xpath("//a[contains(@class, 'oxd-main-menu-item')][contains(text(),'My Info')]")).click();
            
            // Wait for My Info page to load - verify by page header
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h6[normalize-space()='Personal Details']")));
            
            System.out.println("Successfully navigated to My Info page!");
            
            // VERIFY MY INFO PAGE ELEMENTS (Optional assertions)
            // Check if we're on the correct page
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("viewPersonalDetails"), 
                "Should be on Personal Details page. Current URL: " + currentUrl);
            
            // Verify personal details form is present
            boolean firstNameFieldPresent = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.name("firstName"))).isDisplayed();
            Assert.assertTrue(firstNameFieldPresent, "First name field should be displayed");
            
            System.out.println("Personal Details form verified!");
            
            // INTERACT WITH MY INFO PAGE (Example: Get current values)
            try {
                // Get current employee details (if any)
                String firstName = driver.findElement(By.name("firstName")).getAttribute("value");
                String lastName = driver.findElement(By.name("lastName")).getAttribute("value");
                String employeeId = driver.findElement(By.xpath(
                    "//label[contains(text(),'Employee Id')]/following::input[1]")).getAttribute("value");
                
                System.out.println("Current Employee Details:");
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("Employee ID: " + employeeId);
                
            } catch (Exception e) {
                System.out.println("Could not retrieve all employee details: " + e.getMessage());
            }
            
            // EXAMPLE: Update a field (uncomment to test)
            /*
            // Clear and update first name
            WebElement firstNameField = driver.findElement(By.name("firstName"));
            firstNameField.clear();
            firstNameField.sendKeys("Updated");
            
            // Click Save button
            driver.findElement(By.xpath("//button[@type='submit'][normalize-space()='Save']")).click();
            
            // Wait for save confirmation
            Thread.sleep(2000);
            System.out.println("Personal details updated!");
            */
            
            System.out.println("Test completed successfully!");
            
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        } finally {
            // Close browser
            if (driver != null) {
                // Uncomment the next line to close the browser after test
                // driver.quit();
                System.out.println("Browser closed.");
            }
            
         boolean   notseelected=driver.findElement(By.className("oxd-table")).isSelected();
         
         Assert.assertFalse(notseelected);
         
         
        boolean enable=driver.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit'][normalize-space()='Save']")).isEnabled();
         
         Assert.assertTrue(enable);
        }
    }
}