package test.java.po;

import io.qameta.allure.Step;
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
    private String eProducer;
    public Logger logger = LogManager.getLogger(NotebooksPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By labels = By.xpath("//label[contains(text(),'" + label + "')]"); //div[contains(text(),'producer')]
    private final By goodsOfOneLabel = By.xpath("//a[@class='goods-tile__heading']//child::span[contains(text(),'" + label + "')]");
    private final String bannerSelect = "[class='exponea-banner exponea-popup-banner exponea-animate']";
    private final By banner = By.cssSelector(bannerSelect);
    private final By bannerClose = By.cssSelector("[class='exponea-close-cross']");


    public NotebooksPage(WebDriver driver) {
        logger.trace("Notebooks page initialized");
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
    }

    @Step
    public NotebooksPage clickLabel() {
        logger.info("Click label " + label);
        wait.until(ExpectedConditions.elementToBeClickable(labels));
        driver.findElement(labels).click();
//        logger.error("Couldn't interact with element" + labels);
        return this;
    }

    @Step
    public List<WebElement> getGoodsOfOneLabel() {
        logger.info("Get all goods by label " + label);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(goodsOfOneLabel));
        return driver.findElements(goodsOfOneLabel);
    }

    @Step
    public NotebooksPage closeBanner() {
        logger.info("Banner appeared");
        wait.until(ExpectedConditions.visibilityOfElementLocated(banner));
        wait.until(ExpectedConditions.presenceOfElementLocated(bannerClose)).click();
        return this;
    }

    @Step("Set a filter for a producer {producer}")
    public NotebooksPage setFilter(String producer){
        logger.info("set producer: "+producer+" filter");
        this.producer = producer;
        WebElement producerCheckbox = driver.findElement(By.xpath("//*[@for='"+this.producer+"']"));
        producerCheckbox.click();
        return this;
    }

    //выбрать случайного производителя
    public WebElement randomProducer(){
        List<WebElement> listOfProducers = driver.findElements(By.xpath("//div[@data-filter-name='producer']//ul//li//label"));
        int max = listOfProducers.size();
        int min = 2;
        int rand = (int) (Math.random() * (max + 1 - min) + min);
//        String producer = listOfProducers.get(rand).getText();
//        System.out.println(producer);
        WebElement randomProducer = listOfProducers.get(rand);
        return randomProducer;

    }

    @Step("Choose a random producer")
    public String getRandomProducer() {
//        String getRandomProducer = randomProducer().getText().toLowerCase().split(" ",2)[0];
        String str = randomProducer().getText().toLowerCase();
        String randomProducer = str.substring(0,str.indexOf(" "));
//        System.out.println(getRandomProducer);
        return randomProducer;
    }

    @Step("Set a filter for a random producer")
    public NotebooksPage setRandomFilter(){
        randomProducer().click();
        return this;

    }

    @Step("Detect all goods on the page produced by {eProducer}")
    public String detectAllProducerGoods(String eProducer){
        this.eProducer =eProducer;
        List<WebElement> listOfElements = driver.findElements(By.xpath("//a[@class='goods-tile__heading']//child::span[contains(text(),'" + producer + "')]"));
        for (WebElement element : listOfElements) {
            if (!element.getText().toLowerCase().contains(eProducer.toLowerCase())) {
                //if (!element.getText().contains("Meizu")) {
                this.eProducer = element.getText();
                break;
            }
        }
        return this.eProducer;
    }
}




