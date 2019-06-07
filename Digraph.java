import java.util.*;
/**
 * CPSC 6109 
 * Project 3 Shortest Paths 
 *
 * This class represents a Digraph and contains methods to calculate shortest
 * paths from a starting vertex. It also has a method to print the results 
 * to screen.
 *
 * @author Jill Eliceiri
 */
public class Digraph {

    //INSTANCE VARIABLES
    private int numberOfEdges;
    private int numberOfVertices;

    /* A Linked Hash Map (keys, values) is used to represent the adjacency list. 
    The keys are the vertices and the values are the list of eddges which are 
    the successors.*/
    LinkedHashMap<Vertex, ArrayList<Edge>> adjacencyList;

    //This represents the starting vertex for the algorithm (source).
    Vertex startingVertexForAlgorithm;

    //CONSTRUCTOR
    Digraph() {
        //for each vertex, need to initialize a new arrayList
        adjacencyList = new LinkedHashMap();
    }

    //GETTERS AND SETTERS
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public LinkedHashMap<Vertex, ArrayList<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public void addVertex(Vertex vertex) {
        //perform check if it is present
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    //add an edge to a vertex's successor edge list
    public void addSuccessor(Vertex v1, Edge e2) {
        adjacencyList.get(v1).add(e2);
    }

    /**
     * This method takes file input and populates a graph by adding new
     * vertices, edges, and adding them to the adjacency list. It must first
     * check if the vertex is already present in the graph.
     */
    public void addEdge(String v1, String v2, int weight) {

        if (adjacencyList.isEmpty()) {//First line read
            Vertex vertex1 = new Vertex(v1);
            Vertex vertex2 = new Vertex(v2);
            //start with this vertex
            startingVertexForAlgorithm = vertex1;
            startingVertexForAlgorithm.setWeightFromSource(0);
            Edge newEdge = new Edge(vertex1, vertex2, weight);
            adjacencyList.put(vertex1, new ArrayList<>());
            adjacencyList.put(vertex2, new ArrayList<>());
            addSuccessor(vertex1, newEdge);
            numberOfVertices += 2;
        } else {
            boolean v1IsFound = false;
            boolean v2IsFound = false;
            Vertex vertex1 = null;
            Vertex vertex2 = null;

            for (Map.Entry<Vertex, ArrayList<Edge>> entry
                    : adjacencyList.entrySet()) {
                Vertex vertexKey = entry.getKey();
                if (vertexKey.getName().equals(v1)) {
                    v1IsFound = true;
                    vertex1 = vertexKey;
                }
                if (vertexKey.getName().equals(v2)) {
                    v2IsFound = true;
                    vertex2 = vertexKey;
                }
            }
            if (!v1IsFound) {
                vertex1 = new Vertex(v1);
                adjacencyList.put(vertex1, new ArrayList<>());
                numberOfVertices++;
            }
            if (!v2IsFound) {
                vertex2 = new Vertex(v2);
                adjacencyList.put(vertex2, new ArrayList<>());
                numberOfVertices++;
            }
            Edge newEdge = new Edge(vertex1, vertex2, weight);
            //add the new edge as a successor to vertex
            addSuccessor(vertex1, newEdge);
        }
        numberOfEdges++;
    }

    /**
     * This method prints a graph. It is used for testing to see if a graph has
     * been populated correctly after reading the input file.
     */
    public void print() {
        System.out.println("\n+ Graph: ");
        for (Map.Entry<Vertex, ArrayList<Edge>> entry : 
                adjacencyList.entrySet()){
            Vertex key = entry.getKey();
            ArrayList<Edge> value = entry.getValue();

            System.out.println("Vertex " + key.getName() + ", Edges: ");
            //System.out.println("Key = " + key + ", Value = " + value);
            for (Edge e : value) {
                System.out.println("\tSource: " + e.getSource().getName() 
                        + " Destination: " + e.getDestination().getName() 
                        + " Weight: " + e.getWeight());
            }
        }
        System.out.println(" ");
    }

    /**
     * This method calculates the shortest paths. It uses a priority queue to
     * hold vertices to examine. The adjacency list of successors are looked at
     * and the weight from source is updated if it is smaller than the current
     * weight.
     */
    public void calculate() {
        //use a priority queue
        PriorityQueue<Vertex> verticesToExamine = new PriorityQueue<>();
        //add the first vertex
        verticesToExamine.add(startingVertexForAlgorithm);

        while (!verticesToExamine.isEmpty()) {
            //remove vertex from vertices examination list
            Vertex currentVertex = verticesToExamine.poll();
            //System.out.println("CurrentVertex: " + currentVertex.getName());

            /*examine all adjacent vertices: obtain them through the edges.
            a.k.a. relaxing the edges: test if there is a shorter path)
            */
            for (Edge edge : adjacencyList.get(currentVertex)) {
                //get the endpoint vertex of the edge (destination)
                Vertex successorVertex = edge.getDestination();
                //System.out.println("EdgeDestinationVertex: " 
                //+ successorVertex.getName());
                //check if new weight is less than current weight
                int newWeightFromSource = currentVertex.getWeightFromSource()
                        + edge.getWeight();
                //found a shorter path: update vertex to reflect new weight
                if (newWeightFromSource
                        < successorVertex.getWeightFromSource()) {
                    successorVertex.setWeightFromSource(newWeightFromSource);
                    //update the previous path vertex (predecessor)
                    successorVertex.setPreviousVertexPath(currentVertex);
                    //add to examination list
                    verticesToExamine.add(successorVertex);
                }
            }
        }
    }

    /**
     * This method loops through the adjacency list and prints results to the
     * screen, including the shortest path route from the starting vertex.
     */
    public void printResults() {
        //print the results
        System.out.println("Vertex" + "\t" + "Dist." + "\t" + "Path");
        System.out.println("------" + "\t" + "-----" + "\t" + "----");
        for (Map.Entry<Vertex, ArrayList<Edge>> entry
                : adjacencyList.entrySet()) {
            Vertex key = entry.getKey();
            ArrayList<Edge> value = entry.getValue();
            //Print the vertex information, but not the first
            if (key != startingVertexForAlgorithm) {
                System.out.print(key.getName() + "\t"
                        + key.getWeightFromSource() + "\t");
                ArrayList<Vertex> shortestPathToPrint = new ArrayList<>();
                Vertex previous = key.getPreviousVertexPath();
                //populate shortest path routes
                while (previous != null) {
                    shortestPathToPrint.add(0, previous);
                    previous = previous.getPreviousVertexPath();
                }
                //Format path results with commas (reverse to get right order)
                for (int i = shortestPathToPrint.size() - 1; i >= 0; i--) {
                    String toPrint = shortestPathToPrint.get(i).getName();
                    if (shortestPathToPrint.size() == 1) {
                        System.out.print(toPrint + "");
                    } else {
                        if (i == shortestPathToPrint.size() - 1) {
                            System.out.print(toPrint + ", ");
                        } else if (i > 0 && i < shortestPathToPrint.size() - 1){
                            System.out.print(toPrint + ", ");
                        } else /*(i == 0)*/ {
                            System.out.print(toPrint + "");
                        }
                    }
                }
                System.out.print("\n");
            }

        }//END PRINT LOOP
    }
}
