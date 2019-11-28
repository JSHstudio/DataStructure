package example.jsh.graph;

import java.util.Iterator;

public interface Graph<V, E> {
	/**
	 * Returns the number of vertices of the graph
	 * 
	 * @return 그래프의 vertex의 개수를 반환합니다.
	 */
	public int numVertices();

	/**
	 * Returns an iteration of all the vertices of the graph.
	 * 
	 * @return vertex 리스트의 iterator를 반환합니다.
	 */
	public Iterator<Vertex<V>> vertices();

	/**
	 * Returns the number of edges of the graph.
	 * 
	 * @return 그래프의 edge의 개수를 반환합니다.
	 */
	public int numEdges();

	/**
	 * Returns an iteration of all the edges of the graph.
	 * 
	 * @return 그래프의 모든 edge들의 iterator을 반환합니다.
	 */
	public Iterator<Edge<E>> edges();

	/**
	 * Returns the edge from vertex u to vertex v,if one exists; otherwise return
	 * null. For an undirected graph, there is no difference between getEdge(u, v)
	 * and getEdge(v, u).
	 * 
	 * @param u 첫 번째 vertex
	 * @param v 두 번째 vertex
	 * @return 두 vertex사이의 edge를 반환한다.
	 */
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v);

	/**
	 * Returns an array containing the two endpoint vertices of edge e. If the graph
	 * is directed, the first vertex is the origin and the second is the
	 * destination.
	 * 
	 * @param e 찾고싶은 Vertex들 사이의 edge
	 * @return 해당 edge의 양 끝 vertex를 배열의 형태로 반환한다.
	 */
	public Vertex<V>[] endVertices(Edge<E> e);

	/**
	 * For edge e incident to vertex v, returns the other vertex of the edge; an
	 * error occurs if e is not incident to v.
	 * 
	 * @param v 기준이 되는 vertex
	 * @param e v에 연결된 edge
	 * @return v에 연결된 e의 반대편에 있는 vertex를 존재합니다.
	 * @throws IllegalArgumentException e가 v의 edge가 아니면 발생
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException;

	/**
	 * Returns the number of outgoing edges from vertex v.
	 * 
	 * @param v 뻗어나가는 edge의 개수를 알고 싶은 vertex, 하지만 지금은 undirected이다.
	 * @return v에 연결된 edge의 개수
	 */
	public int outDegree(Vertex<V> v);

	/**
	 * Returns the number of incoming edges to vertex v. For an undirected graph,
	 * this returns the same value as does outDegree(v).
	 * 
	 * @param v 다가오는 edge의 개수를 알고 싶은 vertex, 하지만 지금은 undirected이다.
	 * @return v에 연결된 edge의 개수
	 */
	public int inDegree(Vertex<V> v);

	/**
	 * outgoingEdges와 incomingEdges대신 사용하는 매서드 입니다. Returns an iteration of all
	 * outgoing or incoming edges from vertex v.
	 * 
	 * @param v 연결된 모든 edge의 iterator을 구하고 싶은 v
	 * @return v에 연결된 모든 edge의 iterator
	 */
	public Iterator<Edge<E>> incidentEdges(Vertex<V> v);

	/**
	 * Creates and returns a new Vertex storing element x.
	 * 
	 * @param value 새로운 vertex에 추가할 값
	 * @return 새로운 vertex객체
	 */
	public Vertex<V> insertVertex(V value);

	/**
	 * * Creates and returns a new Edge from vertex u to vertex v, storing element
	 * x; an error occurs if there already exists an edge from u to v.
	 * 
	 * @param v     시작 vertex
	 * @param u     끝 vertex
	 * @param value 추가할 edge의 값
	 * @return 새로운 edge객체
	 * @throws IllegalArgumentException 해당 edge가 이미 존재할 경우 발생
	 */
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E value) throws IllegalArgumentException;

	// Tip. 제거함수는 구현하지 않습니다.
}
