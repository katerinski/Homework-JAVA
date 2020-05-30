package test.java;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.po.HomePage;

public class TestSecond extends TestBaseSetup{
    HomePage homePage;

    @BeforeMethod
    public void initialize() {
        homePage = new HomePage(driver);
    }

    @Test
    public void testA() {
        homePage.open();
    }
}
