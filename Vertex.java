/**
 * Shortest Paths
 * April 16, 2019
 * 
 * This class represents a vertex.
 * 
 * @author Jill Eliceiri
 */
public class Vertex implements Comparable<Vertex> {

    //INSTANCE VARIABLES
    private String name;
    private int weightFromSource = Integer.MAX_VALUE;//represent infinity
    private Vertex previousVertexPath = null;

    //CONSTRUCTOR
    Vertex(String name) {
        this.name = name;
    }
    //GETTERS AND SETTERS
    public Vertex getPreviousVertexPath() {
        return previousVertexPath;
    }
    public void setPreviousVertexPath(Vertex previousVertexPath) {
        this.previousVertexPath = previousVertexPath;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeightFromSource() {
        return weightFromSource;
    }
    public void setWeightFromSource(int weightFromSource) {
        this.weightFromSource = weightFromSource;
    }
    @Override
    public int compareTo(Vertex o) {
        int x = 0;
        return x;
    }
}
