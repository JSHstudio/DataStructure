package 진시훈_2018204006_과제1;

public class DoublyLinkedList<E> {
	private static class Node<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;

		public Node(E e, Node<E> p, Node<E> n) {
			this.element = e;
			this.prev = p;
			this.next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setElement(E e) {
			this.element = e;
		}

		public void setPrev(Node<E> p) {
			this.prev = p;
		}

		public void setNext(Node<E> n) {
			this.next = n;
		}
	}

	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	public DoublyLinkedList() {
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null, this.header, null);
		this.header.setNext(this.trailer);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty())
			return null;
		return header.getNext().getElement();
	}

	public E last() {
		if (isEmpty())
			return null;
		return trailer.getPrev().getElement();
	}

	public void addFirst(E e) {
		this.addBetween(e, header, header.getNext());
	}

	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer);
	}

	public boolean add(E e) {
		/*
		 * 이 매서드는 E가 GameEntry인것을 가정하고 작동한다.
		 */
		if(e.getClass() != GameEntry.class)
			return false;
		/*
		 * 아직 list에 아무것도 추가 안한 경우
		 * size == 0
		 */
		if(size == 0) {
			addLast(e);
			return true;
		}
		/*
		 * 일반적인 상황
		 */
		Node<E> temp = trailer.getPrev();
		addBetween(e, trailer.getPrev(), trailer);
		
		Node<E> prevNode;
		Node<E> newNode;
		Node<E> nextNode;
		
		int newScore = ((GameEntry) e).getScore();
		while (temp.getPrev() != null && ((GameEntry) temp.getElement()).getScore() < newScore) {
			/*
			 * 현 노드와 이전 노드를 교체한다.
			 * 현 상황
			 * prevNode -> temp -> newNode -> nextNode
			 * 
			 * 바뀔 상황
			 * prevNode -> newNode -> temp -> nextNode
			 */
			prevNode = temp.getPrev();
			newNode = temp.getNext();
			nextNode = temp.getNext().getNext();
			
			
			prevNode.setNext(newNode);
			
			newNode.setPrev(prevNode);
			newNode.setNext(temp);
			
			temp.setPrev(newNode);
			temp.setNext(nextNode);
			
			nextNode.setPrev(temp);
			/*
			 * temp가 이전 노드를 가리킨다
			 */
			temp = prevNode;
		}
		return true;
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		return remove(header.getNext());
	}

	public E removeLast() {
		if (isEmpty())
			return null;
		return remove(trailer.getPrev());
	}
	
	public E remove(String str) {
		/*
		 * 이 매서드는 E가 GameEntry인것을 가정하고 작동한다.
		 */
		Node<GameEntry> temp = (Node<GameEntry>) header.getNext();
		Node<E> current = header.getNext();
		while(temp.getElement().getname() != str && temp != trailer) {
			temp = temp.getNext();
			current = current.getNext();
		}
		if(temp == trailer)
			return null;
		remove(current);
		return current.getElement();
	}

	public String toString(String str) {
		/*
		 * 이 매서드는 E가 GameEntry인것을 가정하고 작동한다.
		 */
		String result = "";
		result = result.concat("[DoyblyLinkedList] " + str + ": [");
		Node<GameEntry> node = (Node<GameEntry>) header.getNext();
		while(node != trailer) {
			result = result.concat("(" + node.getElement().getname()+", "+node.getElement().getScore()+")");
			if(node != trailer.getPrev())
				result = result.concat(", ");
			node = node.getNext();
		}
		result = result.concat("]");
		return result;
	}
	
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<E>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}

	private E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement();
	}
}
