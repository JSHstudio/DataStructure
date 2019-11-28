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
			// 인수값 조사
			if (label < 0 || label > 5)
				throw new IllegalArgumentException();

			// 출력여부 결정
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
			// 인수값 조사
			if (label < 0 || label > 5)
				throw new IllegalArgumentException();

			// 출력여부 결정
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
	 * 매트릭스를 얻고싶을 때 사용합니다.
	 * 
	 * @return 현재의 매트릭스를 반환
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
		// v와 u의 index 탐색
		int uIndex = searchIndex(u);
		int vIndex = searchIndex(v);

		// 해당 엣지 반환
		return matrix.get(vIndex).get(uIndex);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endVertices(Edge<E> e) {
		// matrix에서 e의 row, column 인덱스 탐색
		int numOfVertices = numVertices();
		int count = 0;
		while (count <= numOfVertices * numOfVertices) {
			// 매트릭스에서 엣지 추출
			Edge<E> edge = matrix.get(count / numOfVertices).get(count % numOfVertices);

			// count 증가
			count++;

			// null포인터 예외 처리
			if (edge == null)
				continue;

			// 해당 엣지를 찾으면 반복문 종료
			else if (edge.equals(e))
				break;
		}

		// 인덱스로 Vertex 역추적, 배열로 묶어서 반환....을 어케하누!
		Vertex<V> v1 = vertices.get((count - 1) / numOfVertices);
		Vertex<V> v2 = vertices.get((count - 1) % numOfVertices);

		return (Vertex<V>[]) new Vertex[] { v1, v2 };
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		// e의 양 끝 vertex를 찾는다.
		Vertex<V>[] vertexArray = endVertices(e);

		// 양 끝 vertex와 비교하여 v랑 같으면 반대편 Vertex를 반환한다.
		if (vertexArray[0].getElement().equals(v.getElement()))
			return vertexArray[1];
		else if (vertexArray[1].getElement().equals(v.getElement()))
			return vertexArray[0];
		else
			throw new IllegalArgumentException();
	}

	@Override
	public int outDegree(Vertex<V> v) {
		// v의 인덱스를 얻어온다.
		int vIndex = searchIndex(v);

		// 한 행의 있는 Edge의 갯수를 센다.
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
		// v의 인덱스 탐색
		int vIndex = searchIndex(v);

		// 새로운 Edge를 담을 list를 만든다.
		List<Edge<E>> list = new LinkedList<Edge<E>>();

		// matrix에서 v가 갖고있는 모든 edge를 list에 추가한다.
		for (int i = 0; i < numVertices(); i++) {
			Edge<E> edge = matrix.get(vIndex).get(i);
			if (edge != null)
				list.add(edge);
		}

		// 리스트의 iterator반환
		return list.iterator();
	}

	@Override
	public Vertex<V> insertVertex(V value) {
		// vertices 배열에 하나 추가
		InnerVertex<V> newVertex = new InnerVertex<V>(value);
		vertices.add(newVertex);

		// matrix의 기존 row에 null 추가
		for (int i = 0; i < matrix.size(); i++)
			matrix.get(i).add(null);

		// matrix에 새로운 row 추가
		ArrayList<Edge<E>> temp = new ArrayList<Edge<E>>();
		matrix.add(temp);

		// 추가된 row에 matrix의 길이만큼 null 추가
		for (int i = 0; i < matrix.size(); i++) {
			temp.add(null);
		}

		return newVertex;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E value) throws IllegalArgumentException {
		// v와 u의 index찾기
		int vIndex = searchIndex(v);
		int uIndex = searchIndex(u);

		// 기존의 엣지 검사
		if (matrix.get(vIndex).get(uIndex) == null)
			throw new IllegalArgumentException(ERROR_MSG_ALREADY_EXIST_EDGE);

		// v,u 와 u,v 의 요소에 Edge 추가
		InnerEdge<E> newEdge = new InnerEdge<E>(value);
		matrix.get(vIndex).set(uIndex, newEdge);
		matrix.get(uIndex).set(vIndex, newEdge);

		// numOfEdges 증가
		numOfEdges++;

		return newEdge;
	}

	/**
	 * vertices 리스트에서 v의 인덱스를 찾습니다. 없을 경우 에러가 발생합니다.
	 * 
	 * @param 인덱스를 찾을 Vertex
	 * @return v의 인덱스 혹은 -1
	 * @throws IllegalArgumentException 해당 vertex가 matrix에 존재하지 않을 경우 발생
	 */
	private int searchIndex(Vertex<V> v) throws IllegalArgumentException {
		int i;
		for (i = 0; i < vertices.size(); i++)
			if (vertices.get(i).equals(v))
				return i;
		throw new IllegalArgumentException(ERROR_MSG_NOT_EXIT_INDEX);
	}

	/**
	 * 매트릭스에 있는 Edge List를 만든다.
	 * 
	 * @return List<Edge<E>>
	 */
	private List<Edge<E>> makeEdgeList() {
		// 반환할 리스트 선언
		List<Edge<E>> edges = new ArrayList<Edge<E>>();

		// 매트릭스 내부에서 엣지의 종류 탐색(윗대각행렬만 찾으면 됨)
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
	 * graph의 내부를 2차원 표로 출력한다.
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
	// label 코드 넘버
	public static final int UNEXPLORED = 0;
	public static final int DISCOVERY = 1;
	public static final int VISITED = 2;
	public static final int CROSS = 3;
	public static final int BACK = 4;

	public void DFS(AdjacencyMatrixGraph<V, E> g) {
		// 모든 Edge의 label을 false로 설정한다.
		int size = g.numVertices();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Edge<E> temp = g.getMatrix().get(i).get(j);
				if (temp != null)
					g.getMatrix().get(i).get(j).setLabel(UNEXPLORED);
			}
		}

		// 모든 Vertex의 label을 false로 설정한다.
		Iterator<Vertex<V>> iterator = g.vertices();
		while (iterator.hasNext()) {
			Vertex<V> v = iterator.next();
			v.setLabel(UNEXPLORED);
		}

		// 모든 Vertex에 대하여 Unexplored -> BFS(G, v)재귀호출
		iterator = g.vertices();
		while (iterator.hasNext()) {
			Vertex<V> v = iterator.next();
			if (v.getLabel() == UNEXPLORED)
				DFS(g, v);
		}
	}

	public void DFS(AdjacencyMatrixGraph<V, E> g, Vertex<V> v) {
		// v의 label에 VISITED 설정
		v.setLabel(VISITED);

		// 모든 v의 Edge에 대해서 조사한다.
		Iterator<Edge<E>> iterator = g.incidentEdges(v);
		while (iterator.hasNext()) {
			Edge<E> e = iterator.next();
			// e의 label이 UNEXPLORED라면
			if (e.getLabel() == UNEXPLORED) {
				// 반대편 Vertex를 찾는다.
				Vertex<V> w = g.opposite(v, e);

				// w의 label를 조사한다.
				if (w.getLabel() == UNEXPLORED) {
					// e의 label에 다녀간 표시를한다.
					e.setLabel(DISCOVERY);

					// 재귀호출을 한다.
					DFS(g, w);
				} else {
					// 다시 돌아간 흔적을 남긴다.
					e.setLabel(BACK);
				}
			}
		}
	}

	public void BFS(AdjacencyMatrixGraph<V, E> g) {
		int size = g.numVertices();
		// 모든 Edge의 label을 false로 설정한다.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Edge<E> temp = g.getMatrix().get(i).get(j);
				if (temp != null)
					g.getMatrix().get(i).get(j).setLabel(UNEXPLORED);
			}
		}

		// 모든 Vertex의 label을 false로 설정한다.
		Iterator<Vertex<V>> iterator = g.vertices();
		while (iterator.hasNext()) {
			Vertex<V> v = iterator.next();
			v.setLabel(UNEXPLORED);
		}

		// 모든 Vertex에 대하여 Unexplored -> BFS(G, v)재귀호출
		iterator = g.vertices();
		while (iterator.hasNext()) {
			Vertex<V> v = iterator.next();
			if (v.getLabel() == UNEXPLORED)
				BFS(g, v);
		}
	}

	public void BFS(AdjacencyMatrixGraph<V, E> g, Vertex<V> v) {
		// 새로운 배열을 만들고
		Queue<Vertex<V>> queue = new LinkedList<Vertex<V>>();

		// 인수로 들어온 Vertex를 넣는다.
		queue.offer(v);

		// v에 다녀온 표시를 해둔다.
		v.setLabel(VISITED);

		// queue에 뭔가가 남아 있으면 계속 실행
		while (!queue.isEmpty()) {
			// queue에서 하나 끄집어 낸다.
			Vertex<V> vertex = queue.poll();

			// 끄집어낸 Vertex 의 모든 Edge를 순회한다.
			Iterator<Edge<E>> iterator = g.incidentEdges(vertex);
			while (iterator.hasNext()) {
				// Edge의 label을 조사한다.
				Edge<E> edge = iterator.next();

				// 순회하지 않은 Edge라면 다녀간 표시를 한다.
				if (edge.getLabel() == UNEXPLORED) {
					// 해당 Edge의 반대편 Vertex를 가져온다.
					Vertex<V> w = g.opposite(vertex, edge);

					// edge의 반대편에 다녀간 흔적이 없으면
					if (w.getLabel() == UNEXPLORED) {
						// edge에 발견을 표시한다.
						edge.setLabel(DISCOVERY);

						// 반대편Vertex에 방문록을 작성한다.
						w.setLabel(VISITED);

						// queue에 w를 추가한다.
						queue.offer(w);
					} else {
						edge.setLabel(CROSS);
					}
				}
			}
		}
	}
}
