package com.test1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppTest {
    private WebDriver driver;

    @BeforeTest
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.ranorex.com/web-testing-examples/vip/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void myFirstTest() throws InterruptedException {


        WebElement firstName = driver.findElement(By.id("FirstName"));
        WebElement lastName = driver.findElement(By.id("LastName"));
        List<WebElement> checkBoxes = driver.findElements(By.id("Gender"));
        Select category = new Select(driver.findElement(By.id("Category")));
        WebElement load = driver.findElement(By.id("Load"));
        String vipCount = driver.findElement(By.id("count")).getText();


        firstName.click();
        firstName.sendKeys("Stas");
        lastName.click();
        lastName.sendKeys("Belyj");
        checkBoxes.get(1).click();
        category.selectByIndex(3);
        Assert.assertEquals(vipCount,"VIP count: 0", "not equal 1st time");
        load.click();
        String vipCountAfterClick = driver.findElement(By.id("count")).getText();
        Assert.assertEquals(vipCountAfterClick,"VIP count: 6", "not equal 2nd time");

    }
    @AfterTest
    public void finish(){
        driver.close();
    }
}
