import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ItemSortTest {
    WebDriver driver;
    private LoginPage loginPage;
    private ItemSortPage itemSortPage;

    @BeforeClass
    public void setup() {
        System.setProperty("chromedriver.exe", "C:\\Users\\Marcel\\Documents\\Selenium\\SeleniumLearn\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/v1/");
        loginPage = new LoginPage(driver);
        itemSortPage = new ItemSortPage(driver);
    }

    @Test
    public void testSorting() {
        // Login
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        // Select 'high to low' from dropdown
        itemSortPage.selectSortOption("Price (high to low)");

        // Get prices of first and second items
        double firstItemPrice = itemSortPage.getItemPrice(1);
        double secondItemPrice = itemSortPage.getItemPrice(2);

        // Assert that the first item price is higher than the second item price
        Assert.assertTrue(firstItemPrice > secondItemPrice, "The first item price is not higher than the second item price");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
