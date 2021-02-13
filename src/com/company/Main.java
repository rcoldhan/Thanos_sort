package com.company;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {4888, -1, -2147483648, 0, 123, 4, 2147483647, 2147483646};
        thanos_sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    /* основной метод принимает на вход массив, вызывает дополнительный метод с тремя параметрами */
    public static void thanos_sort(int[] arr) {
        bucket_sort(arr, 0, arr.length);
    }
    /* проверяем отсортирован ли массив и возвращаем 0, если не отсортиван, 1 - отсортирован */
    public static int is_sorted(int[] arr, int start, int end) {
        for (int i = start; i < end - 1; i++)
            if (arr[i] > arr[i + 1]) {
                return 0;
            }
        return 1;
    }
    /* метод сортировки, параметры: массив, индекс начала массива/подмассива, индекс его конца */
    public static void bucket_sort(int[] arr, int start, int end) {
        /* считаем среднее ариф.элементов массива */
        double mean;
        double sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
        mean = sum / (end - start);

        int[] copy = new int[end - start]; //создаем новый массив
        int left = 0; //крайняя левая свободная ячейка массива
        int right = (end - start) - 1; //крайняя правая свободная ячейка массива

        /* проходим по массиву copy и заполняем его по логике сортировки таноса,
        сравнивая со средним арифметическим */
        for (int i = 0; i < copy.length; i++) {
            if (arr[start + i] <= mean) {
                copy[left] = arr[start + i];
                left++;
            } else {
                copy[right] = arr[start + i];
                right--;
            }
        }
        /* возвращаем в подмассив arr значения элементов из copy */
        for (int i = 0; i < copy.length; i++) {
            arr[start + i] = copy[i];
        }
        /* проверяем отсортированы ли подмассивы и рекурсивно вызываем метод для повторной сортировки */
        if (left > 1 && is_sorted(arr, start, start + left) != 1)
            bucket_sort(arr, start, start + left);
        if (left < end - 1 && is_sorted(arr, start + left, end) != 1)
            bucket_sort(arr, start + left, end);
    }
}
