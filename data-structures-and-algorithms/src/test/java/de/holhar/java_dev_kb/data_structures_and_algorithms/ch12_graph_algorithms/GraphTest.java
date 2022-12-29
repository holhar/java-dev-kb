package de.holhar.java_dev_kb.data_structures_and_algorithms.ch12_graph_algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {

  @Test
  void givenAGraph_whenTraversingDepthFirst_thenExpectedResult() {
    Graph graph = createGraph();
    assertEquals("[Bob, Rob, Maria, Alice, Mark]",
        GraphTraversal.depthFirstTraversal(graph, "Bob").toString());
  }

  @Test
  void givenAGraph_whenTraversingBreadthFirst_thenExpectedResult() {
    Graph graph = createGraph();
    assertEquals("[Bob, Alice, Rob, Mark, Maria]",
        GraphTraversal.breadthFirstTraversal(graph, "Bob").toString());
  }

  @Test
  void givenAGraph_whenRemoveVertex_thenVertedNotFound() {
    Graph graph = createGraph();
    assertEquals("[Bob, Alice, Rob, Mark, Maria]",
        GraphTraversal.breadthFirstTraversal(graph, "Bob").toString());

    graph.removeVertex("Maria");
    assertEquals("[Bob, Alice, Rob, Mark]",
        GraphTraversal.breadthFirstTraversal(graph, "Bob").toString());
  }

  Graph createGraph() {
    Graph graph = new Graph();
    graph.addVertex("Bob");
    graph.addVertex("Alice");
    graph.addVertex("Mark");
    graph.addVertex("Rob");
    graph.addVertex("Maria");
    graph.addEdge("Bob", "Alice");
    graph.addEdge("Bob", "Rob");
    graph.addEdge("Alice", "Mark");
    graph.addEdge("Rob", "Mark");
    graph.addEdge("Alice", "Maria");
    graph.addEdge("Rob", "Maria");
    return graph;
  }
}
