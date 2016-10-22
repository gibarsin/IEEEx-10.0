import sun.security.provider.certpath.Vertex;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PirateLifeForMe {

  /**************************************************************/
  private static class MyArc {

    private Integer value;

    public MyArc(Integer value) {
      this.value = value;
    }

    public Double getValue() {
      return value.doubleValue();
    }

    public String toString() {
      return value.toString();
    }
  }
  /**************************************************************/

  /**
   *
   * Clase abstracta para Grafos (no multigrafos). No soporta multigrafos ni lazos<br>
   * La mayoria de los metodos se implementan como una "traduccion casi directa" de la
   * definicion del problema. Se sugiere pensar alternativas mas eficientes y/o mas
   * claras.
   *
   * @param <V>
   * @param <E>
   */
  private static abstract class GraphAdjList<V, E extends MyArc> {

    protected class Node {
      public V info;
      public boolean visited;
      // coloreo, etc.
      public List<Arc> adj;

      public Node(V info) {
        this.info = info;
        this.visited = false;
        this.adj = new ArrayList<Arc>();
      }

      @Override
      public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((info == null) ? 0 : info.hashCode());
        return result;
      }

      @Override
      public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        final Node other = (Node) obj;
        if (info == null) {
          if (other.info != null)
            return false;
        } else if (!info.equals(other.info))
          return false;
        return true;
      }
    }

    protected class Arc {
      public E info;
      public Node neighbor;

      public Arc(E info, Node neighbor) {
        super();
        this.info = info;
        this.neighbor = neighbor;
      }
    }

    protected HashMap<V, Node> nodes; // Para ubicar los nodos rápidamente
    protected List<Node> nodeList; // Para recorrer la lista de nodos

    public GraphAdjList() {
      this.nodes = new HashMap<V, Node>();
      this.nodeList = new ArrayList<Node>();
    }

    public boolean isEmpty() {
      return nodes.size() == 0;
    }

    public void AddVertex(V vertex) {
      if (!nodes.containsKey(vertex)) {
        Node node = new Node(vertex);
        nodes.put(vertex, node);
        nodeList.add(node);
      }
    }

    public void removeVertex(V v) {
      Node node = nodes.get(v);
      if (node == null)
        return;

      // Primero removerlo de la lista de adyacencia de sus vecinos
      Arc e = new Arc(null, node);
      for (Node n : getNodes()) {
        if (!n.equals(node))
          n.adj.remove(e);
      }

      // Eliminar el nodo
      nodes.remove(v);
      nodeList.remove(node);
    }

    public void addArc(V v, V w, E e) {
      Node origin = nodes.get(v);
      Node dest = nodes.get(w);
      if (origin != null && dest != null && !origin.equals(dest)) {
        for (Arc arc : origin.adj) {
          if (arc.neighbor.info.equals(w)) {
            return;
          }
        }
        origin.adj.add(new Arc(e, dest));
      }
    }

    public void removeArc(V v, V w) {
      Node origin = nodes.get(v);
      if (origin == null)
        return;

      for (int i = 0; i < origin.adj.size(); i++) {
        if ( origin.adj.get(i).neighbor.info.equals(w)) {
          origin.adj.remove(i);
          return;
        }
      }

    }

    public int arcCount() {
      int count = 0;
      for (Node n : getNodes())
        count += n.adj.size();
      return count;
    }

    public E isArc(V v, V w) {
      Node origin = nodes.get(v);
      if (origin == null)
        return null;

      for (Arc e : origin.adj) {
        if (e.neighbor.info.equals(w)) {
          return e.info;
        }
      }
      return null;

    }

    @SuppressWarnings("unchecked")
    public List<V> neighbors(V v) {
      Node node = nodes.get(v);
      if (node != null) {
        List<V> l = new ArrayList(node.adj.size());
        for (Arc e : node.adj) {
          l.add(e.neighbor.info);
        }
      }
      return null;
    }

    public int vertexCount() {
      return nodes.size();
    }

    protected List<Node> getNodes() {
      return nodeList;
    }

    protected void clearMarks() {
      for (Node n : getNodes()) {
        n.visited = false;
      }

    }

    public List<V> DFS(V origin) {
      Node node = nodes.get(origin);
      if (node == null)
        return null;
      clearMarks();
      List<V> l = new ArrayList<V>();
      this.DFS(node, l);
      return l;
    }

    protected void DFS(Node origin, List<V> l) {
      if (origin.visited)
        return;
      l.add(origin.info);
      origin.visited = true;
      for (Arc e : origin.adj)
        DFS(e.neighbor, l);
    }

    public List<V> BFS(V origin) {
      Node node = nodes.get(origin);
      if (node == null)
        return null;
      clearMarks();
      List<V> l = new ArrayList<V>();

      Queue<Node> q = new LinkedList<Node>();
      q.add(node);
      node.visited = true;
      while (!q.isEmpty()) {
        node = q.poll();
        if (!node.visited) {
          l.add(node.info);
        }
        for (Arc e : node.adj) {
          if (!e.neighbor.visited) {
            q.add(e.neighbor);
            e.neighbor.visited = true;
          }
        }
      }
      return l;
    }

    public boolean isPath(V origin, V dest) {
      List<V> l = DFS(origin);
      if (l == null)
        return false;
      return l.contains(dest);
    }

    /**
     * Construye una lista donde para cada nodo informa la mínima distancia al
     * origen
     *
     * @param origin
     */
    public HashMap<V, Double> Dijkstra(V origin) {
      Node nodeOrigin = nodes.get(origin);
      if (nodeOrigin == null)
        return null;

      clearMarks();
      // Para cada nodo guardar cuál es la distancia acumulada
      HashMap<V, Double> distance = new HashMap<V, Double>(nodes.size());

      // Creamos una lista de nodos visitados
      List<Node> visited = new ArrayList<Node>(nodes.size());
      distance.put(origin, 0.0);
      nodeOrigin.visited = true;
      visited.add(nodeOrigin);

      Arc min;
      do {
        // Tomar el nodo sin visitar más cercano al origen
        min = null;
        Double minDist = 0.0;
        for (Node node : visited) {
          Double distToNode = distance.get(node.info);
          for (Arc e : node.adj) {
            if (!e.neighbor.visited
                    && (min == null || e.info.getValue() + distToNode < minDist)) {
              min = e;
              minDist = e.info.getValue() + distToNode;
            }
          }
        }
        if (min != null) {
          visited.add(min.neighbor);
          min.neighbor.visited = true;
          distance.put(min.neighbor.info, minDist);
        }
      } while (min != null);

      return distance;
    }

    ///////////////////////////////////////////////
    /**
     * Construye una lista donde para cada nodo informa la mínima distancia al
     * origen
     *
     * @param origin
     */
    public HashMap<V, Double> DijkstraMinDistance(V origin, V dest) {
      Node nodeOrigin = nodes.get(origin);
      if (nodeOrigin == null)
        return null;

      clearMarks();
      // Para cada nodo guardar cuál es la distancia acumulada
      HashMap<V, Double> distance = new HashMap<V, Double>(nodes.size());

      // Creamos una lista de nodos visitados
      List<Node> visited = new ArrayList<Node>(nodes.size());
      distance.put(origin, 0.0);
      nodeOrigin.visited = true;
      visited.add(nodeOrigin);

      Arc min;
      do {
        // Tomar el nodo sin visitar más cercano al origen
        min = null;
        Double minDist = 0.0;
        for (Node node : visited) {
          Double distToNode = distance.get(node.info);
          for (Arc e : node.adj) {
            if (!e.neighbor.visited
                    && (min == null || e.info.getValue() + distToNode < minDist)) {
              min = e;
              minDist = e.info.getValue() + distToNode;
            }
          }
        }
        if (min != null) {
          distance.put(min.neighbor.info, minDist);
          visited.add(min.neighbor);
          min.neighbor.visited = true;
          if(min.neighbor.info.equals(dest)){
            return distance;
          }
        }
      } while (min != null);

      return distance;
    }
    ///////////////////////////////////////////////

  }
  /**************************************************************/

  private static class Graph<V, E extends MyArc> extends GraphAdjList<V, E> {

    @Override
    public void addArc(V v, V w, E e) {
      super.addArc(v, w, e);
      super.addArc(w, v, e);
    }

    @Override
    public void removeArc(V v, V w) {
      super.removeArc(v, w);
      super.removeArc(w, v);
    }

    public int degree(V v) {
      Node node = nodes.get(v);
      if (node != null) {
        return node.adj.size();
      }
      return 0;
    }

    public boolean isConnected() {
      if (isEmpty()) {
        return true;
      }
      clearMarks();
      List<Node> l = getNodes();
      List<V> laux = new ArrayList<V>();
      DFS(l.get(0), laux);
      for (Node node : l) {
        if (!node.visited) {
          return false;
        }
      }
      return true;
    }

    public int connectedComponents() {
      clearMarks();
      return pathCount();
    }

    private int pathCount() {
      int count = 0;
      Node node;
      while ((node = unvisited()) != null) {
        count++;
        DFS(node, new ArrayList<V>());
      }
      return count;
    }

    private Node unvisited() {
      for(Node node : getNodes()) {
        if (! node.visited )
          return node;
      }
      return null;
    }

    public boolean cutVertex(V vertex) {
      Node node = nodes.get(vertex);
      if (node == null || node.adj.size() == 0)
        return false;
      clearMarks();
      int components = pathCount();
      clearMarks();
      node.visited = true;
      return components != pathCount();
    }

    public boolean isBridge(V v, V w) {
      E e = isArc(v,w);
      if ( e == null)
        return false;
      int components = connectedComponents();
      removeArc(v, w);
      int newComponents = connectedComponents();
      addArc(v, w, e);
      return components != newComponents;

    }

  }

  /**************************************************************/

  private static class DiGraph<V, E extends MyArc> extends GraphAdjList<V, E> {

    public int inDegree(V v) {
      int count = 0;
      Node node = nodes.get(v);
      for (Node n : getNodes()) { // Recorremos lista de nodos
        if (!n.equals(node)) {
          for (Arc adj : n.adj)
            // Recorremos lista de adyacencia
            if (adj.neighbor.equals(node))
              count++;
        }
      }
      return count;
    }

    public int outDegree(V v) {
      Node node = nodes.get(v);
      if (node != null) {
        return node.adj.size();
      }
      return 0;
    }


  }

  /**************************************************************/
  private static class MyValue{
    private char c;
    private long id;

    public MyValue(char c, long id) {
      this.c = c;
      this.id = id;
    }

    public char getC() {
      return c;
    }

    public long getId() {
      return id;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      MyValue myValue = (MyValue) o;

      return id == myValue.id;

    }

    @Override
    public int hashCode() {
      return (int) (id ^ (id >>> 32));
    }
  }

  /**************************************************************/

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    final long N = scanner.nextLong();
    final long M = scanner.nextLong();
    final long testCases = scanner.nextLong();
    long x1, x2, y1, y2, origin, dest;
    DiGraph<Long, MyArc> graph = new DiGraph<>();

    scanner.nextLine();

    char[] line, prevLine = null;
    for (long i=0; i<N; i++){
      line = scanner.nextLine().toCharArray();
      populate(graph, line, i, prevLine);
      prevLine = line;
    }

    // printGraph(graph);

    for(int i=0; i < testCases; i++) {
      x1 = scanner.nextLong() - 1;
      x2 = scanner.nextLong() - 1;
      y1 = scanner.nextLong() - 1;
      y2 = scanner.nextLong() - 1;

      origin = x1 * M + x2;
      dest = y1 * M + y2;

      System.out.println(String.valueOf(findMinPath(origin, dest, graph)));
    }
  }

  private static void printGraph(DiGraph<Long, MyArc> graph) {
    for(GraphAdjList.Node node : graph.getNodes()){

    }
  }

  private static long findMinPath(long origin, long dest, DiGraph<Long, MyArc> graph) {
//    if(origin == dest){
//     return 0;
//    }
    //graph.clearMarks();
   // return graph.Dijkstra(origin).get(dest).longValue();
   return graph.DijkstraMinDistance(origin, dest).get(dest).longValue();

  }

  private static void populate(DiGraph graph, char[] line, long row, char[] prevLine) {
    int i=0;
    long id;
    char cur, other;
    int e1, e2;

    for (char c : line){
      id = i + row * line.length;
      graph.AddVertex(id);

      cur = line[i];

      // Add arc with the left node
      if(i > 0){
        other = line[i-1];
        e1 = changedTerrain(cur, other);
        e2 = changedTerrain(other, cur);
        graph.addArc(id, id-1, new MyArc(e1));
        graph.addArc(id-1, id, new MyArc(e2));
      }
      // Add arc with the upper node
      if(row > 0){
        other = prevLine[i];
        e1 = changedTerrain(cur, other);
        e2 = changedTerrain(other, cur);
        graph.addArc(id, id-line.length, new MyArc(e1));
        graph.addArc(id-line.length, id, new MyArc(e2));
//        graph.addArc(id, id-line.length, new MyArc(line[i] == '~' ? 0:1));
//        graph.addArc(id-line.length, id, new MyArc(prevLine[i]== '~' ? 0:1));
      }

      // Add arc with the upper-right node
      if(row>0 && i<line.length-1){
        other = prevLine[i+1];
        e1 = changedTerrain(cur, other);
        e2 = changedTerrain(other, cur);
        graph.addArc(id, id-line.length + 1, new MyArc(e1));
        graph.addArc(id-line.length + 1, id, new MyArc(e2));
//        graph.addArc(id, id-line.length + 1, new MyArc(line[i] == '~' ? 0:1));
//        graph.addArc(id-line.length + 1, id, new MyArc(prevLine[i+1] == '~' ? 0:1));
      }
      i++;
    }
  }

  private static int changedTerrain(char cur, char other) {
    if (cur == '~' && cur != other){ // cur == '~' && other == 'O'
      return 1;
    }
    return 0;
  }
}


