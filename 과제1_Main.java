package 진시훈_2018204006_과제1;

public class 과제1_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] names = { "Rob", "Mike", "Rose", "Jill", "Jack", "Paul", "Bob" };
		String[] removes = {"Mike", "Paul", "Bob"};
		int[] scores = { 750, 1105, 590, 740, 610, 410, 840 };
		int capacity = 5;

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
		

		String str = "";
		String set = "abc";
		PuzzleSolve p = new PuzzleSolve();
		p.puzzleSolve(3, str, set);
	}

}
