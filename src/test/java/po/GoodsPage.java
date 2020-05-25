package test.java.po;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoodsPage {
    public Logger logger = LogManager.getLogger(GoodsPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By notebooks = By.xpath("//div[@class='tile-cats' ]//child::a[contains(text(),'Ноутбуки')]");


    public GoodsPage(WebDriver driver) {
        logger.trace("Goods page initialized");
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
    }

    public GoodsPage clickGoodsBtn() {
        logger.info("Click notebook button");
        ((JavascriptExecutor) driver).executeScript("scroll(0,1000)");//прокрутить страницу вниз, чтобы элемент стал видимым
        logger.debug("element " + notebooks + "is visible");
        wait.until(ExpectedConditions.elementToBeClickable(notebooks));
        driver.findElement(notebooks).click();
        return this;
    }
}
