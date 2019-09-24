package 자료구조;

/*
 * class for 순열(영어몰라서 한글 쓴거 아닙니다)
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
//			System.out.println("앞 S :" + S + ", U : " + U + ", temp : " + temp);
			/*
			 * char 배열을 쓰레기값으로 초기화 할땐 무슨 값을 넣어야 하죠? 추가 바람.
			 */
//			System.out.println("S : " + S + ", temp : " + temp);
			if (k == 1)	System.out.println(S);
			else puzzleSolve(k - 1, S, U);
			U = temp.concat(U);
			S = S.replace(temp, "");
//			System.out.println("뒤 S : " + S + ", U : " + U + ", temp : " + temp);
		}
	}
}
