package main.java.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Web_homework {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rozetka.com.ua/");
        Thread.sleep(1000);
        String phone = driver.findElement(By.cssSelector("[class='link-dashed']")).getText();
        System.out.println(phone.replaceAll("-| |\\)|\\(", "")); //«044XXXXXXX»
        driver.quit();

    }
}

