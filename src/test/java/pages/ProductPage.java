package pages;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;
    BasketPage basketpage;
    HomePage homepage;

    String txt_pageProductId;
    String txt_price;

    By txt_productPrice = By.id("sp-price-highPrice");
    By txt_productId = By.cssSelector("#DescriptionTabProductIdContainer>#DescriptionTabProductId");

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void setUpPageDriver(){
        basketpage = new BasketPage(driver);
        homepage = new HomePage(driver);
    }

    public void getProductPrice(){
        txt_price = driver.findElement(txt_productPrice).getText();
    }

    public void getProductId(){
        txt_pageProductId = driver.findElement(txt_productId).getText() ;
    }

    public void deleteItemById() throws InterruptedException {
        basketpage = new BasketPage(driver);
        basketpage.deleteProduct(sendProductId());
    }

    public void checkProductPrices(){
        setUpPageDriver();
        homepage.passBasketPage();
        basketpage.comparePrices(sendProductPrice());
    }

    public String sendProductId(){
        return txt_pageProductId;
    }

    public String sendProductPrice(){
        return txt_price;
    }

}
