package edu.udacity.java.nano;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebSocketChatServerTest {

    private WebDriver webDriver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Temporary\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/index?userName=LolDude");
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

    @Test
    public void testENTER() {
        webDriver.get("http://localhost:8080/");
        webDriver.findElement(By.id("username")).sendKeys("LolDude");
        webDriver.findElement(By.className("submit")).click();
        webDriver.get("http://localhost:8080/index?userName=LolDude");
        String title = webDriver.findElement(By.className("chat-num")).getText();
        assertEquals(title, "1");
    }

    @Test
    public void testCHAT() {
        webDriver.get("http://localhost:8080/index?userName=LolDude");
        WebDriverWait wait = new WebDriverWait(webDriver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));
        webDriver.findElement(By.id("msg")).sendKeys("Testing Bruh!");
        webDriver.findElement(By.className("send-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("message-content")));
        String testMessage = webDriver.findElement(By.className("message-content")).getText();
        assertEquals(testMessage, "LolDude: Testing Bruh!");
    }

    @Test
    public void testQUIT() {
        WebDriverWait wait = new WebDriverWait(webDriver, 61);
        webDriver.get("http://localhost:8080/");
        webDriver.findElement(By.id("username")).sendKeys("SecondLolDude");
        webDriver.findElement(By.className("submit")).click();
        webDriver.get("http://localhost:8080/index?userName=SecondLolDude");
        webDriver.get("http://localhost:8080/index?userName=LolDude");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("exit-button")));
        webDriver.findElement(By.className("exit-button")).click();
        webDriver.get("http://localhost:8080/index?userName=SecondLolDude");
        String title = webDriver.findElement(By.className("chat-num")).getText();
        System.out.println(title);
    }
}
