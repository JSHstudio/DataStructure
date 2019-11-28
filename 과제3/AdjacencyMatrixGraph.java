package example.jsh.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjacencyMatrixGraph<V, E> implements Graph<V, E> {
	private class InnerVertex<V> implements Vertex<V> {
		private V element;
		private int label;

		public InnerVertex(V element) {
			this.element = element;
		}

		@Override
		public V getElement() {
			return element;
		}

		@Override
		public void setLabel(int label) {
			// �μ��� ����
			if (label < 0 || label > 5)
				throw new IllegalArgumentException();

			// ��¿��� ����
			if (label == Search.VISITED)
				System.out.print(element + ", ");

			this.label = label;
		}

		@Override
		public int getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return (String) element;
		}
	}

	private class InnerEdge<E> implements Edge<E> {
		private E element;
		private int label;

		public InnerEdge(E element) {
			this.element = element;
		}

		@Override
		public E getElement() {
			return element;
		}

		@Override
		public void setLabel(int label) {
			// �μ��� ����
			if (label < 0 || label > 5)
				throw new IllegalArgumentException();

			// ��¿��� ����
			if (label == Search.DISCOVERY)
				System.out.print(element + ", ");

			this.label = label;
		}

		@Override
		public int getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return (String) element;
		}
	}

	private static final String ERROR_MSG_NOT_EXIT_INDEX = "u or v don't exist.";
	private static final String ERROR_MSG_ALREADY_EXIST_EDGE = "edge is already exist.";

	private ArrayList<ArrayList<Edge<E>>> matrix;
	private ArrayList<Vertex<V>> vertices;
	private int numOfEdges;

	public AdjacencyMatrixGraph() {
		matrix = new ArrayList<ArrayList<Edge<E>>>();
		vertices = new ArrayList<Vertex<V>>();
	}

	/**
	 * ��Ʈ������ ������ �� ����մϴ�.
	 * 
	 * @return ������ ��Ʈ������ ��ȯ
	 */
	protected ArrayList<ArrayList<Edge<E>>> getMatrix() {
		return matrix;
	}

	@Override
	public int numVertices() {
		return vertices.size();
	}

	@Override
	public Iterator<Vertex<V>> vertices() {
		return vertices.iterator();
	}

	@Override
	public int numEdges() {
		return numOfEdges;
	}

	@Override
	public Iterator<Edge<E>> edges() {
		return makeEdgeList().iterator();
	}

	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
		// v�� u�� index Ž��
		int uIndex = searchIndex(u);
		int vIndex = searchIndex(v);

		// �ش� ���� ��ȯ
		return matrix.get(vIndex).get(uIndex);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endVertices(Edge<E> e) {
		// matrix���� e�� row, column �ε��� Ž��
		int numOfVertices = numVertices();
		int count = 0;
		while (count <= numOfVertices * numOfVertices) {
			// ��Ʈ�������� ���� ����
			Edge<E> edge = matrix.get(count / numOfVertices).get(count % numOfVertices);

			// count ����
			count++;

			// null������ ���� ó��
			if (edge == null)
				continue;

			// �ش� ������ ã���� �ݺ��� ����
			else if (edge.equals(e))
				break;
		}

		// �ε����� Vertex ������, �迭�� ��� ��ȯ....�� �����ϴ�!
		Vertex<V> v1 = vertices.get((count - 1) / numOfVertices);
		Vertex<V> v2 = vertices.get((count - 1) % numOfVertices);

		return (Vertex<V>[]) new Vertex[] { v1, v2 };
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		// e�� �� �� vertex�� ã�´�.
		Vertex<V>[] vertexArray = endVertices(e);

		// �� �� vertex�� ���Ͽ� v�� ������ �ݴ��� Vertex�� ��ȯ�Ѵ�.
		if (vertexArray[0].getElement().equals(v.getElement()))
			return vertexArray[1];
		else if (vertexArray[1].getElement().equals(v.getElement()))
			return vertexArray[0];
		else
			throw new IllegalArgumentException();
	}

	@Override
	public int outDegree(Vertex<V> v) {
		// v�� �ε����� ���´�.
		int vIndex = searchIndex(v);

		// �� ���� �ִ� Edge�� ������ ����.
		int count = 0;
		for (int i = 0; i < matrix.get(vIndex).size(); i++) {
			if (!matrix.get(vIndex).get(i).equals(null))
				count++;
		}
		return count;
	}

	@Override
	public int inDegree(Vertex<V> v) {
		return outDegree(v);
	}

	@Override
	public Iterator<Edge<E>> incidentEdges(Vertex<V> v) {
		// v�� �ε��� Ž��
		int vIndex = searchIndex(v);

		// ���ο� Edge�� ���� list�� �����.
		List<Edge<E>> list = new LinkedList<Edge<E>>();

		// matrix���� v�� �����ִ� ��� edge�� list�� �߰��Ѵ�.
		for (int i = 0; i < numVertices(); i++) {
			Edge<E> edge = matrix.get(vIndex).get(i);
			if (edge != null)
				list.add(edge);
		}

		// ����Ʈ�� iterator��ȯ
		return list.iterator();
	}

	@Override
	public Vertex<V> insertVertex(V value) {
		// vertices �迭�� �ϳ� �߰�
		InnerVertex<V> newVertex = new InnerVertex<V>(value);
		vertices.add(newVertex);

		// matrix�� ���� row�� null �߰�
		for (int i = 0; i < matrix.size(); i++)
			matrix.get(i).add(null);

		// matrix�� ���ο� row �߰�
		ArrayList<Edge<E>> temp = new ArrayList<Edge<E>>();
		matrix.add(temp);

		// �߰��� row�� matrix�� ���̸�ŭ null �߰�
		for (int i = 0; i < matrix.size(); i++) {
			temp.add(null);
		}

		return newVertex;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E value) throws IllegalArgumentException {
		// v�� u�� indexã��
		int vIndex = searchIndex(v);
		int uIndex = searchIndex(u);

		// ������ ���� �˻�
		if (matrix.get(vIndex).get(uIndex) == null)
			throw new IllegalArgumentException(ERROR_MSG_ALREADY_EXIST_EDGE);

		// v,u �� u,v �� ��ҿ� Edge �߰�
		InnerEdge<E> newEdge = new InnerEdge<E>(value);
		matrix.get(vIndex).set(uIndex, newEdge);
		matrix.get(uIndex).set(vIndex, newEdge);

		// numOfEdges ����
		numOfEdges++;

		return newEdge;
	}

	/**
	 * vertices ����Ʈ���� v�� �ε����� ã���ϴ�. ���� ��� ������ �߻��մϴ�.
	 * 
	 * @param �ε����� ã�� Vertex
	 * @return v�� �ε��� Ȥ�� -1
	 * @throws IllegalArgumentException �ش� vertex�� matrix�� �������� ���� ��� �߻�
	 */
	private int searchIndex(Vertex<V> v) throws IllegalArgumentException {
		int i;
		for (i = 0; i < vertices.size(); i++)
			if (vertices.get(i).equals(v))
				return i;
		throw new IllegalArgumentException(ERROR_MSG_NOT_EXIT_INDEX);
	}

	/**
	 * ��Ʈ������ �ִ� Edge List�� �����.
	 * 
	 * @return List<Edge<E>>
	 */
	private List<Edge<E>> makeEdgeList() {
		// ��ȯ�� ����Ʈ ����
		List<Edge<E>> edges = new ArrayList<Edge<E>>();

		// ��Ʈ���� ���ο��� ������ ���� Ž��(���밢��ĸ� ã���� ��)
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = i; j < vertices.size(); j++) {
				if (matrix.get(i).get(j) != null) {
					edges.add(matrix.get(i).get(j));
				}
			}
		}

		return edges;
	}

	/**
	 * graph�� ���θ� 2���� ǥ�� ����Ѵ�.
	 */
	public void show() {
		System.out.print("   ");
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print(String.format("%6s", (String) vertices.get(i).getElement()));
		}
		System.out.println("");

		for (int i = 0; i < matrix.size(); i++) {
			System.out.print(vertices.get(i).getElement() + " ");
			for (int j = 0; j < matrix.get(i).size(); j++) {
				String temp;
				try {
					temp = (String) matrix.get(i).get(j).getElement();
				} catch (NullPointerException e) {
					temp = "";
				}
				System.out.print(String.format("%6s", temp));
			}
			System.out.println("\n");
		}
	}
}

