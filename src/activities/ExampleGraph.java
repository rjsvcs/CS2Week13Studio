package activities;

import graphs.Graph;

public class ExampleGraph {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addValue("A");
        graph.addValue("B");
        graph.addValue("C");
        graph.addValue("D");
        graph.addValue("E");
        graph.addValue("F");
        graph.addValue("G");
        graph.addValue("H");
        graph.addValue("I");
        graph.addValue("J");
        graph.addValue("K");

        graph.connectUndirected("A", "B", "C");
        graph.connectUndirected("B", "D", "H");
        graph.connectUndirected("C", "D", "E", "H");
        graph.connectUndirected("D", "F");
        graph.connectUndirected("F", "G", "H");

        graph.connectUndirected("I", "J", "K");
        graph.connectUndirected("J", "K");

        System.out.println("BFS from A to G: " +
                graph.breadthFirstSearch("A", "G"));
        System.out.println("BFS from A to J: " +
                graph.breadthFirstSearch("A", "J"));
        System.out.println("BFS from J to K: " +
                graph.breadthFirstSearch("J", "K"));

        System.out.println("BFS path from A to G: " +
                graph.breadthFirstPath("A", "G"));
        System.out.println("BFS path from A to J: " +
                graph.breadthFirstPath("A", "J"));
        System.out.println("BFS path from J to K: " +
                graph.breadthFirstPath("J", "K"));

        System.out.println("DFS from A to G: " +
                graph.depthFirstSearch("A", "G"));
        System.out.println("DFS from A to J: " +
                graph.depthFirstSearch("A", "J"));
        System.out.println("DFS from J to K: " +
                graph.depthFirstSearch("J", "K"));

        System.out.println("DFS path from A to G: " +
                graph.depthFirstPath("A", "G"));
        System.out.println("DFS path from A to J: " +
                graph.depthFirstPath("A", "J"));
        System.out.println("DFS path from J to K: " +
                graph.depthFirstPath("J", "K"));
    }


}
