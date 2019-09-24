package 자료구조;

public class 실습과제1_Main {
	/*
	 * Scoreboard class
	 * 
	 * example of [실습과제1] Array
	 * 
	 * @author JSH
	 * @license GNU General Public License v2.0
	 * @version 1.0
	 * @see README
	 * @see System.out.println
	 * @Link https://github.com/JSHstudio/DataStructure
	 */
	public static void main(String[] args) {
		String[] names = {"Rob", "Mike", "Rose", "Jill", "Jack", "Paul", "Bob"};
	    int[] scores = {750, 1105, 590, 740, 610, 410, 840};
	    int capacity = 5;
	    
		Scoreboard board = new Scoreboard(capacity);
		for(int i=0;i<names.length;i++) {
			GameEntry a = new GameEntry(names[i], scores[i]);
			board.add(a);
		}
		
		try {
			board.remove(3);
			board.remove(1);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}	
		
		String str = "";
		String set = "abc";
		
		PuzzleSolve p = new PuzzleSolve();
		p.puzzleSolve(3, str, set);
	}
}
