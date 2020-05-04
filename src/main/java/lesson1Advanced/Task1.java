package lesson1Advanced;

public class Task1 {
    /* Минимум n чисел
    Написать функцию, которая вычисляет минимум из массива чисел.
    */
    public static void main(String[] args) {
        int[] array={2,4,6};
        int[] array2={2,4,6,1,90,2,0};
        System.out.println(min(array));
        System.out.println(min(array2));
    }

    public static int min(int[] arr){
        int min = arr[0];
        for (int value : arr) {

            if (value < min) {
                min = value;
            }
        }

        return min;
    }
}
