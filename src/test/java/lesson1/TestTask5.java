package test.java.lesson1;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static main.java.lesson1.Task5.reverseString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestTask5 {

    //with testng.xml
    @Parameters({"greeting"})
    @Test
    public void isFirstCharacterInReversedStringIsCorrect(@Optional("Good Bye!") String greeting) {
        String reverse = reverseString(greeting);
        char actualFirstChar = reverse.charAt(0);
        char expectedFirstChar = greeting.charAt(greeting.length()-1); //"!"
        assertEquals(
                actualFirstChar,
                expectedFirstChar,
                "Expected first char " + expectedFirstChar  + " is not " + actualFirstChar
        );
    }

    @Test
    public void isLengthOfStringCorrect() {
        String str = "Hello world!!!";
        String reverse = reverseString(str);
        int actualLengthOfString = reverse.length();
        int expectedLengthOfString = str.length();
        assertEquals(
                actualLengthOfString,
                expectedLengthOfString,
                "Expected length is " + expectedLengthOfString  + " is not equal " + actualLengthOfString
        );
    }
}

