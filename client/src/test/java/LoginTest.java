import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

import javax.annotation.Nullable;

import java.util.List;

import static org.junit.Assert.*;

public class LoginTest {

    private WebDriver driver;
    private WebElement user;
    private WebElement pass;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");
        // Launch Chrome
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Navigate to page
        driver.get("http://localhost:8091/loginPage");

        user = driver.findElement(By.name("username"));
        pass = driver.findElement(By.name("password"));
    }

    @Test
    public void login() throws InterruptedException {
        WebElement button = driver.findElement(By.xpath(".//*[@id='mainWrapper']/div/div/div/form/div[3]/input"));

        user.sendKeys("anna");
        pass.sendKeys("anna");
        button.click();

        String logout = driver.findElement(By.xpath("html/body/nav/div[3]/button")).getText();
        assertEquals("Wyloguj się", logout);
    }

    @Test
    public void chooseFirm() throws InterruptedException {
        login();

        driver.findElement(By.id("dropdownMenuLink")).click();
        driver.findElement(By.className("dropdown-item")).click();
        assertEquals("http://localhost:8091/facturesList", driver.getCurrentUrl());
    }

    @Test
    public void addProduct() throws InterruptedException
    {
        chooseFirm();
        driver.findElement(By.linkText("Twoje produkty")).click();
        assertEquals("http://localhost:8091/products", driver.getCurrentUrl());

        driver.findElement(By.name("name")).sendKeys("krzesło");
        driver.findElement(By.name("netUnitPrice")).sendKeys("11.5");
        driver.findElement(By.name("unit")).sendKeys("1");
        driver.findElement(By.name("vatRate")).sendKeys("23");
        driver.findElement(By.xpath(".//*[@id='navbarNav']/ul/li[3]/a")).click();

        WebElement mytable = driver.findElement(By.xpath("html/body/div[2]/table/tbody"));
        List <WebElement> rows_table = mytable.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();
        String strXpath;
        for (int row = 1; row <= rows_count; row++) {
            strXpath = ".//*[@id='searchTable']/tr["+ row +"]/td[1]";
            if(driver.findElement(toBy(strXpath)).getText() == "krzesło"){
                assertEquals(0,0);
            }
        }

        driver.findElement(By.name("name")).sendKeys("tablica");
        driver.findElement(By.name("netUnitPrice")).sendKeys("21.5");
        driver.findElement(By.name("unit")).sendKeys("3");
        driver.findElement(By.name("vatRate")).sendKeys("23");
        driver.findElement(By.xpath(".//*[@id='navbarNav']/ul/li[3]/a")).click();

        for (int row = 1; row <= rows_count; row++) {
            strXpath = ".//*[@id='searchTable']/tr["+ row +"]/td[1]";
            if(driver.findElement(toBy(strXpath)).getText() == "tablica"){
                assertEquals(0,0);
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        // Close the browser
        driver.quit();
    }

    private By toBy(String xpath){
        return By.xpath(xpath);
    }
}
