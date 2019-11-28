package example.jsh.graph;

public class _Main {

	public static void main(String[] args) {
		// 매트릭스 그래프 선언
		AdjacencyMatrixGraph<String, String> graph = new AdjacencyMatrixGraph<String, String>();

		// 그래프에 데이터 추가
		Vertex<String> v_HNL = graph.insertVertex("HNL");
		Vertex<String> v_SFO = graph.insertVertex("SFO");
		Vertex<String> v_LAX = graph.insertVertex("LAX");
		Vertex<String> v_ORD = graph.insertVertex("ORD");
		Vertex<String> v_DFW = graph.insertVertex("DFW");
		Vertex<String> v_PVD = graph.insertVertex("PVD");
		Vertex<String> v_LGA = graph.insertVertex("LGA");
		Vertex<String> v_MIA = graph.insertVertex("MIA");
		
		Edge<String> e_NHL_LAX = graph.insertEdge(v_HNL, v_LAX, "2555");
		Edge<String> e_LAX_SFO = graph.insertEdge(v_LAX, v_SFO, "337");
		Edge<String> e_LAX_ORD = graph.insertEdge(v_LAX, v_ORD, "1743");
		Edge<String> e_LAX_DFW = graph.insertEdge(v_LAX, v_DFW, "1233");
		Edge<String> e_SFO_ORD = graph.insertEdge(v_SFO, v_ORD, "1843");
		Edge<String> e_DFW_ORD = graph.insertEdge(v_DFW, v_ORD, "802");
		Edge<String> e_DFW_LGA = graph.insertEdge(v_DFW, v_LGA, "1387");
		Edge<String> e_DFW_MIA = graph.insertEdge(v_DFW, v_MIA, "1120");
		Edge<String> e_ORD_PVD = graph.insertEdge(v_ORD, v_PVD, "849");
		Edge<String> e_LGA_PVD = graph.insertEdge(v_LGA, v_PVD, "142");
		Edge<String> e_LGA_MIA = graph.insertEdge(v_LGA, v_MIA, "1099");
		
		// 그래프 구조 출력
//		graph.show();
		
		// 탐색 알고리즘 객체 생성
		Search<String, String> search = new Search<String, String>();

		// DFS 출력
		System.out.print("DFS: ");
		search.DFS(graph);
		
		// BFS 출력
		System.out.print("\nBFS: ");
		search.BFS(graph);
	}
}
