package example.jsh.graph;

import java.util.Iterator;

public interface Graph<V, E> {
	/**
	 * Returns the number of vertices of the graph
	 * 
	 * @return �׷����� vertex�� ������ ��ȯ�մϴ�.
	 */
	public int numVertices();

	/**
	 * Returns an iteration of all the vertices of the graph.
	 * 
	 * @return vertex ����Ʈ�� iterator�� ��ȯ�մϴ�.
	 */
	public Iterator<Vertex<V>> vertices();

	/**
	 * Returns the number of edges of the graph.
	 * 
	 * @return �׷����� edge�� ������ ��ȯ�մϴ�.
	 */
	public int numEdges();

	/**
	 * Returns an iteration of all the edges of the graph.
	 * 
	 * @return �׷����� ��� edge���� iterator�� ��ȯ�մϴ�.
	 */
	public Iterator<Edge<E>> edges();

	/**
	 * Returns the edge from vertex u to vertex v,if one exists; otherwise return
	 * null. For an undirected graph, there is no difference between getEdge(u, v)
	 * and getEdge(v, u).
	 * 
	 * @param u ù ��° vertex
	 * @param v �� ��° vertex
	 * @return �� vertex������ edge�� ��ȯ�Ѵ�.
	 */
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v);

	/**
	 * Returns an array containing the two endpoint vertices of edge e. If the graph
	 * is directed, the first vertex is the origin and the second is the
	 * destination.
	 * 
	 * @param e ã����� Vertex�� ������ edge
	 * @return �ش� edge�� �� �� vertex�� �迭�� ���·� ��ȯ�Ѵ�.
	 */
	public Vertex<V>[] endVertices(Edge<E> e);

	/**
	 * For edge e incident to vertex v, returns the other vertex of the edge; an
	 * error occurs if e is not incident to v.
	 * 
	 * @param v ������ �Ǵ� vertex
	 * @param e v�� ����� edge
	 * @return v�� ����� e�� �ݴ��� �ִ� vertex�� �����մϴ�.
	 * @throws IllegalArgumentException e�� v�� edge�� �ƴϸ� �߻�
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException;

	/**
	 * Returns the number of outgoing edges from vertex v.
	 * 
	 * @param v ������� edge�� ������ �˰� ���� vertex, ������ ������ undirected�̴�.
	 * @return v�� ����� edge�� ����
	 */
	public int outDegree(Vertex<V> v);

	/**
	 * Returns the number of incoming edges to vertex v. For an undirected graph,
	 * this returns the same value as does outDegree(v).
	 * 
	 * @param v �ٰ����� edge�� ������ �˰� ���� vertex, ������ ������ undirected�̴�.
	 * @return v�� ����� edge�� ����
	 */
	public int inDegree(Vertex<V> v);

	/**
	 * outgoingEdges�� incomingEdges��� ����ϴ� �ż��� �Դϴ�. Returns an iteration of all
	 * outgoing or incoming edges from vertex v.
	 * 
	 * @param v ����� ��� edge�� iterator�� ���ϰ� ���� v
	 * @return v�� ����� ��� edge�� iterator
	 */
	public Iterator<Edge<E>> incidentEdges(Vertex<V> v);

	/**
	 * Creates and returns a new Vertex storing element x.
	 * 
	 * @param value ���ο� vertex�� �߰��� ��
	 * @return ���ο� vertex��ü
	 */
	public Vertex<V> insertVertex(V value);

	/**
	 * * Creates and returns a new Edge from vertex u to vertex v, storing element
	 * x; an error occurs if there already exists an edge from u to v.
	 * 
	 * @param v     ���� vertex
	 * @param u     �� vertex
	 * @param value �߰��� edge�� ��
	 * @return ���ο� edge��ü
	 * @throws IllegalArgumentException �ش� edge�� �̹� ������ ��� �߻�
	 */
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E value) throws IllegalArgumentException;

	// Tip. �����Լ��� �������� �ʽ��ϴ�.
}
