package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.util.List;
import java.util.Random;

public class HomePage {

    WebDriver driver;
    final static Logger logger = Logger.getLogger(HomePage.class);

    By txt_searchBar = By.name("k");
    By btn_search = By.className("hKfdXF");
    By btn_pageNext = By.cssSelector(".next-link>a");
    By txt_currentPage = By.cssSelector(".clearfix>.selected>a");
    By list_product = By.cssSelector(".catalog-view.clearfix.products-container>li");
    By logo_page = By.cssSelector(".sc-1fjiks5-1.gMtcKW>div>img");
    By btn_Basket = By.cssSelector(".basket-title");
    By btn_addItem = By.xpath("//div[@id='sp-addbasket-button']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void checkHomePage(){
        Assert.assertNotNull(logo_page);
        logger.info("HomePage checked");
    }

    public void passBasketPage(){
        driver.findElement(btn_Basket).click();
    }

    public void searchItem(String itemName){
        driver.findElement(txt_searchBar).click();
        driver.findElement(txt_searchBar).sendKeys(itemName);
        driver.findElement(btn_search).click();
    }

    public void searchPassTwoPage(){
        driver.findElement(btn_pageNext).click();
    }

    public void checkSecondPage(){
        int actualNumber = Integer.parseInt(driver.findElement(txt_currentPage).getText());
        Assert.assertEquals(2,actualNumber);
        logger.info("Second search page is success.");
    }

    public void selectRandomProduct() {
        List<WebElement> allProducts = driver.findElements(list_product);
        Random random = new Random();
        allProducts.get(random.nextInt(allProducts.size())).click(); //Navigate random product's page
        addItemToBasket();
    }

    public void addItemToBasket() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,200)");
        driver.findElement(btn_addItem).click();
    }

}
