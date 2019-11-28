package example.jsh.graph;

public interface Vertex<V> {
	/**
	 * vertex의 요소를 반환합니다.
	 * 
	 * @return vertex의 element
	 */
	public V getElement();

	/**
	 * vertex의 label을 반환합니다.
	 * 
	 * @return vertex의 label
	 */
	public void setLabel(int label);

	/**
	 * vertex의 label을 반환합니다.
	 * 
	 * @return vertex의 label
	 */
	public int getLabel();
}
