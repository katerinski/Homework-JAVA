package lesson1;

public class Task1 {
    /* Минимум трех чисел
    Написать функцию, которая вычисляет минимум из трёх чисел.
    */
    public static void main(String[] args) {
        System.out.println(min(5,2,3));
    }

    public static int min(int a, int b, int c) {
        if (a < b && a < c) return a;
        if (b < a && b < c) return b;
        if (c < b && c < a) return c;
        else return a;
    }
}


