package test.java;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.po.HomePage;
import test.java.utils.RetryAnalyzer;

import static org.testng.FileAssert.fail;

public class TestFirst extends TestBaseSetup {
    HomePage homePage;


    @BeforeMethod
    public void initialize() {
        homePage = new HomePage(driver);
    }

    @Test
    public void testA() {
        homePage.open();
    }

    @Test
//    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testB() {
        homePage.open();
//        fail();
    }
}

//С помощью AnnotationTransformer реализовать возможность подкладывать
//дата провайдер через testng.xml файл (т.е. Пользователь может запускать тесты подкладывая
//разные дата провайдеры не меняя сам тестовый класс )

