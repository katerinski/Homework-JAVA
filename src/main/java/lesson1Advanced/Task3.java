package main.java.lesson1Advanced;

/* Мое имя
Вывести на экран любой символ пирамидкой в обратную сторону высотой в 10 строк.
         *
        **
       ***
      ****
     *****
    ******
   *******
  ********
 *********
**********
System.out.println, System.out.print можно использовать только по одному разу
для этого нужно воспользоваться циклом
*/

public class Task3 {
        public static void main(String[] args) {
            String s = "*";
            for (int i = 1; i <= 10; i++) {
                for (int j = i; j < 10; j++) {
                    System.out.print(" ");
                }
                for (int j = 1; j <= i; j++) {
                    System.out.print(s);
                }
                System.out.println();
            }
        }
}