
public class Main {
    public static void main(String[] args){
        Graph graph = new Graph(Graph.GRAPH, "Sevilla", "Bilbao");

        graph.dijkstra(Graph.START);
        graph.printPath(Graph.END);
    }
}
