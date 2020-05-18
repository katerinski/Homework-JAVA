package test.java.lesson8;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestWaits {
    WebDriver driver;
    WebDriverWait wait;
    String popupStrSelector = "[class='popup-css lang-switcher-popup sprite-side']";
    String expectedColorOfAllBorders = "rgb(248, 65, 71)";
    String expectedColorOfBordersIfInputFilled = "rgb(210, 210, 210)";
    String nameOfUser = "Катерина";
    By userLink = By.cssSelector("[class='header-topline__user-link link-dashed']");
    By registerLink = By.cssSelector("[class='auth-modal__register-link']");
    By name = By.cssSelector("[formcontrolname='name']"); //By.xpath("//input[@formcontrolname='name']");//
    By email = By.cssSelector("[formcontrolname='username']");
    By password = By.cssSelector("[formcontrolname='password']");
    By registerButton = By.cssSelector("[class='button button_size_large button_color_green auth-modal__submit']");
    By popup = By.cssSelector(popupStrSelector);
    By popupClose = By.cssSelector(popupStrSelector + "[class='popup-close']");

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions(); // https://peter.sh/experiments/chromium-command-line-switches/
        options.addArguments("--window-size=1300,1080"); // настройка открывающегося окна
        options.addArguments("--disable-notifications"); // отключить уведомления
//        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.get("https://rozetka.com.ua/");
        WebElement enter = driver.findElement(userLink);
        wait.until(ExpectedConditions.elementToBeClickable(enter));
        if (driver.findElements(popup).size() > 0) {
            driver.findElement(popupClose).click();
        }
        enter.click();
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();

    }

    @Test
    public void allEmptyInputFieldsHighlightedInRed() {
        WebElement userName = driver.findElement(name);
        wait.until(ExpectedConditions.elementToBeClickable(userName)).click();
        WebElement userEmail = driver.findElement(email);
        wait.until(ExpectedConditions.elementToBeClickable(userEmail)).click();
        WebElement userPassword = driver.findElement(password);
        wait.until(ExpectedConditions.elementToBeClickable(userPassword)).click();
        driver.findElement(registerButton).click();
        String actualColorOfBorderUserName = userName.getCssValue("border-color");
        assertEquals(
                actualColorOfBorderUserName,
                expectedColorOfAllBorders,
                "UserName assert failed"
        );
        String actualColorOfBorderEmail = userEmail.getCssValue("border-color");
        assertEquals(
                actualColorOfBorderEmail,
                expectedColorOfAllBorders,
                "UserEmail assert failed"
        );
        String actualColorOfBorderPassword = userPassword.getCssValue("border-color");
        assertEquals(
                actualColorOfBorderPassword,
                expectedColorOfAllBorders,
                "Password assert failed"
        );
    }

    @Test
    public void filledInputIsNotHighlightedInRed() {
        WebElement userName = driver.findElement(name);
        wait.until(ExpectedConditions.elementToBeClickable(userName)).click();
        userName.sendKeys(nameOfUser);
        WebElement userEmail = driver.findElement(email);
        wait.until(ExpectedConditions.elementToBeClickable(userEmail)).click();
        WebElement userPassword = driver.findElement(password);
        wait.until(ExpectedConditions.elementToBeClickable(userPassword)).click();
        driver.findElement(registerButton).click();
        String actualColorOfBorderUserName = userName.getCssValue("border");
        assertTrue(actualColorOfBorderUserName.contains(expectedColorOfBordersIfInputFilled),
                String.format("Expected '%s' to contains '%s'", actualColorOfBorderUserName, expectedColorOfBordersIfInputFilled)
        );
        String actualColorOfBorderEmail = userEmail.getCssValue("border-color");
        assertEquals(
                actualColorOfBorderEmail,
                expectedColorOfAllBorders,
                "UserEmail assert failed"
        );
        String actualColorOfBorderPassword = userPassword.getCssValue("border-color");
        assertEquals(
                actualColorOfBorderPassword,
                expectedColorOfAllBorders,
                "Password assert failed"
        );
    }

    @AfterMethod
    public void afterMethod() {

        driver.quit();
    }

}
