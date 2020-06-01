package test.java.utils;

import org.testng.annotations.DataProvider;

public class DataProvider1 {

    @DataProvider
    Object[][] getDP1() {
        return new Object[][]{
                {"Dell"},
                {"Google"},
        };
    }

    @DataProvider
    Object[][] getDP2() {
        return new Object[][]{
                {"Acer"},
                {"Apple"},
                {"Asus"},
        };
    }
}
