package lesson1;

/**
 * Реализовать функцию, которая принимает строку и возвращает ее же в обратном виде
 * например "Hello world!!!" -> "!!!dlrow olleH"
 */

public class Task5 {
    public static void main(String[] args) {
        String str = "Hello world!!!";
        String reverse = new StringBuffer(str).reverse().toString();
        System.out.println(reverse);
    }
}

/*второй вариант

public class Task5 {
    public static void main(String[] args) {
        String str = "Hello world!!!";
        char[] reverseArray1 = str.toCharArray(); // Преобразуем строку str в массив символов (char)
        for (int i = reverseArray1.length-1; i >= 0; i--)
            System.out.print(reverseArray1[i]);
    }
}*/