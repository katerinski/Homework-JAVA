package test.java;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.po.HomePage;
import test.java.po.NotebooksPage;
import test.java.utils.RetryAnalyzer;

import static org.testng.Assert.assertEquals;
import static org.testng.FileAssert.fail;

public class TestFirst extends TestBaseSetup {
    HomePage homePage;
    NotebooksPage notebooksPage;


    @BeforeMethod
    public void initialize() {
        homePage = new HomePage(driver);
        notebooksPage = new NotebooksPage(driver);
    }

    @Test
    public void testA() {
        homePage.open();
    }

//    @Test
////    @Test(retryAnalyzer = RetryAnalyzer.class)
//    public void testB() {
//        homePage.open();
////        fail();
//    }

    @Test
    public void producerFiltersCheck(String producer) {
        homePage.openThisPage("https://rozetka.com.ua/notebooks/c80004/preset=workteaching/");
        notebooksPage.setFilter(producer);
        String actualState = notebooksPage.detectAllProducerGoods(producer);
        assertEquals(actualState, producer, "Error on page. Some item not from '" + producer + "' producer : " + actualState);
    }
}



