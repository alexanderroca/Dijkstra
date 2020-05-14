import java.util.*;

public class Graph {

    private HashMap<String, Node> graph;

    public static String START;
    public static String END;
    public static final Edge[] GRAPH = {
            // Distance from node "a" to node "b" is 7.
            // In the current Graph there is no way to move the other way (e,g, from "b" to "a"),
            // a new edge would be needed for that

            new Edge("Barcelona", "Madrid", 625),
            new Edge("Barcelona", "Valencia", 352),
            new Edge("Barcelona","Zaragoza", 314),
            new Edge("Barcelona", "Bilbao", 660),
            new Edge("Barcelona", "Valladolid", 728),
            new Edge("Barcelona", "Hospitalet de llobregat", 8),
            new Edge("Madrid", "Barcelona", 621),
            new Edge("Madrid", "Valencia", 357),
            new Edge("Madrid", "Zaragoza", 314),
            new Edge("Madrid", "Murcia", 399),
            new Edge("Madrid", "Bilbao", 397),
            new Edge("Madrid", "Cordoba", 394),
            new Edge("Madrid", "Valladolid", 217),
            new Edge("Madrid", "Hospitalet de llobregat", 613),
            new Edge("Valencia", "Barcelona", 350),
            new Edge("Valencia", "Madrid", 355),
            new Edge("Valencia", "Zaragoza", 309),
            new Edge("Valencia", "Malaga", 655),
            new Edge("Valencia", "Murcia", 228),
            new Edge("Valencia", "Hospitalet de llobregat", 343),
            new Edge("Sevilla", "Madrid", 532),
            new Edge("Sevilla", "Malaga", 205),
            new Edge("Sevilla", "Bilbao", 882),
            new Edge("Sevilla", "Vigo", 726),
            new Edge("Sevilla", "Cordoba", 141),
            new Edge("Sevilla", "Valladolid", 586),
            new Edge("Zaragoza", "Barcelona", 312),
            new Edge("Zaragoza", "Madrid", 355),
            new Edge("Zaragoza", "Valencia", 308),
            new Edge("Zaragoza", "Malaga", 843),
            new Edge("Zaragoza", "Murcia", 524),
            new Edge("Zaragoza", "Bilbao", 303),
            new Edge("Zaragoza", "Cordoba", 713),
            new Edge("Zaragoza", "Valladolid", 357),
            new Edge("Zaragoza", "Hospitalet de llobregat", 304),
            new Edge("Malaga", "Madrid", 531),
            new Edge("Malaga", "Sevilla", 200),
            new Edge("Malaga", "Zaragoza", 834),
            new Edge("Malaga", "Murcia", 524),
            new Edge("Malaga", "Cordoba", 159),
            new Edge("Malaga", "Valladolid", 739),
            new Edge("Murcia", "Madrid", 398),
            new Edge("Murcia", "Valencia", 226),
            new Edge("Murcia", "Sevilla", 523),
            new Edge("Murcia", "Zaragoza", 515),
            new Edge("Murcia", "Malaga", 399),
            new Edge("Murcia", "Cordoba", 476),
            new Edge("Murcia", "Hospitalet de llobregat", 578),
            new Edge("Bilbao", "Barcelona", 609),
            new Edge("Bilbao", "Madrid", 402),
            new Edge("Bilbao", "Valencia", 599),
            new Edge("Bilbao", "Zaragoza", 302),
            new Edge("Bilbao", "Malaga", 920),
            new Edge("Bilbao", "Murcia", 783),
            new Edge("Bilbao", "Vigo", 665),
            new Edge("Bilbao", "Valladolid", 279),
            new Edge("Vigo", "Barcelona", 1150),
            new Edge("Vigo", "Madrid", 597),
            new Edge("Vigo", "Zaragoza", 848),
            new Edge("Vigo", "Bilbao", 664),
            new Edge("Vigo", "Cordoba", 781),
            new Edge("Vigo", "Valladolid", 443),
            new Edge("Cordoba", "Barcelona", 862),
            new Edge("Cordoba", "Madrid", 397),
            new Edge("Cordoba", "Valencia", 521),
            new Edge("Cordoba", "Sevilla", 133),
            new Edge("Cordoba", "Zaragoza", 723),
            new Edge("Cordoba", "Malaga", 163),
            new Edge("Cordoba", "Murcia", 435),
            new Edge("Cordoba", "Vigo", 779),
            new Edge("Cordoba", "Valladolid", 604),
            new Edge("Valladolid", "Barcelona", 681),
            new Edge("Valladolid", "Madrid", 213),
            new Edge("Valladolid", "Valencia", 547),
            new Edge("Valladolid", "Sevilla", 584),
            new Edge("Valladolid", "Zaragoza", 363),
            new Edge("Valladolid", "Bilbao", 281),
            new Edge("Valladolid", "Murcia", 435),
            new Edge("Valladolid", "Vigo", 443),
            new Edge("Valladolid", "Cordoba", 583),
            new Edge("Hospitalet de llobregat", "Barcelona", 11),
            new Edge("Hospitalet de llobregat", "Madrid", 615),
            new Edge("Hospitalet de llobregat", "Valencia", 345),
            new Edge("Hospitalet de llobregat", "Zaragoza", 304),
            new Edge("Hospitalet de llobregat", "Bilbao", 601),
            new Edge("Hospitalet de llobregat", "Murcia", 435),
            new Edge("Hospitalet de llobregat", "Valladolid", 719),
    };



    public Graph(Edge[] edges, String origin, String destiny) {
        graph = new HashMap<>(edges.length);
        START = origin;
        END = destiny;

        for (Edge e : edges) {
            if (!graph.containsKey(e.getOrigin()))
                graph.put(e.getOrigin(), new Node(e.getOrigin()));
            if (!graph.containsKey(e.getDestiny()))
                graph.put(e.getDestiny(), new Node(e.getDestiny()));
        }   //if

        // another pass to set neighbouring vertices
        for (Edge e : edges) {
            graph.get(e.getOrigin()).neighbours.put(graph.get(e.getDestiny()), e.getDistance());
            // graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Node source = graph.get(startName);
        NavigableSet<Node> q = new TreeSet<>();

        // set-up vertices
        for (Node v : graph.values()) {
            v.setPreviousNode(v == source ? source : null);
            v.setDistance(v == source ? 0 : Integer.MAX_VALUE);
            q.add(v);
        }

        dijkstra(q);
    }

    private void dijkstra(final NavigableSet<Node> q) {
        Node u, v;
        while (!q.isEmpty()) {
            // vertex with shortest distance (first iteration will return source)
            u = q.pollFirst();
            if (u.getDistance() == Integer.MAX_VALUE)
                break; // we can ignore u (and any other remaining vertices) since they are unreachable

            // look at distances to each neighbour
            for (Map.Entry<Node, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); // the neighbour in this iteration

                final int alternateDist = u.getDistance() + a.getValue();
                if (alternateDist < v.getDistance()) { // shorter path to neighbour found
                    q.remove(v);
                    v.setDistance(alternateDist);
                    v.setPreviousNode(u);
                    q.add(v);
                }
            }
        }
    }

    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }

    /**
     * Prints the path from the source to every vertex (output order is not guaranteed)
     */
    public void printAllPaths() {
        for (Node v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }
}
