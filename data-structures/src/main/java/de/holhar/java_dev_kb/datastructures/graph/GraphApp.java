package de.holhar.java_dev_kb.datastructures.graph;

public class GraphApp {

    public static void main(String[] args) {
        //Lets create GraphNodes as given as an example in the article
        GraphNode nA = new GraphNode('A');
        GraphNode nB = new GraphNode('B');
        GraphNode nC = new GraphNode('C');
        GraphNode nD = new GraphNode('D');
        GraphNode nE = new GraphNode('E');
        GraphNode nF = new GraphNode('F');

        //Create the graph, add GraphNodes, create edges between GraphNodes
        Graph g = new Graph();
        g.addNode(nA);
        g.addNode(nB);
        g.addNode(nC);
        g.addNode(nD);
        g.addNode(nE);
        g.addNode(nF);
        g.setRootNode(nA);

        g.connectNode(nA, nB);
        g.connectNode(nA, nC);
        g.connectNode(nA, nD);

        g.connectNode(nB, nE);
        g.connectNode(nB, nF);
        g.connectNode(nC, nF);


        //Perform the traversal of the graph
        System.out.println("DFS Traversal of a tree is ------------->");
        g.dfs();

        System.out.println("\nBFS Traversal of a tree is ------------->");
        g.bfs();
    }
}
