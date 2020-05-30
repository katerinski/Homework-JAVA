package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.po.GoodsPage;
import test.java.po.HomePage;
import test.java.po.NotebooksPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class TestPO extends TestBaseSetup {
    HomePage homePage;
    GoodsPage goodsPage;
    NotebooksPage notebooksPage;


    @BeforeMethod
    public void initialize() {
        homePage = new HomePage(driver);
        goodsPage = new GoodsPage(driver);
        notebooksPage = new NotebooksPage(driver);
    }

    //Проверить, что в списке товаров отображаются только iPhone
    @Test
    public void onlyOneTypeOfProductIsDispleyd() {
        String goods = "iPhone";
        homePage.open(); //Открыть главную страницу
        homePage.search(goods); //Ввести в поиск «iPhone»
        List<WebElement> webLinks = homePage.getGoodsOnPage();
        for (WebElement element : webLinks) {
            String actualNameOfGoods = element.getText();
            assertTrue(
                    actualNameOfGoods.contains(goods),
                    "Page contains other goods " + actualNameOfGoods
            );
        }

    }

    //Проверить, что в левой панели списка типов товаров отражаются все типы товаров Samsung
    @Test
    public void onlyOneTypeOfProductIsDisplayedInLeftSidebar() {
        String goods1 = "Samsung";
        homePage.open(); //Открыть главную страницу
        homePage.search(goods1); //Ввести в поиск «samsung»
        List<WebElement> webLinks = homePage.getGoodsInLeftSidebar();
        for (WebElement element : webLinks) {
            String actualNameOfGoods = element.getAttribute("href");
            assertTrue(actualNameOfGoods.contains(goods1.toLowerCase()));
        }
    }

    //проверить, что все разделы товаров из перечня представлены на главной странице
    @Test
    public void allGoodsOnHomePage() {
        int i = 0;
        String[] expectedAllGoods = {computers, phones, appliances, forHome
                , instruments, sanitary, garden, sport, clothes, beauty
                , children, books, food, tourism, service, foodSets};
        homePage.open();
        List<WebElement> webLinks = homePage.getAllGoods();
        for (i = 0; i < webLinks.size(); i++) {
            String actualAllGoods = webLinks.get(i).getText();
            assertEquals(
                    actualAllGoods,
                    expectedAllGoods[i],
                    "expected " + expectedAllGoods[i] + " but actual " + actualAllGoods);
        }
    }

    //проверить, что на странице все товары одного производителя
    @Test
    public void testNotebooks() {
        homePage.open();
        homePage.clickGood();
        goodsPage.clickGoodsBtn();
        notebooksPage.clickLabel();
        List<WebElement> webLinks = notebooksPage.getGoodsOfOneLabel();
        for (WebElement element : webLinks) {
            String actualLabelOfGoods = element.getText();
            assertTrue(actualLabelOfGoods.contains("Acer"),
                    "expected label is \"Acer\" but found " + actualLabelOfGoods
            );
        }
    }

    //проверить, что на странице все товары одного производителя с DataProvider
    @Test(dataProvider = "goods")
    public void testNotebooksAndComputers(String label) throws InterruptedException {
        By labels = By.xpath("//label[contains(text(),'" + label + "')]");
        By goodsOfOneLabel = By.xpath("//a[@class='goods-tile__heading']//child::span[contains(text(),'" + label + "')]");
        homePage.open();
        homePage.clickGood();
        goodsPage.clickGoodsBtn();
        wait.until(ExpectedConditions.elementToBeClickable(labels));
        driver.findElement(labels).click();
        Thread.sleep(5000);
        notebooksPage.closeBanner();
        List<WebElement> webLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(goodsOfOneLabel));
        for (WebElement element : webLinks) {
            String actualLabelOfGoods = element.getText();
            assertTrue(actualLabelOfGoods.contains(label),
                    "expected label is " + label + " but found " + actualLabelOfGoods
            );
        }
    }

    @DataProvider(name = "goods")
    public Object[][] getGods() {
        return new Object[][]{
                {"Acer"},
                {"Apple"},
                {"Asus"},
        };

    }

    @Test(dataProvider = "getProducerName")
    public void notebookFiltersCheck(String producer) {
        homePage.openThisPage("https://rozetka.com.ua/notebooks/c80004/preset=workteaching/");
        notebooksPage.setFilter(producer);
        String actualState = notebooksPage.detectAllProducerGoods(producer);
        assertEquals(actualState, producer, "Error on page. Some item not from '"+producer+"' producer : " + actualState);
    }


    @DataProvider
    public Object[] getProducerName() {
        return new Object[][]{
                {"Asus"},
                {"Apple"},
                {"Acer"}
        };
    }
}




