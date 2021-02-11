package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    By txt_username = By.id("L-UserNameField");
    By txt_password = By.id("L-PasswordField");
    By btn_login = By.id("gg-login-enter");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void checkLogin(String username,String password){
        Assert.assertEquals("your username here",username);
        Assert.assertEquals("your password here",password);

    }

    public void login(String username,String password) throws InterruptedException {

        driver.findElement(txt_username).sendKeys(username); //Enter username
        driver.findElement(txt_password).sendKeys(password); //Enter password
        Thread.sleep(2000);
        driver.findElement(btn_login).click(); //Login
        checkLogin(username,password); //Check login

    }

}
