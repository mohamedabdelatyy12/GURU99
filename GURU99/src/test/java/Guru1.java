import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Guru1 {
    public static WebDriver driver = new ChromeDriver();

    public static void main(String[] args) {

    }

    @BeforeMethod
    public void setUp() {
        driver.get("https://clicks.aweber.com/y/ct/?l=bPh1bn&m=mcNYuYFeLUQLjy9&b=h5T4l2fu1DOGeQSqXkdkzQ");
        driver.manage().window().maximize();

    }

    @Test
    public static void maintitle_TestCase1() {
        String sitetitle = driver.findElement(By.cssSelector("div.page-title h2")).getText();
        System.out.println(sitetitle);
        Assert.assertEquals(sitetitle, "THIS IS DEMO SITE FOR   ");
    }

    @Test
    public static void mobiletitle_TestCase1() {
        driver.findElement(By.cssSelector("li:nth-of-type(1) .level0")).click();
        String mobiletitle = driver.findElement(By.cssSelector("div h1")).getText();
        Assert.assertEquals(mobiletitle, "MOBILE");
    }

    @Test
    public static void dropdown_TestCase1() throws IOException {
        driver.findElement(By.cssSelector("li:nth-of-type(1) .level0")).click();
        new Select(driver.findElement(By.cssSelector(".sort-by select[title=\"Sort By\"]")))
                .selectByVisibleText("Name");
        File Sc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targett = "D:\\Eng\\Automation Projects\\Selenium_Projects\\src\\main\\resources";
        File target = new File(targett + "Dropdown_By_Name" + ".png");
        FileUtils.copyFile(Sc, target);
    }

    @Test
    public static void xperia_cost_TestCase2() {
        driver.findElement(By.linkText("MOBILE")).click();
        String cost1 = driver.findElement(By.id("product-price-1")).getText();
        System.out.println(cost1);
        driver.findElement(By.id("product-collection-image-1")).click();
        String cost2 = driver.findElement(By.id("product-price-1")).getText();
        System.out.println(cost2);
        Assert.assertEquals(cost1, cost2);
    }

    @Test
    public static void errorVerification_TestCase3() throws InterruptedException {
        driver.findElement(By.linkText("MOBILE")).click();
        driver.findElement(By.xpath("//button[contains(@onclick, 'product/1') and @title='Add to Cart']")).click();
        driver.findElement(By.cssSelector(".product-cart-actions [type='text']")).click();
        driver.findElement(By.cssSelector(".product-cart-actions [type='text']")).clear();
        driver.findElement(By.cssSelector(".product-cart-actions [type='text']")).sendKeys("1000");
        driver.findElement(By.cssSelector(".product-cart-actions [type='submit']")).click();
        String txt = driver.findElement(By.xpath("//p[@class='item-msg error']")).getText();
        System.out.println(txt);
        Assert.assertEquals(txt, "the requested quantity for\"Sony Xperial\"is not available.");
        driver.findElement(By.id("empty_cart_button")).click();
        String Cartempty = driver.findElement(By.className("page-title")).getText();
        System.out.println(Cartempty);
        Assert.assertEquals(Cartempty, "SHOPPING CART IS EMPTY");
    }

    @Test
    public static void pop_ups_TestCase4() throws IOException {
        driver.findElement(By.linkText("MOBILE")).click();
        driver.findElement(By.xpath("//a[contains(@href, 'product/1') and @class='link-compare']")).click();
        driver.findElement(By.xpath("//a[contains(@href, 'product/2') and @class='link-compare']")).click();
        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        String original_window = driver.getWindowHandle(); // The current Url ID
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(original_window)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        File Sc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targett = "D:\\Eng\\Automation Projects\\Selenium_Projects\\src\\main\\resources";
        File target = new File(targett + "Compare" + ".png");
        FileUtils.copyFile(Sc, target);
        driver.close();
    }
    @Test
    public static void Create_Account_TestCase5() {
        driver.findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.cssSelector(".buttons-set [title='Create an Account']")).click();
        driver.findElement(By.id("firstname")).sendKeys("mohamed");
        driver.findElement(By.id("lastname")).sendKeys("Kabil");
        driver.findElement(By.id("email_address")).sendKeys("mohamedabdelatyy12@gmail.com");
        driver.findElement(By.id("password")).sendKeys("mohamed12343SOSOS@");
        driver.findElement(By.id("confirmation")).sendKeys("mohamed12343SOSOS@");
        driver.findElement(By.cssSelector(".buttons-set [type='submit']")).click();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


