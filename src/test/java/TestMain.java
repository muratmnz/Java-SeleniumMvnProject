import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasketPage;
import pages.HomePage;
import pages.LoginPage;
import org.apache.log4j.Logger;
import pages.ProductPage;

public class TestMain{

    WebDriver driver = null;
    final static Logger logger=Logger.getLogger(TestMain.class);

    LoginPage loginpage;
    HomePage homepage;
    BasketPage basketpage;
    ProductPage productpage;

    @Before
    public void setUpTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Test started.");
    }

    @Before
    public void setUpPageDriver(){
        loginpage = new LoginPage(driver);
        homepage = new HomePage(driver);
        basketpage = new BasketPage(driver);
        productpage = new ProductPage(driver);
    }

    @Test
    public void TestCases() throws InterruptedException {

        setUpPageDriver();

        System.setProperty(Utils.CHROME_DRIVER_PROPERTY,Utils.CHROME_DRIVER_LOCATION);
        driver.navigate().to(Utils.base_URL);
        homepage.checkHomePage();
        logger.info("Home page is opened.");

        driver.navigate().to(Utils.loginPage_URL);
        loginpage.login("your username here","your password here"); //Send information to login
        logger.info("Login is successful.");

        homepage.searchItem("bilgisayar");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        homepage.searchPassTwoPage();
        logger.info("Search two page is passed successfully.");

        homepage.checkSecondPage();
        logger.info("Search two page is opened successfully.");

        homepage.selectRandomProduct(); //Select random product

        productpage.getProductId(); //Some need datas
        productpage.getProductPrice();

        productpage.checkProductPrices();
        logger.info("Comparison is successful.");

        basketpage.addProductNumber();
        logger.info("Adding process is successful.");

        productpage.deleteItemById();
        logger.info("Delete operation is successful.");

    }

    @After
    public void downTest(){
        driver.close();
        driver.quit();
        logger.info("Test completed.");
    }

}
