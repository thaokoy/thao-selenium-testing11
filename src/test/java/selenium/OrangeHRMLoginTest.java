package selenium;

//<input data-v-1f99f73c="" class="oxd-input oxd-input--active" name="username" placeholder="Username" autofocus="">
//flow tao test case
//b1: khoi tao trinh duyet
//b2: Dieu huong toi website muon test
//b3: tim cac element, locator (input, button, label,... )
// de phuc vu viet cac step test
//b4: nhap du lieu, thao tac tren element
//b5: verify ket qua (expect, actual )
//b6: dong trinh duyet va giai phong tai nguyen





import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRMLoginTest {
// b1: khoi tao trinh duyet
// driverla class giup tuongtac tren page
    private WebDriver driver;

    private WebDriverWait wait;

//    URL cua page login
    private static final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

//    username
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin123";

//    setup moi truong test
//    before method: chay truoc moi test case
//    VD: khoi tao driver
    @BeforeMethod
    public void setUp() {
//  B1: Setup chrome driver voi webdriver manager
        WebDriverManager.chromedriver().setup();
//        B2: cau hinh chrome
        ChromeOptions options = new ChromeOptions();
//        mo brower o che do full screen
//        dam bao tat ca cac elements deu hien thi
//        test nhat quan tren moi man hinh
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);
//        B3: Co thoi gian doi Chrome setup
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }
@Test(description = "Test Login thanh cong")
    public void testLoginSuccess() throws InterruptedException {
//        b1: truy cap trang web Login
        driver.get(LOGIN_URL);
        Thread.sleep(15000);
//        b2: tim element input username va fill username vao
        WebElement usernameField  = driver.findElement(By.xpath("//input[@name='username']"));
        usernameField.sendKeys(USERNAME);
        Thread.sleep(5000);

//        By usernameInput = By.name("username");
//        By passwordInput = By.name("password");
//        By loginButton   = By.xpath("//button[@type='submit']");
//        b3: tim element input password vaf fill password
        WebElement passwordField  = driver.findElement(By.xpath("//input[@name='password']"));
    passwordField.sendKeys(PASSWORD);
        Thread.sleep(5000);

//        b4: tim element button login va clic vao button
        WebElement loginBtn  = driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();
        Thread.sleep(5000);

//        b5: verify ket qua sau khi thao tac login
//        TH1: kiem tra url co chua "dashboard" khong?
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("dashboard"),
                "URL phai chua 'dashboard' sau khi login"
        );

    }

//    after method: cleanup- dong browser, giai phong tai nguyen
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }
}
