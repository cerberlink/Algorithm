
/*
Author: Carlos Guerrero

Professor: Aaron Biel

Project 5 - Array Sort

*/
import java.util.*;
import java.io.*;

public class Main {
    private static final String file = "input5.txt"; // filename

    // This will create a scanner instance from the file
    private static Scanner createFileScanner() throws Exception {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(file)); // try to create the file scanner
        } catch (FileNotFoundException fnfe) {
            throw new Exception("Cannot open input file " + file);// if file is not found
        }

        return scanner;
    }

    /**
     * Method: getNumCount This method will read from the file and return total
     * number of numbers in that file
     * 
     * @return
     * @throws Exception
     */
    private static int getNumCount() throws Exception {
        Scanner sc = createFileScanner();
        int num = 0;
        if (sc != null) {
            while (sc.hasNextInt()) {
                sc.nextInt();
                num++;
            }
        }
        sc.close();
        return num;
    }

    /**
     * Method: read This method will create an Integer[] based on total number of
     * integers read from getNumCount method. Then it will populate the array by
     * reading the file again and return the array
     * 
     * @return
     * @throws Exception
     */
    private static Integer[] read() throws Exception {
        int num = getNumCount();
        Integer arr[] = new Integer[num];
        Scanner sc = createFileScanner();
        int index = 0;
        if (sc != null) {
            while (sc.hasNextInt()) {
                arr[index++] = sc.nextInt();// populate the array
            }
        }
        return arr;

    }

    /**
     * Method: sortArray It will take an Integer array and sort its elements based
     * on absolute value
     * 
     * @param array
     */
    private static void sortArray(Integer array[]) {
        // Create a Comparator<Integer> which will compare Integer by their absolute
        // value
        Comparator<Integer> absoluteValueComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (Math.abs(x) == Math.abs(y)) {
                    if (x < y)
                        return -1;
                    if (x > y)
                        return 1;
                    return 0;
                } else if (Math.abs(x) < Math.abs(y)) {
                    return -1;
                }
                return 1;
            }
        };
        if (array != null) {
            // Now sort the array using the absoluteValueComparator by passing passing
            // it as the second argument of Arrays.sort() method
            Arrays.sort(array, absoluteValueComparator);
        }
    }

    /**
     * Method:printArray This method will print the array
     * 
     * @param arr
     */
    private static void printArray(Integer[] arr) {
        if (arr != null) {
            System.out.println(Arrays.toString(arr));
        } else {
            System.out.println("array is NULL!!!");
        }
    }

    // main method
    public static void main(String[] args) {
        Integer[] arr = null;
        try {
            arr = read();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        sortArray(arr);// sort the array by absolute value
        printArray(arr);// print the sorted array
    }

    // // this method can getting the original elements
    // private static int sort() {
    // try {
    // Scanner scanner = new Scanner(new File(file));
    // int num = 0;
    // while (scanner.hasNextInt()) {
    // scanner.nextInt();
    // num++;
    // }
    // scanner.close();
    // return num;
    // } catch (FileNotFoundException fnfe) {
    // return -1;
    // }
    // }

    // // this method is reading the elements from input.txt
    // private static void read(int array[]) {
    // try {
    // Scanner scanner = new Scanner(new File(file));
    // int index = 0;
    // while (scanner.hasNextInt()) {
    // int integer = scanner.nextInt();
    // array[index++] = integer;
    // }
    // scanner.close();
    // } catch (FileNotFoundException fnfe) {
    // }
    // }

    // // Algorithm - sort using absolute. O(n^2) time
    // private static void sortArray(int array[]) {
    // // Arrays.sort(array);
    // for (int i = 0; i < array.length; i++) {
    // for (int j = i + 1; j < array.length; j++) {
    // // using the if/else condition due to swap the elements.
    // if (Math.abs(array[i]) == Math.abs(array[j])) {
    // if (array[i] > array[j]) {
    // int temp = array[i];
    // array[i] = array[j];
    // array[j] = temp;
    // }
    // } else if (Math.abs(array[i]) > Math.abs(array[j])) {
    // int temp = array[i];
    // array[i] = array[j];
    // array[j] = temp;
    // }
    // }
    // }
    // }

    // // this method is the main.
    // public static void main(String[] args) {
    // int arr = sort();
    // if (arr < 0) {
    // // show message
    // System.out.println("Cannot open input file " + file);
    // // terminate
    // return;
    // }
    // int[] array = new int[arr];
    // read(array);
    // // Function call for sorting
    // sortArray(array);
    // System.out.println(Arrays.toString(array));
    // }
}