package example.jsh.graph;

public interface Vertex<V> {
	/**
	 * vertex�� ��Ҹ� ��ȯ�մϴ�.
	 * 
	 * @return vertex�� element
	 */
	public V getElement();

	/**
	 * vertex�� label�� ��ȯ�մϴ�.
	 * 
	 * @return vertex�� label
	 */
	public void setLabel(int label);

	/**
	 * vertex�� label�� ��ȯ�մϴ�.
	 * 
	 * @return vertex�� label
	 */
	public int getLabel();
}
