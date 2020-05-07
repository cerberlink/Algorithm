import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * A Graph represented as adjacency matrix.
 * 
 *
 */
public class GraphAM {

    // the adjacency matrix for the graph
    private int adjacencyMatrix[][];

    // the number of vertices in this graph
    private int V;

    // the constructor for this graph
    public GraphAM(String filename) {
        // count the number of vertices first
        countNumVertices(filename);

        // create the 2D array to hold the adjacency matrix
        adjacencyMatrix = new int[V][V];

        // read the graph
        readGraph(filename);
    }

    // the method to read the graph
    private void readGraph(String filename) {
        // try to open the file
        try {
            // use a scanner object to open the file
            Scanner scanner = new Scanner(new File(filename));

            // read the matrix
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // read the matrix
                    adjacencyMatrix[i][j] = scanner.nextInt();
                }
            }

            // close the scanner
            scanner.close();
        } catch (Exception e) {
            // show message
            System.out.println("Error reading from the file " + filename);

            // exit the program
            System.exit(0);
        }
    }

    // the method to count the number of vertices in the graph
    private void countNumVertices(String filename) {
        // try to open the file
        try {
            // use a scanner object to open the file
            Scanner scanner = new Scanner(new File(filename));

            // set the number of vertices to 0
            V = 0;

            // as long as there is a line in the input file
            while (scanner.hasNextLine()) {
                // read a line
                scanner.nextLine();

                // increment the number of vertices by 1
                V++;
            }

            // close the scanner
            scanner.close();
        } catch (FileNotFoundException e1) {
            // show message
            System.out.println("Cannot open file " + filename);

            // exit the program
            System.exit(0);
        }
    }

    // method to check if there is a cycle
    public boolean hasCycle() {
        // create an array to hold the vertices that have been visited
        boolean visited[] = new boolean[V];

        // set all the visited to false
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        // for all the vertices
        for (int i = 0; i < V; i++) {
            // if this is not visited and a cycle is detected
            if (!visited[i] && BFSCheckCycle(i, visited)) {
                return true;
            }
        }
        // finally return false
        return false;
    }

    // use BFS
    private boolean BFSCheckCycle(int s, boolean visited[]) {
        // the Queue for the BFS
        Queue<Integer> queue = new LinkedList<>();

        // add the starting vertex to the queue
        queue.add(s);
        // set this s to visited
        visited[s] = true;

        // the array to store the parents
        int[] parents = new int[V];
        // set all the parents to - 1
        for (int i = 0; i < parents.length; i++)
            parents[i] = -1;

        // as long as the queue is not empty
        while (!queue.isEmpty()) {
            // Dequeue a vertex from the queue
            int u = queue.poll();

            // Get all adjacent vertices of the dequeued vertex u.
            // If a adjacent has not been visited, then mark it visited and enqueue it.
            // We also mark parent so that parent is not considered for cycle.

            // for all the adjacent vertices to this vertex s
            for (int i = 0; i < V; i++) {
                // if there is an edge between s and i - it means i is adjacent to s
                if (adjacencyMatrix[u][i] == 1) {
                    // if this is not visited
                    if (!visited[i]) {
                        // mark this to visited
                        visited[i] = true;
                        // add this i to the queue
                        queue.add(i);
                        // set the parent of i
                        parents[i] = u;
                    } else if (parents[u] != i)
                        return true;
                }
            }
        }
        return false;
    }

    // method to get the number of layers
    public int getNumLayers() {
        // the number of layers to be returned
        int numLayers = -1;

        // the Queue for the BFS
        Queue<Integer> queue = new LinkedList<>();

        // create an array to hold the vertices that have been visited
        boolean visited[] = new boolean[V];

        // set all the visited to false
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        // add the starting vertex to the queue
        queue.add(0);
        // add null
        queue.add(null);
        // set this s to visited
        visited[0] = true;

        // BFS
        // as long as the queue is not empty
        while (!queue.isEmpty()) {
            // Dequeue a vertex from the queue
            Integer u = queue.poll();

            if (u == null) {
                numLayers++;
                queue.add(null);
                if (queue.peek() == null)
                    break;
                else
                    continue;
            }

            // for all the adjacent vertices to this vertex s
            for (int i = 0; i < V; i++) {
                // if there is an edge between s and i - it means i is adjacent to s
                if (adjacencyMatrix[u][i] == 1) {
                    // if this is not visited
                    if (!visited[i]) {
                        // mark this to visited
                        visited[i] = true;
                        // add this i to the queue
                        queue.add(i);
                    }
                }
            }
        }
        return numLayers;
    }
}
