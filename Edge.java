/**
 * Shortest Paths
 * April 16, 2019
 * 
 * This class represents an Edge.
 * 
 * @author Jill Eliceiri
 */
public class Edge {
    //INSTANCE VARIABLES
    Vertex source;
    Vertex destination;
    int weight;
    //CONSTRUCTOR
    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    //GETTERS AND SETTERS
    public Vertex getSource() {
        return source;
    }
    public void setSource(Vertex source) {
        this.source = source;
    }
    public Vertex getDestination() {
        return destination;
    }
    public void setDestination(Vertex destination) {
        this.destination = destination;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
