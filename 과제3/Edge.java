package example.jsh.graph;

public interface Edge<E> {
	/**
	 * edge�� ��Ҹ� ��ȯ�մϴ�.
	 * 
	 * @return edge�� element
	 */
	public E getElement();

	/**
	 * label�� �����մϴ�. label�� ���� ���� ���ؼ��� �׷��� �ڵ带 �����Ͻʽÿ�.
	 * 
	 * @param label ������ label ��
	 */
	public void setLabel(int label);

	/**
	 * edge�� label�� ��ȯ�մϴ�.
	 * 
	 * @return edge�� label
	 */
	public int getLabel();
}