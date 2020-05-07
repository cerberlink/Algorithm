/*

Developer: Carlos Guerrero
Professor: Aaron Biel
Project 2 - Graph

*/

import java.util.*;
import java.io.*;

class graph {
    // the main method in this graph
    public static void main(String args[]) {
        // create a graph using input2.txt
        GraphAM graph = new GraphAM("input2.txt");

        // print if this has a cycle
        System.out.println("Does cycle exist?: " + (graph.hasCycle() ? "Yes" : "No"));
        // print the number of layers in the graph
        System.out.println("Number of layers in the graph: " + graph.getNumLayers());
    }
}