package JSH_2018204006_과제1;

public class 괴제1_Main {
	/*
	 * example of [실습과제1]
	 * 
	 * @author JSH
	 * @license GNU General Public License v2.0
	 * @version 1.0
	 * @see README
	 * @see System.out.println
	 * @Link https://github.com/JSHstudio/DataStructure
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] names = { "Rob", "Mike", "Rose", "Jill", "Jack", "Paul", "Bob" };
		String[] removes = {"Mike", "Paul", "Bob"};
		int[] scores = { 750, 1105, 590, 740, 610, 410, 840 };
		int capacity = 5;
		/*
		* ArrayList Start
		*/
		Scoreboard board = new Scoreboard(capacity);
		for (int i = 0; i < names.length; i++) {
			GameEntry a = new GameEntry(names[i], scores[i]);
			board.add(a);
		}
		try {
			board.remove(3);
			board.remove(1);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		/*
		* DoublyLinkedList Start;
		*/
		
		DoublyLinkedList<GameEntry> doublyLinkedList = new DoublyLinkedList<GameEntry>();
		for (int i = 0; i < names.length; i++) {
			GameEntry b = new GameEntry(names[i], scores[i]);
			doublyLinkedList.add(b);
			System.out.println(doublyLinkedList.toString("Added"));
		}
		for(int i=0;i<removes.length;i++) {
			doublyLinkedList.remove(removes[i]);
			System.out.println(doublyLinkedList.toString("removed"));
		}
		/*
		* Permutation Start;
		*/
		String str = "";
		String set = "abc";
		Permutation p = new Permutation();
		p.permutation(3, str, set);
	}

}
