package example.jsh.graph;

public interface Edge<E> {
	/**
	 * edge의 요소를 반환합니다.
	 * 
	 * @return edge의 element
	 */
	public E getElement();

	/**
	 * label을 설정합니다. label의 설정 값에 대해서는 그래프 코드를 참고하십시오.
	 * 
	 * @param label 설정할 label 값
	 */
	public void setLabel(int label);

	/**
	 * edge의 label을 반환합니다.
	 * 
	 * @return edge의 label
	 */
	public int getLabel();
}