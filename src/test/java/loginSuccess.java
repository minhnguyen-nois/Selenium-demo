import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
//import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class loginSuccess { ;

    @Test
    void testLoginSuccessfully() {
        // Tắt log cảnh báo - PHẢI NẰM TRONG METHOD
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        WebDriver driver = new ChromeDriver();
        // Increase the waiting time to 15s to handle slower loading times
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.manage().window().maximize();
            driver.get("https://dev.agentiqai.ai/auth/sign-in");

            // Input Email - use wait to wait for the element to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("minhnguyen@agentiqai.ai");

            // Input Password
            driver.findElement(By.name("password")).sendKeys("minhnguyen");

            // Click button Sign In
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sign In']")));
            signInButton.click();

            // Verify login success by checking the presence of an element on the dashboard
            WebDriverWait loginWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            loginWait.until(ExpectedConditions.urlContains("/dashboard"));

            // 2. Assert (Xác nhận) kết quả
            String currentUrl = driver.getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains("dashboard"),
                    "Đăng nhập thất bại! URL hiện tại là: " + currentUrl);

        } finally {
            // always close the browser
            driver.quit();
        }
    }

}