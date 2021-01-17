package de.holhar.java_dev_kb.datastructures.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    private static final Logger LOGGER = LoggerFactory.getLogger(Graph.class);

    public GraphNode root;
    public ArrayList<GraphNode> nodes = new ArrayList<>();
    public int[][] adjMatrix; // edges will be represented as adjacency matrix
    int size;

    public Graph() {
    }

    public GraphNode getRootNode() {
        return root;
    }

    public void setRootNode(GraphNode node) {
        root = node;
    }

    public void addNode(GraphNode node) {
        nodes.add(node);
    }

    // connect two nodes
    public void connectNode(GraphNode start, GraphNode end) {
        if (adjMatrix == null) {
            size = nodes.size();
            adjMatrix = new int[size][size];
        }

        int startIndex = nodes.indexOf(start);
        int endIndex = nodes.indexOf(end);
        adjMatrix[startIndex][endIndex] = 1;
        adjMatrix[endIndex][startIndex] = 1;
    }

    private GraphNode getUnvisitedChildNode(GraphNode node) {
        int index = nodes.indexOf(node);
        int j = 0;
        while (j < size) {
            if (adjMatrix[index][j] == 1 && ((GraphNode) nodes.get(j)).visited == false) {
                return (GraphNode) nodes.get(j);
            }
            j++;
        }
        return null;
    }

    // BFS traversal of a tree
    public void bfs() {
        // BFS uses a Queue
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(this.root);
        printNode(this.root);
        root.visited = true;
        while (!queue.isEmpty()) {
            GraphNode node = (GraphNode) queue.remove();
            GraphNode child = null;
            while ((child = getUnvisitedChildNode(node)) != null) {
                child.visited = true;
                printNode(child);
                queue.add(child);
            }
        }
        clearNodes();
    }

    // DFS traversal of a tree
    public void dfs() {
        // DFS uses a Stack
        Stack<GraphNode> stack = new Stack<>();
        stack.push(root);
        root.visited = true;
        printNode(root);
        while (!stack.isEmpty()) {
            GraphNode node = (GraphNode) stack.peek();
            GraphNode child = getUnvisitedChildNode(node);
            if (child != null) {
                child.visited = true;
                printNode(child);
                stack.push(child);
            } else {
                stack.pop();
            }
        }
        clearNodes();
    }

    // clear visited property of all nodes
    private void clearNodes() {
        int i = 0;
        while (i < size) {
            GraphNode n = (GraphNode) nodes.get(i);
            n.visited = false;
            i++;
        }
    }

    // print the node's label
    private void printNode(GraphNode node) {
        LOGGER.debug("{}", node.label);
    }
}
