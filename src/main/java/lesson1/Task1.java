package main.java.lesson1;

public class Task1 {
    int number;
    /* Минимум трех чисел
    Написать функцию, которая вычисляет минимум из трёх чисел.
    */
    public static void main(String[] args) {

        System.out.println(min(5, 2, 3));
    }

    public static int min(int a, int b, int c) {
        int min = 0;
        if (a <= b && a <= c)
            min = a;
        else if (b <= a && b <= c)
            min = b;
        if (c <= b && c <= a)
            min = c;
        return min;
    }

    public void setNumber (int number) {

        this.number=number;
    }

    public int getNumber() {
        return this.number;
    }

}


