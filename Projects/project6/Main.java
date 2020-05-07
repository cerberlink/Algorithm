
/*
Author Carlos Guerrero 

Professor Aaron Biel 

Project 6: stairway dynamic programming

I am going to use the bottom-up method in the dynamic programming.
*/
import java.util.*;
import java.io.*;

public class Main {
    // create the function for stair in dp.
    static int stair(int n) {
        int[] dp = new int[n + 1];
        // set up the base case
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) throws FileNotFoundException {
        // create the file for reading the input.
        File file = new File("input6.txt");
        Scanner sc = new Scanner(file);
        int first = -1;
        // loop to read the input and function.
        while (sc.hasNextInt()) {
            first = sc.nextInt();
            int output = stair(first);
            System.out.println(output);
        }
    }
}