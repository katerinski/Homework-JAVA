package test.java.po;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class NotebooksPage {
    String label = "Acer";
    private String producer;
    private String eState;
    public Logger logger = LogManager.getLogger(NotebooksPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By labels = By.xpath("//label[contains(text(),'" + label + "')]");
    private final By goodsOfOneLabel = By.xpath("//a[@class='goods-tile__heading']//child::span[contains(text(),'" + label + "')]");
    private final String bannerSelect = "[class='exponea-banner exponea-popup-banner exponea-animate']";
    private final By banner = By.cssSelector(bannerSelect);
    private final By bannerClose = By.cssSelector("[class='exponea-close-cross']");


    public NotebooksPage(WebDriver driver) {
        logger.trace("Notebooks page initialized");
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
    }

    public NotebooksPage clickLabel() {
        logger.info("Click label " + label);
        wait.until(ExpectedConditions.elementToBeClickable(labels));
        driver.findElement(labels).click();
//        logger.error("Couldn't interact with element" + labels);
        return this;
    }

    public List<WebElement> getGoodsOfOneLabel() {
        logger.info("Get all goods by label " + label);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(goodsOfOneLabel));
        return driver.findElements(goodsOfOneLabel);
    }

    public NotebooksPage closeBanner() {
        logger.info("Banner appeared");
        wait.until(ExpectedConditions.visibilityOfElementLocated(banner));
        wait.until(ExpectedConditions.presenceOfElementLocated(bannerClose)).click();
        return this;
    }

    public NotebooksPage setFilter(String producer){
        logger.info("set producer: "+producer+" filter");
        this.producer = producer;
        WebElement producerCheckbox = driver.findElement(By.xpath("//*[@for='"+this.producer+"']"));
        producerCheckbox.click();
        return this;
    }

    public String detectAllProducerGoods(String eState){
        this.eState=eState;
        List<WebElement> listOfElementsIphone = driver.findElements(By.xpath("//a[@class='goods-tile__heading']//child::span[contains(text(),'" + producer + "')]"));
        for (WebElement element : listOfElementsIphone) {
            if (!element.getText().toLowerCase().contains(eState.toLowerCase())) {
                //if (!element.getText().contains("Meizu")) {
                this.eState = element.getText();
                break;
            }
        }
        return this.eState;
    }
}