class Search<V, E> {
	// label �ڵ� �ѹ�
	public static final int UNEXPLORED = 0;
	public static final int DISCOVERY = 1;
	public static final int VISITED = 2;
	public static final int CROSS = 3;
	public static final int BACK = 4;

	public void DFS(AdjacencyMatrixGraph<V, E> g) {
		// ��� Edge�� label�� false�� �����Ѵ�.
		int size = g.numVertices();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Edge<E> temp = g.getMatrix().get(i).get(j);
				if (temp != null)
					g.getMatrix().get(i).get(j).setLabel(UNEXPLORED);
			}
		}

		// ��� Vertex�� label�� false�� �����Ѵ�.
		Iterator<Vertex<V>> iterator = g.vertices();
		while (iterator.hasNext()) {
			Vertex<V> v = iterator.next();
			v.setLabel(UNEXPLORED);
		}

		// ��� Vertex�� ���Ͽ� Unexplored -> BFS(G, v)���ȣ��
		iterator = g.vertices();
		while (iterator.hasNext()) {
			Vertex<V> v = iterator.next();
			if (v.getLabel() == UNEXPLORED)
				DFS(g, v);
		}
	}

	public void DFS(AdjacencyMatrixGraph<V, E> g, Vertex<V> v) {
		// v�� label�� VISITED ����
		v.setLabel(VISITED);

		// ��� v�� Edge�� ���ؼ� �����Ѵ�.
		Iterator<Edge<E>> iterator = g.incidentEdges(v);
		while (iterator.hasNext()) {
			Edge<E> e = iterator.next();
			// e�� label�� UNEXPLORED���
			if (e.getLabel() == UNEXPLORED) {
				// �ݴ��� Vertex�� ã�´�.
				Vertex<V> w = g.opposite(v, e);

				// w�� label�� �����Ѵ�.
				if (w.getLabel() == UNEXPLORED) {
					// e�� label�� �ٳణ ǥ�ø��Ѵ�.
					e.setLabel(DISCOVERY);

					// ���ȣ���� �Ѵ�.
					DFS(g, w);
				} else {
					// �ٽ� ���ư� ������ �����.
					e.setLabel(BACK);
				}
			}
		}
	}

	public void BFS(AdjacencyMatrixGraph<V, E> g) {
		int size = g.numVertices();
		// ��� Edge�� label�� false�� �����Ѵ�.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Edge<E> temp = g.getMatrix().get(i).get(j);
				if (temp != null)
					g.getMatrix().get(i).get(j).setLabel(UNEXPLORED);
			}
		}

		// ��� Vertex�� label�� false�� �����Ѵ�.
		Iterator<Vertex<V>> iterator = g.vertices();
		while (iterator.hasNext()) {
			Vertex<V> v = iterator.next();
			v.setLabel(UNEXPLORED);
		}

		// ��� Vertex�� ���Ͽ� Unexplored -> BFS(G, v)���ȣ��
		iterator = g.vertices();
		while (iterator.hasNext()) {
			Vertex<V> v = iterator.next();
			if (v.getLabel() == UNEXPLORED)
				BFS(g, v);
		}
	}

	public void BFS(AdjacencyMatrixGraph<V, E> g, Vertex<V> v) {
		// ���ο� �迭�� �����
		Queue<Vertex<V>> queue = new LinkedList<Vertex<V>>();

		// �μ��� ���� Vertex�� �ִ´�.
		queue.offer(v);

		// v�� �ٳ�� ǥ�ø� �صд�.
		v.setLabel(VISITED);

		// queue�� ������ ���� ������ ��� ����
		while (!queue.isEmpty()) {
			// queue���� �ϳ� ������ ����.
			Vertex<V> vertex = queue.poll();

			// ����� Vertex �� ��� Edge�� ��ȸ�Ѵ�.
			Iterator<Edge<E>> iterator = g.incidentEdges(vertex);
			while (iterator.hasNext()) {
				// Edge�� label�� �����Ѵ�.
				Edge<E> edge = iterator.next();

				// ��ȸ���� ���� Edge��� �ٳణ ǥ�ø� �Ѵ�.
				if (edge.getLabel() == UNEXPLORED) {
					// �ش� Edge�� �ݴ��� Vertex�� �����´�.
					Vertex<V> w = g.opposite(vertex, edge);

					// edge�� �ݴ��� �ٳణ ������ ������
					if (w.getLabel() == UNEXPLORED) {
						// edge�� �߰��� ǥ���Ѵ�.
						edge.setLabel(DISCOVERY);

						// �ݴ���Vertex�� �湮���� �ۼ��Ѵ�.
						w.setLabel(VISITED);

						// queue�� w�� �߰��Ѵ�.
						queue.offer(w);
					} else {
						edge.setLabel(CROSS);
					}
				}
			}
		}
	}
}
