package test.java.lesson1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static main.java.lesson1.Task1.min;
import static org.testng.Assert.assertEquals;

public class TestTask1 {

    @Test(dataProvider = "data")
    public void testMin(int number1, int number2, int number3) {
        int actualMin = min(number1, number2, number3);
        int expectedMin = 2;
        assertEquals(
                actualMin,
                expectedMin,
                "Expected min is " + expectedMin + " but actual min is " + actualMin
        );
    }

    @DataProvider(name = "data")
    public Object[][] getNumbers() {
        return new Object[][]{
                {5, 2, 3},
                {2, 2, 2},
                {2, 2, 5},
        };
    }

    @Test
    public void isMinResultCorrect() {
        int actualMinResult = min(8, 3, 2);
        int expectedMinResult = 2;
        assertEquals(actualMinResult,
                expectedMinResult,
                "Expected min is " + expectedMinResult + " but actual min is " + actualMinResult
        );
    }

//    @Test(dataProvider = "numbers")
//    public void testMin2(int actualMin) {
//        int expectedMin = 2;
//        assertEquals(
//                actualMin,
//                expectedMin,
//                "Expected min is " + expectedMin + " but actual min is " + actualMin
//        );
//    }
//
//    @DataProvider(name = "numbers")
//    public Object[][] getData() {
//        return new Object[][] {
//                {min(5,2,3)},
//                {min(2,2,2)},
//                {min(2,2,5)},
//        };
//    }

}
