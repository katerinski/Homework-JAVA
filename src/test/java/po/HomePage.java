package test.java.po;

import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.utils.PropertyLoader;

import java.util.List;

public class HomePage {
    public Logger logger = LogManager.getLogger(HomePage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private String searchStr;
    private final String popupStrSelect = "[class='popup-css lang-switcher-popup sprite-side']";
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

    @FindBy(css = "[name='search']")
    private WebElement search;
    @FindBy(css = popupStrSelect)
    private List<WebElement> popup;
    @FindBy(css = popupStrSelect + "[class='popup-close']")
    private WebElement popupClose;
    By goodsOnPage = By.xpath("//a[@class='goods-tile__heading']");
    By goodsInLeftSidebar = By.xpath("//a[@class='m-cat-l-i-title-link novisited']");//("//h4[@class='m-cat-l-i-title rz-m-cat-l-i-title']/child::a[contains(@href,'samsung')]");
    By allGoods = By.xpath("//a[@class='menu-categories__link']");
    By good = By.xpath("//div[@class='menu-wrapper display-block menu-wrapper_state_static']//a[contains(text(),'" + computers + "')]");


    public HomePage(WebDriver driver) {
        logger.trace("Home page initialized");
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }


    @Step("Open homepage")
    public HomePage open() {
        logger.info(PropertyLoader.loadProperty("url"));
        driver.get(PropertyLoader.loadProperty("url"));
        if (popup.size() > 0) {
            popupClose.click();
        }
        logger.trace("Popups closed");
        return this;
    }

    @Step("Open home page by url {url}")
    public HomePage openThisPage(String url) {
        driver.get(url);
        return this;
    }

    @Step("Search by {searchStr}")
    public HomePage search(String searchStr) {
        logger.info("Search on home page by " + searchStr);
        this.searchStr = searchStr;
        wait.until(ExpectedConditions.elementToBeClickable(search));
        if (popup.size() > 0) {
            popupClose.click();
        }
        logger.trace("All popups closed");
        search.sendKeys(this.searchStr);
        search.sendKeys(Keys.ENTER);
        return this;
    }

    @Step
    public List<WebElement> getGoodsOnPage() {
        logger.info("Get goods on page");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(goodsOnPage));
//        logger.error("not all elements located on Goods page are visible");
        return driver.findElements(goodsOnPage);
    }

    @Step
    public List<WebElement> getGoodsInLeftSidebar() {
        logger.info("Get goods in left sidebar");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(goodsInLeftSidebar));
        return driver.findElements(goodsInLeftSidebar);
    }

    @Step
    public List<WebElement> getAllGoods() {
        logger.info("Get all goods");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allGoods));
        return driver.findElements(allGoods);
    }

    @Step
    public HomePage clickGood() {
        logger.info("Page with goods --" + computers + "-- is open");
        WebElement goodBtn = driver.findElement(good);
        wait.until(ExpectedConditions.elementToBeClickable(goodBtn)).click();
        return this;

    }


}
