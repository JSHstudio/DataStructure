package 진시훈_2018204006_과제1;

public class GameEntry {
	private String name;
	private int score;
	
	public GameEntry(String n, int s) {
		this.name = n;
		this.score = s;
	}
	
	public String getname() {return name;}
	public int getScore() {return score;}
	public String toString() {
		return "("+name+", " + score + ")";
	}
}
