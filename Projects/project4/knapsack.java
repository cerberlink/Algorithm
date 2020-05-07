import java.util.*;
import java.io.*;

class knapsack {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack(int W, ArrayList<Integer> arrli) {

        int n = arrli.size() / 2;
        ArrayList<Integer> value = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            value.add(arrli.get(i));
            weight.add(arrli.get(i + n));
        }

        int K[][] = new int[n + 1][W + 1];
        // Build table K[][] in bottom up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (weight.get(i - 1) <= w)
                    K[i][w] = max(value.get(i - 1) + K[i - 1][w - weight.get(i - 1)], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }

    public static void main(String args[]) throws FileNotFoundException {
        File text = new File("./input4.txt");
        Scanner scnr = new Scanner(text);

        int capacity = scnr.nextInt();
        ArrayList<Integer> arrli = new ArrayList<>();

        while (scnr.hasNextInt()) {
            arrli.add(scnr.nextInt());
        }

        int maxValue = knapSack(capacity, arrli);
        System.out.println(maxValue);
        System.out.println();
        try {
            FileWriter myWriter = new FileWriter("./input4.txt");
            myWriter.write(Integer.toString(maxValue));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}