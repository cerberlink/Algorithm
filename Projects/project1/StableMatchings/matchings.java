/*
    Author: Carlos Guerrero
    Professor: Aaron Biel
    Date: 02/09/2020
    Project 1: Stable Matchings
*/

//Make a plan before coding the project
/*
1. take an instance of the Stable Matching Problem and return the total number possible stable matches.


=======
Input: I need to create the input.txt.

then it will need to read from what I suppose to expect:

- how many men/women will there be?

- each preference list for the men

- each preference list for the women

- You will separate numbers in each list with a space


example 1:
4 that will let me know that there are 4 men and 4 women

below the list of preference is men
1 2 3 4
2 1 3 4
3 4 1 2
4 3 1 2

below the list of preference is women
2 1 3 4
1 2 3 4
4 3 1 2
3 4 1 2
*/
import java.util.*;
import java.io.*;

class matchings {
    // START create the definition in global variables scope
    static String text = "input1.txt";
    static boolean debug = false;
    static LinkedList<LinkedList<String>> choices = new LinkedList<LinkedList<String>>();
    static LinkedList<String> matches = new LinkedList<String>();
    static int totalPeople;
    // END create the definition

    public static void main(String[] args) {
        // create the read the file from input.txt
        readFile();
        // matchings algorithm
        matching();
    }

    private static void readFile() {
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(text));
            line = br.readLine();// read the first line to get the # people
            totalPeople = Integer.parseInt(line); // string -> int convert
            while ((line = br.readLine()) != null) { // iteration each loop to read the line in file.
                String[] temp = line.split(" ");// whitespace character
                LinkedList<String> row = new LinkedList<String>();
                for (int i = 0; i < temp.length; i++) {
                    row.add(temp[i]);
                    if (debug) {
                        System.out.println(String.format("added: &s", temp[i]));
                    }
                }
                choices.add(row);// add the LL (linkedlist) row to the main's LL
            }
        } catch (FileNotFoundException e) { // use the case if we cannot find the exiting filename in the same
                                            // directionary.
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (debug) {
            System.out.println(String.format("totalPeople: &s", totalPeople));
            System.out.println(String.format("choices LL: &s\n\n", choices.toString()));
        }
    }

    // GSA algorithm
    private static void matching() {
        // create the variables
        int count = 0;
        boolean finished = false;
        int rank, target, maxRankCheck;
        // END create the variable
        boolean[] matched = new boolean[totalPeople + 1];
        // iteration for people
        for (maxRankCheck = 0; maxRankCheck < totalPeople - 2; maxRankCheck++) {
            for (target = 0; target < totalPeople; target++) { // move through rows (people)
                if (matched[target] == false) { // check if target already matched
                    LinkedList targetWants = new LinkedList();
                    targetWants = (LinkedList) (choices.get(target)).clone(); // deconstruct the LL matrix
                    for (rank = maxRankCheck; rank >= 0; rank--) { // move through col (rank)
                        int targetWantsMate = Integer.parseInt(targetWants.get(rank).toString());
                        if (debug)
                            System.out.println(String.format("TARGET %1$s @ RANK %2$d - WANTS %3$d @ RANK %4$d",
                                    target + 1, maxRankCheck, targetWantsMate, rank));
                        if (matched[targetWantsMate] == false) { // check if targetWant already matched
                            LinkedList targetMateRank = new LinkedList();
                            targetMateRank = (LinkedList) (choices.get(targetWantsMate - 1)).clone();
                            if (debug)
                                System.out.println(
                                        String.format("%1$d ?= %2$s", target + 1, targetMateRank.get(rank).toString()));
                            count++;
                            if (target + 1 == Integer.parseInt(targetMateRank.get(rank).toString())
                                    || (target + 1 == Integer.parseInt(targetMateRank.get(rank + 1).toString()))) {
                                if (debug)
                                    System.out.println("MATCH FOUND!!!");
                                matched[target] = true;
                                matched[targetWantsMate - 1] = true;
                                String tempMatch = "(" + String.valueOf(target + 1) + ", "
                                        + String.valueOf(targetWantsMate) + ")";
                                matches.add(tempMatch);
                                break;
                            } else if (debug) {
                                System.out.println("no match");
                            }
                        } else if (debug) {
                            System.out.println("WANTED MATE already matched!");
                        }
                    }
                } else if (debug) {
                    System.out.println("TARGET already matched!");
                }
            }
            finished = true; // reset, check if all matchings matched
            for (int i = 0; i < totalPeople; i++) {
                if (!matched[i]) {
                    finished = false;
                    break;
                }
            }
        }
        if (finished) {
            System.out.println(String.format("%d", matches.size()));
        }
        System.out.println(String.format("%s", matches.size()));
    }
}

/*
 * else { System.out.println("odd number of people"); } // if success is
 * complete if (finished) { System.out.println(String.format("%d",
 * matches.size())); } else { System.out.println("unstable matchings"); }
 */