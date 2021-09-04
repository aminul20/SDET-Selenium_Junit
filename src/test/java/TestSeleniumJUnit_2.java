import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSeleniumJUnit_2 {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup(){
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops=new FirefoxOptions();
//        ops.addArguments("--headless");//run test without opening browser
        ops.addArguments("--headed");
        driver=new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void getTitle(){
        driver.get("https://demoqa.com");
        String title=driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains("ToolsQA"));
    }

    @Test
    public void clickOnButon(){
        driver.get("https://demoqa.com/buttons");
        Actions action = new Actions(driver);
        List<WebElement> list= driver.findElements(By.cssSelector("button"));
        action.doubleClick(list.get(1)).perform();
        String text= driver.findElement(By.id("doubleClickMessage")).getText();
        Assert.assertTrue(text.contains("You have done a double click"));
        action.contextClick(list.get(2)).perform();
        String text2 = driver.findElement(By.id("rightClickMessage")).getText();
        Assert.assertTrue(text2.contains("You have done a right click"));
        list.get(3).click();
        String text3 = driver.findElement(By.id("dynamicClickMessage")).getText();
        Assert.assertTrue(text3.contains("You have done a dynamic click"));
    }

    @Test
    public void writeOnTextBox(){
        driver.get("https://demoqa.com/elements");
        driver.findElement(By.xpath("//span[contains(text(),'Text Box')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='userName']"))).sendKeys("Rahim");
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[id=userName]"))).sendKeys("Rahim");
//        driver.findElement(By.cssSelector("[id=userName]")).sendKeys("Rahim");
//        driver.findElement(By.xpath("//button[@id='submit']")).click();
        driver.findElement(By.xpath("//button[@id='submit']")).sendKeys(Keys.ENTER);
        String text= driver.findElement(By.cssSelector("[id=name]")).getText();
        Assert.assertTrue(text.contains("Rahim"));
    }

    @After
    public void finishTest(){
        driver.close();
    }
}
