package 자료구조;

import java.util.ArrayList;

public class Scoreboard {
	private int numEntries = 0;
	private int capacity;
	private ArrayList<GameEntry> board;

	public Scoreboard(int capacity) {
		board = new ArrayList<GameEntry>(capacity);
		this.capacity = capacity;
	}

	public void add(GameEntry e) {
		int newScore = e.getScore();
		if (numEntries == 0) {
			board.add(e);
			numEntries++;
		} else if (numEntries < capacity || newScore > board.get(numEntries - 1).getScore()) {
			if (numEntries < capacity)
				numEntries++;
			int j = numEntries - 1;
			board.add(e);
			while (j > 0 && board.get(j - 1).getScore() < newScore) {
				board.set(j, board.get(j - 1));
				j--;
			}
			board.set(j, e);
		}
		print("Added");
	}

	public GameEntry remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= numEntries)
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		GameEntry temp = board.get(index);
		for (int j = index; j < numEntries - 1; j++) {
			board.set(j, board.get(j + 1));
		}
		board.remove(numEntries - 1);
		numEntries--;
		print("removed");
		return temp;
	}

	private void print(String str) {
		System.out.print("[Scoreboard] " + str + ": [");
		for (int i = 0; i < numEntries; i++) {
			System.out.print("(" + board.get(i).getname() + ", " + board.get(i).getScore() + ")");
			if (i != numEntries - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
}
