package de.holhar.java_dev_kb.data_structures_and_algorithms._deprecated.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphApp.class);

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
        LOGGER.debug("DFS Traversal of a tree is ------------->");
        g.dfs();

        LOGGER.debug("\nBFS Traversal of a tree is ------------->");
        g.bfs();
    }
}
