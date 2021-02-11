package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BasketPage {

    WebDriver driver;
    final static Logger logger=Logger.getLogger(BasketPage.class);

    String txt_checkBasket = "Sepetinizde ürün bulunmamaktadır.";

    By btn_amountItem = By.cssSelector(".gg-input.gg-input-select>.amount");
    By btn_deleteItem = By.cssSelector(".row>.btn-delete.btn-update-item.hidden-m");
    By txt_basketItemPrice = By.cssSelector(".product-item-box.product.selected>.padding-none>.pull-right-m>.total-price-box>.total-price");
    By txt_checkEmptyBasket = By.cssSelector(".gg-w-22.gg-d-22.gg-t-21.gg-m-18>h2");

    public BasketPage(WebDriver driver){
        this.driver = driver;
    }

    public void addProductNumber() throws InterruptedException {
        driver.findElement(btn_amountItem).click();
        driver.findElement(btn_amountItem).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(btn_amountItem).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        int obj = Integer.parseInt(driver.findElement(btn_amountItem).getAttribute("value"));
        Assert.assertEquals(2,obj);
    }

    public void comparePrices(String price){
        List<WebElement> elements = driver.findElements(txt_basketItemPrice);
        for (WebElement e: elements){
            if (e.getText().equals(price)){
                logger.info("Item prices are equal.");
                break;
            }else{
                logger.info("Item prices are not equal.");
            }
        }
    }

    public void deleteProduct(String id) throws InterruptedException {
        List<WebElement> elements = driver.findElements(btn_deleteItem);
        for (WebElement e: elements){
            if (e.getAttribute("data-id").equals(id)){
                driver.findElement(btn_deleteItem).click();
                Thread.sleep(1000);
                logger.info("Item deleted.");
                Assert.assertEquals(driver.findElement(txt_checkEmptyBasket).getText(),txt_checkBasket);
            }
        }
    }

}
