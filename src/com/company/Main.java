package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {4888, -1, -2147483648, 0, 123, 4, 2147483647, 2147483646, 9};
        ThanosSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * основной метод принимает на вход массив, вызывает дополнительный метод с тремя параметрами
     */
    public static void ThanosSort(int[] arr) {
        BucketSort(arr, 0, arr.length);
    }

    /**
     * проверяем отсортирован ли массив и возвращаем 0, если не отсортиван, 1 - отсортирован
     */
    public static boolean NotSorted(int[] arr, int start, int end) {
        for (int i = start; i < end - 1; i++)
            if (arr[i] > arr[i + 1]) {
                return true;
            }
        return false;
    }

    /**
     * метод сортировки, параметры: массив, индекс начала массива/подмассива, индекс его конца
     */
    public static void BucketSort(int[] arr, int start, int end) {
        // считаем среднее ариф.элементов массива
        double mean;
        double sum = 0;
        int len = end - start;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
        mean = sum / len;

        int[] copy = new int[len]; //создаем новый массив
        int left = 0; //крайняя левая свободная ячейка массива
        int right = len - 1; //крайняя правая свободная ячейка массива

        // проходим по массиву copy и заполняем его по логике сортировки таноса, сравнивая со средним арифметическим
        for (int i = 0; i < copy.length; i++) {
            if (arr[start + i] <= mean) {
                copy[left] = arr[start + i];
                left++;
            } else {
                copy[right] = arr[start + i];
                right--;
            }
        }
        // возвращаем в подмассив arr значения элементов из copy
        if (copy.length >= 0) {
            System.arraycopy(copy, 0, arr, start, copy.length);
        }
        // роверяем отсортированы ли подмассивы и рекурсивно вызываем метод для повторной сортировк
        if (left > 1 && NotSorted(arr, start, start + left)) {
            BucketSort(arr, start, start + left);
        }
        if (left < end - 1 && NotSorted(arr, start + left, end)) {
            BucketSort(arr, start + left, end);
        }
    }
}