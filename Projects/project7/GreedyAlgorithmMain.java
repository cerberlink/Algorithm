
/*
Author: Carlos Guerrero
Professor: Aaron Biel

Project 7

*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GreedyAlgorithmMain {
	// the input file name
	private static final String INPUT_FILE_NAME = "input7.txt";

	// the method to count the number of integers in the file
	private static int countNumIntegers() {
		// open the file to read from
		try {
			// use a scanner object
			Scanner scanner = new Scanner(new File(INPUT_FILE_NAME));
			// the number of integers to return
			int numIntegers = 0;
			// as long as there is input in the file
			while (scanner.hasNextInt()) {
				// read the integer
				scanner.nextInt();
				// increment the number of integers
				numIntegers++;
			}
			// close the input file
			scanner.close();
			// return the number of integers
			return numIntegers;
		}
		// if cannot open file, return -1 to indicate file could not be open
		catch (FileNotFoundException fnfe) {
			// return -1
			return -1;
		}
	}

	// method to read integers from input file
	private static void readIntegers(int array[]) {
		// open the file to read from
		try {
			// use a scanner object
			Scanner scanner = new Scanner(new File(INPUT_FILE_NAME));
			// the index
			int index = 0;
			// as long as there is input in the file
			while (scanner.hasNextInt()) {
				// read the integer
				int integer = scanner.nextInt();
				// add this integer to the array
				array[index++] = integer;
			}
			// close the input file
			scanner.close();
		}
		// if cannot open file, return -1 to indicate file could not be open
		catch (FileNotFoundException fnfe) {
			// do nothing
		}
	}

	// method to run the greedy algorithm
	private static boolean runGreedyAlgorithm(int array[]) {
		// the maximum reachable index
		int maxReachableIndex = 0;
		// iterate the array
		for (int i = 0; i < array.length; i++) {
			// if i is within max reachable index
			if (i <= maxReachableIndex) {
				// update max reachable index
				maxReachableIndex += array[i];
			}
		}
		// return true if maximum reachable index is
		return maxReachableIndex >= (array.length - 1);
	}

	public static void main(String[] args) {
		// count the number of integers
		int numIntegers = countNumIntegers();
		// if this is less than 0, stop the program
		if (numIntegers < 0) {
			// show message
			System.out.println("Cannot open input file " + INPUT_FILE_NAME);
			// terminate
			return;
		}
		// create the array
		int[] array = new int[numIntegers];
		// read the file into the array
		readIntegers(array);
		// run the greedy algorithm
		if (runGreedyAlgorithm(array))
			System.out.println("yes");
		else
			System.out.println("no");
	}

}
