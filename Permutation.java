package 진시훈_2018204006_과제1;

/*
 * class for Permutation
 * 
 * PuzzleSolve (int n, Sequence s, Universe u)
 * if(n == 1) than print Sequence + u[0]
 * else if ( n > 1 ) than move u[n] to end of Sequence 
 */
public class Permutation {
	public void permutation(int k, String S, String U) {
		for (int i = 0; i < k; i++) {
			String temp = "" + U.charAt(i);
			U = U.replace(temp, "");
			S = S.concat(temp);
			if (k == 1 && U.length() == 0)	System.out.println(S);
			else puzzleSolve(k - 1, S, U);
			U = temp.concat(U);
			S = S.replace(temp, "");
		}
	}
}
