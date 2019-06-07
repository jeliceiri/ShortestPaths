import java.io.*;
import java.util.*;

/**
 * Shortest Paths
 * April 16, 2019
 * 
 * This main class contains the main method which creates a graph and where 
 * the user has the option to enter a file name on the command line. Then, the 
 * graph is populated by calling appropriate methods to read and parse the file. 
 * Finally,the shortest path weights are calculated from the starting vertex 
 * of the graph and the results are printed to the screen.
 * 
 * @author Jill Eliceiri
 */
public class ShortestPaths {

    /**
     * Main method accepts user input to define an input file name, otherwise
     * it uses a default filename. It calls methods to read and parse the file,
     * and then it calls a method to calculate the shortest paths from the
     * start vertex.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //instantiate a new Digraph object
        Digraph graph = new Digraph();
        //instance variable represents the filename
        String filename;

        //create scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the input filename "
                + "(or simply press return to use ./project3-testA.tab)");
        filename = scanner.nextLine();
        scanner.close();
        if (filename.isEmpty()) {
            //filename = "project2-testA.tab";

            /*COMMENT OUT BELOW FOR ADDITIONAL TESTS or enter at command line*/
            //filename = "project3-testB.tab";
            //filename = "project3-testC.tab";
            filename = "project3-testA.tab";
        }
        //System.out.println("Filename: " + filename + " end");

        readInputFile(filename, graph);
        System.out.println();
        System.out.println("Importing vertices, edges "
                + "(and their weights) from ./project3-testA.tab . . .");
        
        System.out.println();
        System.out.println("Found " + graph.getNumberOfVertices() 
                + " vertices and " + graph.getNumberOfEdges() 
                + " edges in ./" + filename);
        
        System.out.println("The costs for the shortest path from the "
                + "first vertex (s):\n");
        graph.calculate();
        graph.printResults();
        //System.out.print("\n");
    }
    /**
     * This method creates a scanner object and reads the lines of a file. If a
     * line is not an empty string, then call the method to parse the line.
     */  
    static void readInputFile(String fileName, Digraph graph) {
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            //check if there is more input
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (!"".equals(str)) {
                    parseLine(str, graph);
                }
            }
            //handle exception
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        //close scanner
        scanner.close();
    }

    /**
     * This method parses a line using a tab delimiter and calls methods
     * to populate a graph using the file input.
     */
    private static void parseLine(String str, Digraph graph) throws IOException {

        Scanner scanner = new Scanner(str);
        scanner.useDelimiter("\t");
        while (scanner.hasNext()) {

            String a = scanner.next();
            String b = scanner.next();
            int c = Integer.parseInt(scanner.next());
            
            graph.addEdge(a, b, c); 
        }
        scanner.close();
    }
}
//TESTING BEFORE INPUT FILE
/*graph.addEdge("s", "t", 10 );
graph.addEdge("s", "y", 5 );
graph.addEdge("t", "x", 1 );
graph.addEdge("t", "y", 2 );
graph.addEdge("y", "t", 3 );
graph.addEdge("y", "x", 9 );
graph.addEdge("y", "z", 2 );
graph.addEdge("x", "z", 4 );
graph.addEdge("z", "s", 7 );
graph.addEdge("z", "x", 6);*/