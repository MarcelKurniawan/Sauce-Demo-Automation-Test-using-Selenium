import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ItemSortPage {
    WebDriver driver;

    public ItemSortPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSortOption(String option) {
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByVisibleText(option);
    }

    public double getItemPrice(int itemIndex) {
        WebElement itemPriceElement = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[" + itemIndex + "]"));
        return Double.parseDouble(itemPriceElement.getText().substring(1));
    }
}