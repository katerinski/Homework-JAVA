package test.java;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.po.HomePage;
import test.java.po.NotebooksPage;
import test.java.utils.RetryAnalyzer;

import static org.testng.Assert.assertEquals;
import static org.testng.FileAssert.fail;

public class TestFilter extends TestBaseSetup {
    HomePage homePage;
    NotebooksPage notebooksPage;


    @BeforeMethod
    public void initialize() {
        homePage = new HomePage(driver);
        notebooksPage = new NotebooksPage(driver);
    }


    @Epic("Filter")
    @Feature("Filter with DP")
    @Issue("AAA-21")
    @Test
    public void producerFiltersCheck(String producer) {
        homePage.openThisPage("https://rozetka.com.ua/notebooks/c80004/preset=workteaching/");
        notebooksPage.setFilter(producer);
        String actualState = notebooksPage.detectAllProducerGoods(producer);
        assertEquals(actualState, producer, "Error on page. Some item not from '" + producer + "' producer : " + actualState);
    }

    //выполнить для случайного производителя
    @Epic("Filter")
    @Feature("Random filter")
    @Issue("AAA-22")
    @Test
    public void producerRandomFilterCheck() {
        homePage.openThisPage("https://rozetka.com.ua/notebooks/c80004/preset=workteaching/");
        WebElement randProducer = notebooksPage.randomProducer();
        String randomProducer = randProducer.getText().toLowerCase().split(" ", 2)[0];
        randProducer.click();
        String actualState = notebooksPage.detectAllProducerGoods(randomProducer);
        assertEquals(actualState, randomProducer, "Error on page. Some item not from '" + randomProducer + "' producer : " + actualState);
    }


}



