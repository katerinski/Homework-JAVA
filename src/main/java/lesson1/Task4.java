package lesson1;

/* Мое имя
Вывести на экран свое имя 5 строк по 10 раз (через пробел).
System.out.println, System.out.print можно использовать только по одному разу
для этого нужно воспользоваться циклом do () while
*/

public class Task4 {

    public static void main(String[] args) {
        String str = "Kate";
        int j=0;
        do {
            int i=0;
            do {
                System.out.print(str+" ");
                i++;
            }while (i<10);
            System.out.println(str);
            j++;
        }while (j<5);
    }
}