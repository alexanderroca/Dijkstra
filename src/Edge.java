public class Edge {
    private final String origin;
    private final String destiny;
    private final int distance;

    public Edge(String origin, String destiny, int distance) {
        this.origin = origin;
        this.destiny = destiny;
        this.distance = distance;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public int getDistance() {
        return distance;
    }
}
