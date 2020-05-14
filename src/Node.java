import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node implements Comparable<Node>{

    private final String city;
    private int distance = Integer.MAX_VALUE;
    private Node previousNode = null;
    public final Map<Node, Integer> neighbours = new HashMap<>();

    public Node(String city) {
        this.city = city;
    }

    public void printPath() {
        if (this == this.previousNode) {
            System.out.printf("%s", this.city);
        } else if (this.previousNode == null) {
            System.out.printf("%s(unreached)", this.city);
        } else {
            this.previousNode.printPath();
            System.out.printf(" -> %s(%d)", this.city, this.distance);
        }
    }

    public int compareTo(Node other) {
        if (distance == other.distance)
            return city.compareTo(other.city);

        return Integer.compare(distance, other.distance);
    }

    @Override
    public String toString() {
        return "(" + city + ", " + distance + ")";
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
}
