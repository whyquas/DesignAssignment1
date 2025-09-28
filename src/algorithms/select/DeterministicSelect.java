package algorithms.select;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k out of range");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) return arr[left];

            int pivotIndex = medianOfMedians(arr, left, right);
            pivotIndex = partition(arr, left, right, pivotIndex);

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    // Деление массива вокруг pivot
    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    // Median of Medians
    private static int medianOfMedians(int[] arr, int left, int right) {
        if (right - left < 5) {
            Arrays.sort(arr, left, right + 1);
            return (left + right) / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            int medianIdx = (i + subRight) / 2;
            swap(arr, left + numMedians, medianIdx);
            numMedians++;
        }

        return medianOfMedians(arr, left, left + numMedians - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
