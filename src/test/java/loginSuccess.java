import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class loginSuccess {

    @Test
    void testLoginSuccessfully() {
        WebDriver driver = new ChromeDriver();
        // Tăng thời gian chờ lên 15 giây để đảm bảo trang load kịp
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.manage().window().maximize();
            driver.get("https://dev.agentiqai.ai/auth/sign-in");

            // Nhập Email - sử dụng wait để đợi ô nhập hiện ra
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("minhnguyen@gmail.com");

            // Nhập Password
            driver.findElement(By.name("password")).sendKeys("minhnguyen");

            // Click nút Sign In
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // XÁC MINH: Đợi cho đến khi URL thay đổi sang dashboard
            wait.until(ExpectedConditions.urlContains("/dashboard"));

            // Kiểm tra xem URL hiện tại có đúng là chứa dashboard không
            String currentUrl = driver.getCurrentUrl();
            org.junit.jupiter.api.Assertions.assertTrue(currentUrl.contains("dashboard"),
                    "Đăng nhập không thành công, URL hiện tại là: " + currentUrl);

        } finally {
            // Luôn đóng trình duyệt để không bị tốn RAM
            driver.quit();
        }
    }

}