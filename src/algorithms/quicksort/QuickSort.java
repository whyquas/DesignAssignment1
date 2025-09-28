package algorithms.quicksort;

import java.util.Random;

public class QuickSort {
    private static final Random RAND = new Random();

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        while (left < right) {
            int pivotIndex = left + RAND.nextInt(right - left + 1);
            int newPivotIndex = partition(arr, left, right, pivotIndex);

            if (newPivotIndex - left < right - newPivotIndex) {
                sort(arr, left, newPivotIndex - 1);
                left = newPivotIndex + 1;
            } else {
                sort(arr, newPivotIndex + 1, right);
                right = newPivotIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
