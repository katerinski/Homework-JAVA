package test.java;

import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import test.java.utils.Screenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

public class TestBaseSetup {
    WebDriver driver;
    WebDriverWait wait;
    Screenshot screenshot;
    String computers = "Ноутбуки и компьютеры";
    String phones = "Смартфоны, ТВ и электроника";
    String appliances = "Бытовая техника";
    String forHome = "Товары для дома";
    String instruments = "Инструменты и автотовары";
    String sanitary = "Сантехника и ремонт";
    String garden = "Дача, сад и огород";
    String sport = "Спорт и увлечения";
    String clothes = "Одежда, обувь и украшения";
    String beauty = "Красота и здоровье";
    String children = "Детские товары";
    String books = "Канцтовары и книги";
    String food = "Алкогольные напитки и продукты";
    String tourism = "Товары для бизнеса";
    String service = "Услуги и сервисы";
    String foodSets = "Продовольственные наборы";

    /* реализация @BeforeMethod с использованием RemoteWebDriver
    @BeforeMethod
    public void beforeMethod(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions optionsCh = new ChromeOptions();
        optionsCh.addArguments("--disable-notifications");
        optionsCh.addArguments("--window-size=1300,1080");
        FirefoxOptions optionsFF = new FirefoxOptions();
        context.setAttribute("driver", driver);
        try {
            driver = new RemoteWebDriver(
                    new URL("http://ec2-3-21-234-230.us-east-2.compute.amazonaws.com:4444/wd/hub"), optionsFF); //запуск драйвера в изолированном контейнере
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), optionsCh);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        screenshot = new Screenshot(driver);
        wait = new WebDriverWait(driver, 10);
    }

    */

    // multybrowsers-testng.xml
    @Parameters("driver")
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browserName, ITestContext context) {
        driver = getDriverByName(browserName);
        context.setAttribute("driver", driver);
        screenshot = new Screenshot(driver);
        wait = new WebDriverWait(driver, 10);
    }

    private WebDriver getDriverByName(String browserName) {
        WebDriver driver = null;
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--window-size=1300,1080");
            driver = new ChromeDriver();
        }
        if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
//            FirefoxProfile profile = new FirefoxProfile();
            driver = new FirefoxDriver();
            FirefoxOptions optionsFF = new FirefoxOptions();
        }
        return driver;
    }

    //метод для подтверждения или опровержения того, что элемент есть на странице
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
//        screenshot.getScreenshot(result);
        attachScreen();
        driver.quit();
    }

    @Attachment(value = "screenshot", type = "image/png")
    private byte[] attachScreen() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
