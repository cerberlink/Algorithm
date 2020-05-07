
/*
Author: Carlos Guerrero

Professor Aaron Biel 

Project 8
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Count {

    // Returns count of possible paths to reach
    // cell at row number m and column number n
    // from the topmost leftmost cell (cell at 1, 1)
    static int getTotalWays(int firstval, int secondval) {
        // Initialising value array where value[x][y] shows the total path to reach at
        // x,y
        int value[][] = new int[firstval][secondval];
        // initialising all firt row and first column values as 0, as there is one path
        // only
        int i = 0, j = 0;
        for (i = 0; i < firstval; i++)
            value[i][0] = 1;
        i = 0;
        for (j = 0; j < secondval; j++)
            value[0][j] = 1;
        // Calculate count of paths for other cells in
        // bottom-up manner using the recursive solution
        for (i = 1; i < firstval; i++) {
            for (j = 1; j < secondval; j++)
                // paths adding that are coming from vertical back and horizontal back
                value[i][j] = value[i - 1][j] + value[i][j - 1];
        }
        return value[firstval - 1][secondval - 1];
    }

    public static void main(String args[]) throws Exception {
        File file = new File("input8.txt");
        Scanner sc = new Scanner(file);
        int first = -1, second = -1;
        while (sc.hasNextInt()) {
            first = sc.nextInt();
            second = sc.nextInt();
            int NoOfWays = getTotalWays(first, second);
            System.out.println("Total No of ways to Reach at " + first + "," + second + ": " + NoOfWays);
        }
    }
}