package �ڷᱸ��;

/*
 * class for ����(������� �ѱ� ���� �ƴմϴ�)
 * 
 * PuzzleSolve (int n, Sequence s, Universe u)
 * if(n == 1) than print Sequence + u[0]
 * else if ( n > 1 ) than move u[n] to end of Sequence 
 */
public class PuzzleSolve {
	public void puzzleSolve(int k, String S, String U) {
		for (int i = 0; i < k; i++) {
			String temp = "" + U.charAt(i);
			U = U.replace(temp, "");
			S = S.concat(temp);
//			System.out.println("�� S :" + S + ", U : " + U + ", temp : " + temp);
			/*
			 * char �迭�� �����Ⱚ���� �ʱ�ȭ �Ҷ� ���� ���� �־�� ����? �߰� �ٶ�.
			 */
//			System.out.println("S : " + S + ", temp : " + temp);
			if (k == 1)	System.out.println(S);
			else puzzleSolve(k - 1, S, U);
			U = temp.concat(U);
			S = S.replace(temp, "");
//			System.out.println("�� S : " + S + ", U : " + U + ", temp : " + temp);
		}
	}
}
