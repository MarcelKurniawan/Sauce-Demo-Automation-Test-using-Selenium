import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ShoppingCartPage {
    WebDriver driver;
    WebDriverWait wait;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addToCart(int itemIndex) {
        WebElement addToCartButton = driver.findElement(By.xpath("(//button[text()='ADD TO CART'])[" + itemIndex + "]"));
        addToCartButton.click();
    }

    public void goToCart() {
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));
        cartIcon.click();
    }

    public void proceedToCheckout() {
        WebElement checkoutButton = driver.findElement(By.xpath("//a[text()='CHECKOUT']"));
        checkoutButton.click();
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
        driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();
    }

    public void finishCheckout() {
        WebElement finishButton = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]"));
        finishButton.click();
    }

    public String getSuccessHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        WebElement successHeader = driver.findElement(By.className("complete-header"));
        return successHeader.getText();
    }
}
