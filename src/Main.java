import algorithms.mergesort.MergeSort;
import algorithms.quicksort.QuickSort;
import algorithms.select.DeterministicSelect;
import algorithms.closest.ClosestPair;
import algorithms.closest.ClosestPair.Point;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final Random RAND = new Random();

    public static void main(String[] args) {
        int n = 20;
        int[] arr1 = randomArray(n);
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);

        System.out.println("=== Sorting Algorithms ===");
        System.out.println("Original:  " + Arrays.toString(arr1));

        // MergeSort
        MergeSort.sort(arr1);
        System.out.println("MergeSort: " + Arrays.toString(arr1));

        // QuickSort
        QuickSort.sort(arr2);
        System.out.println("QuickSort: " + Arrays.toString(arr2));

        // Deterministic Select (исправленный)
        int[] arr3 = randomArray(n);
        int k = 5; // 0-based индекс
        int kth = DeterministicSelect.select(arr3.clone(), k);
        int kthCheck = Arrays.stream(arr3.clone()).sorted().toArray()[k];
        System.out.println("\n=== Deterministic Select ===");
        System.out.println("Array: " + Arrays.toString(arr3));
        System.out.println(k + "-th smallest element = " + kth + " (check: " + kthCheck + ")");

        // Closest Pair of Points
        System.out.println("\n=== Closest Pair of Points ===");
        Point[] pts = randomPoints(10);
        for (Point p : pts) {
            System.out.printf("(%.2f, %.2f) ", p.x, p.y);
        }
        System.out.println();
        ClosestPair.Result res = ClosestPair.find(pts);
        System.out.println(res);
    }

    private static int[] randomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = RAND.nextInt(100);
        return arr;
    }

    private static Point[] randomPoints(int n) {
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(RAND.nextDouble() * 100, RAND.nextDouble() * 100);
        }
        return pts;
    }
}
