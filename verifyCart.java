import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class verifyCart {

    @Test
    public void verifyAddToCartWorkflow(String productName, int quantity) throws InterruptedException {

        Webdriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","/Users/sameerjaggi/IdeaProjects/verifyAddToCartWorkflow/src/main/resources/chromedriver.exe");

        driver.get("http://www.amazon.com/");

        WebDriverWait wait = new WebDriverWait(driver, 10);  // you can reuse this one
        WebElement element = driver.findElement(By.id("nav-logo-sprites"));
        element.click();
        wait.until(ExpectedConditions.visibilityOf(element));

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(productName);
        driver.findElement(By.id("nav-search-submit-button")).click();

        driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[4]/div/div/span/div/div/div/div[2]/div/div/div[1]/h2/a/span")).click();
        int unitPrice = driver.findElement(By.xpath("//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[1]")).getAttribute("value");
        driver.findElement(By.id("add-to-cart-button")).click();


        driver.findElement(By.xpath("//*[@id=\"sw-gtc\"]/span/a")).click();
        driver.findElement(By.xpath("//*[@id=\"a-autoid-5-announce\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"quantity_"+quantity+"\\"]")).click();

        String finalValue = driver.findElement(By.xpath("//*[@id=\"sc-subtotal-amount-buybox\"]/span")).getAttribute("value");

        Assert.assertEquals(unitPrice*quantity,finalValue);


    }

}
