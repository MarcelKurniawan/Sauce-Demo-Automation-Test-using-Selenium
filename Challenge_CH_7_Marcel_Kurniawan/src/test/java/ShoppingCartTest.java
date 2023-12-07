import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ShoppingCartTest {
    WebDriver driver;
    WebDriverWait wait;
    private LoginPage loginPage;
    private ShoppingCartPage shoppingCartPage;


    @BeforeClass
    public void setup() {
        System.setProperty("chromedriver.exe", "C:\\Users\\Marcel\\Documents\\Selenium\\SeleniumLearn\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/v1/");
        loginPage = new LoginPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @Test
    public void testCheckoutProcess() {
        // Navigate to the website
        driver.get("https://www.saucedemo.com/v1/");

        // Login
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        // Add two different items to cart
        shoppingCartPage.addToCart(1);
        shoppingCartPage.addToCart(2);

        // Go to cart
        shoppingCartPage.goToCart();

        // Proceed to checkout
        shoppingCartPage.proceedToCheckout();

        // Fill in checkout information
        shoppingCartPage.fillCheckoutInfo("Marcel", "Kurniawan", "11480");

        // Finish the checkout
        shoppingCartPage.finishCheckout();

        // Validate success page
        Assert.assertEquals(shoppingCartPage.getSuccessHeader(), "THANK YOU FOR YOUR ORDER");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
