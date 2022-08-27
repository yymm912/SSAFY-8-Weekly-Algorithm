package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1629 {
	static long A, B, C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(stk.nextToken());
		B = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		System.out.println(sol(1,B));
	}
	
	
	// e가 짝수이면 왼쪽 반을 수행했을때 오른쪽반은 똑같은 결과가 나오므로 수행할 필요가 없음
	// e가 홀수이면 오른쪽이 왼쪽보다 1 많으므로 한번 더 곱해줘야함
	static long sol(long s, long e) {
		if(s == e) return A % C;
		long left = sol(s, e/2);
		if(e % 2 == 0) return (left * left) % C;
		else return (((left * left) % C ) * A) % C;
	}
}