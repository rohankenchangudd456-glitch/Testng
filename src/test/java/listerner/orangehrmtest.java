package listerner;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class orangehrmtest {
    ChromeDriver driver;
    
    @Test(priority = 1)
    public void hrm() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }
    
    @Test(priority = 2)
    public void admin() {
        driver.findElement(By.xpath("//a[normalize-space()='Admin']")).click(); // Fixed XPath
        boolean isdisplayed = driver.findElement(By.xpath("//h5[normalize-space()='System Users']")).isDisplayed();
        Assert.assertTrue(isdisplayed);
    }
    
    @Test(priority = 3)
    public void logoot() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
    }
}