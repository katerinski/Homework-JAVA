package main.java.lesson1;

/* Мое имя
Вывести на экран свое имя 5 строк по 10 раз (через пробел).
System.out.println, System.out.print можно использовать только по одному разу
для этого нужно воспользоваться циклом while
*/

public class Task3 {
    String name;

    public static void main(String[] args) {
        String str = "Kate ";
        System.out.println(nameTenTimes(str));
    }

    public static String nameTenTimes(String name) {
        String result = "";
        int i = 0;
        while (i < 5) {
            int j = 1;
            while (j < 10) {
                result += name;//System.out.print(name);
                j++;
            }
            result += name + "\n"; //System.out.println (name);
            i++;
        }
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}


