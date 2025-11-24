/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project21_7;

///**
// *
// * @author sdbch
// */
//public class Project21_7 {
//
//    public static void main(String[] args) {
//        System.out.println("Hello World!");
//    }
//}

import java.util.Random;

public class Project21_7 {
    
    // Bubble sort implementation
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // Shaker sort implementation (cocktail sort)
    public static void shakerSort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        boolean swapped = true;
        
        while (swapped) {
            swapped = false;
            
            // Left to right pass
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) break;
            
            right--;
            swapped = false;
            
            // Right to left pass
            for (int i = right; i > left; i--) {
                if (arr[i] < arr[i - 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    swapped = true;
                }
            }
            
            left++;
        }
    }
    
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }

    public static int[] generatePartiallySortedArray(int size) {
        int[] arr = generateRandomArray(size);
        quickSort(arr); 
        
        Random random = new Random();
        int elementsToShuffle = size / 4;
        for (int i = 0; i < elementsToShuffle; i++) {
            int idx1 = random.nextInt(size);
            int idx2 = random.nextInt(size);
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
        return arr;
    }
    
    public static int[] generateReverseSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] generateArrayWithDuplicates(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        int uniqueValues = Math.max(1, size / 10);
        
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(uniqueValues);
        }
        return arr;
    }

    public static int[] generateAlmostSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        quickSort(arr); 
        Random random = new Random();
        int elementsToShuffle = size / 10;
        for (int i = 0; i < elementsToShuffle; i++) {
            int idx1 = random.nextInt(size);
            int idx2 = random.nextInt(size);
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
        return arr;
    }

    public static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }
    
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static long measureSortingTime(SortingAlgorithm algorithm, int[] arr) {
        int[] copy = copyArray(arr);
        long startTime = System.nanoTime();
        algorithm.sort(copy);
        long endTime = System.nanoTime();
        
        if (!isSorted(copy)) {
            System.out.println("Error: Array is not sorted!");
        }
        
        return endTime - startTime;
    }
    
    // Interface for sorting algorithms
    interface SortingAlgorithm {
        void sort(int[] arr);
    }
    
    // Main testing method
    public static void main(String[] args) {
        // Array sizes for testing
        int[] sizes = {500, 5000, 10000}; // Small, medium, large
        
        // Sorting algorithms
        SortingAlgorithm[] algorithms = {
            new SortingAlgorithm() { public void sort(int[] arr) { bubbleSort(arr); } },
            new SortingAlgorithm() { public void sort(int[] arr) { shakerSort(arr); } },
            new SortingAlgorithm() { public void sort(int[] arr) { quickSort(arr); } }
        };
        
        String[] algorithmNames = {"Bubble", "Shaker", "Quick"};
        String[] arrayTypes = {
            "Random", "Partially Sorted", "Reverse Sorted",
            "With Duplicates", "Almost Sorted"
        };
        
        // Number of runs for each test
        int runs = 5;
        
        System.out.println("COMPARATIVE ANALYSIS OF SORTING ALGORITHMS\n");
        
        for (int size : sizes) {
            System.out.println("=== ARRAY SIZE: " + size + " ===");
            
            for (String arrayType : arrayTypes) {
                System.out.println("\n--- " + arrayType + " Array ---");
                
                // Generate test array
                int[] testArray = generateTestArray(arrayType, size);
                
                for (int i = 0; i < algorithms.length; i++) {
                    long totalTime = 0;
                    long bestTime = Long.MAX_VALUE;
                    
                    // Perform 5 runs
                    for (int run = 0; run < runs; run++) {
                        long time = measureSortingTime(algorithms[i], testArray);
                        totalTime += time;
                        if (time < bestTime) {
                            bestTime = time;
                        }
                    }
                    
                    long averageTime = totalTime / runs;
                    
                    System.out.printf("%s Sort: average = %.3f ms, best = %.3f ms%n",
                            algorithmNames[i],
                            averageTime / 1_000_000.0,
                            bestTime / 1_000_000.0);
                }
            }
        }
    }

    private static int[] generateTestArray(String type, int size) {
        switch (type) {
            case "Random":
                return generateRandomArray(size);
            case "Partially Sorted":
                return generatePartiallySortedArray(size);
            case "Reverse Sorted":
                return generateReverseSortedArray(size);
            case "With Duplicates":
                return generateArrayWithDuplicates(size);
            case "Almost Sorted":
                return generateAlmostSortedArray(size);
            default:
                return generateRandomArray(size);
        }
    }
}