
/*

Developer: Carlos Guerrero
Professor: Aaron Biel
Date: March 07, 2020
Project 3 - Counting Inversion

*/
import java.util.*;
import java.io.*;

public class CountingInversion {
    int mergeInversion(ArrayList<Integer> arr, int l, int m, int r) {
        // Size of two arrays
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /* Copy data to temp arrays */
        for (int i = 0; i < n1; ++i)
            L[i] = arr.get(l + i);
        for (int j = 0; j < n2; ++j)
            R[j] = arr.get(m + 1 + j);
        // Counting Inversion
        int count = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (L[i] > R[j]) // Condition to check inversion
                {
                    count++;
                }
            }
        }
        return count;
    }

    // Divide Approach for Inversion
    int CountInversion(ArrayList<Integer> arr, int l, int r) {
        int countInv = 0;
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;
            // Sort first and second halves
            countInv = countInv + CountInversion(arr, l, m); // Recursively doing Inversion for first sub array
            countInv = countInv + CountInversion(arr, m + 1, r); // Recursively doing Inversion for second sub array
            // Merge the sorted halves
            countInv = countInv + mergeInversion(arr, l, m, r); // Counting Inversion in from first to Second array
        }
        return countInv;
    }

    // Driver method
    public static void main(String args[]) throws Exception {
        CountingInversion ob = new CountingInversion();
        // TODO need to constructor for read file
        File readFile = new File("input3.txt");
        // ArrayList<Integer> array = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        try {
            Scanner scanner = new Scanner(readFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                // splitting the data based on space and store it in an string array.
                String[] ar = data.split(" ");
                // loop to store string elements as integer elements in array
                for (int i = 0; i < ar.length; i++) {
                    arr.add(Integer.parseInt(ar[i]));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // System.out.println(readFile); completed!
        // Enter Array Values Here.
        int val;
        // int arr_sor[] = { 21, 59, 98, 23, 1, 5, 97 };
        // val = ob.CountInversion(arr_sor, 0, arr_sor.length - 1);
        // System.out.println("\nTotal Number Of Inversion(Sorted): " + val);
        // Here we use the array which stores the elements in reading file
        // printing the output
        val = ob.CountInversion(arr, 0, arr.size() - 1);
        System.out.println(val); // Here ouptut is 0.
    }
}