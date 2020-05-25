package test.java.lesson1;

import org.testng.annotations.Test;

import static main.java.lesson1.Task3.nameTenTimes;
import static org.testng.Assert.assertEquals;

public class TestTask3 {
    String name = "Kate ";

    @Test
    public void isResultCorrect() {
        String actualResult = nameTenTimes(name); //метод while
        String expectedResult = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 10; j++) {
                expectedResult += name;
            }
            expectedResult += name + "\n";
        }
        assertEquals(
                actualResult,
                expectedResult,
                "Actual result is " + actualResult + " but expected result is " + expectedResult
        );
    }

    @Test
    public void isQuantityOfStringsCorrect() {
        String newName = nameTenTimes(name);
        String[] arr = newName.split("\n");
        int actualQuantityOfStrings = arr.length;
        
//        for (int i = 0; i < arr.length; i++) {
//            ++actualQuantityOfStrings;
//        }
        int expectedQuantityOfStrings = 5;
        assertEquals(
                actualQuantityOfStrings,
                expectedQuantityOfStrings,
                "Expected quantity of strings is " + expectedQuantityOfStrings + " but actual quantity is " + actualQuantityOfStrings
        );
    }

    @Test
    public void isQuantityOfWordsCorrect() {
        String newName = nameTenTimes(name);
        int index = newName.indexOf("\n");
        String newName1 = newName.substring(0, index);
        String[] arr = newName1.split(" ");
        int actualQuantityOfWords = arr.length;
//        for (int i = 0; i < arr.length; i++) {
//            ++actualQuantityOfWords;
//        }
        int expectedQuantityOfWords = 10;
        assertEquals(
                actualQuantityOfWords,
                expectedQuantityOfWords,
                "Expected quantity of strings is " + expectedQuantityOfWords + " but actual quantity is " + actualQuantityOfWords
        );
    }

    @Test
    public void isLengthCorrect() {
        int actualLength = nameTenTimes(name).length();
        int expectedLength = 255;
        assertEquals(
                actualLength,
                expectedLength
        );
    }

}
