package mazes;

import graphs.Graph;

public class MazeSolver {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

        graph.addValues("S", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "10", "11", "E");

        graph.connectUndirected("S", "1");
        graph.connectUndirected("1", "2", "3");
        graph.connectUndirected("3", "4", "11");
        graph.connectUndirected("4", "5", "10");
        graph.connectUndirected("5", "6", "9");
        graph.connectUndirected("6", "7", "8");
        graph.connectUndirected("11", "E");

        System.out.println(graph.depthFirstPath("S", "E"));
    }
}
