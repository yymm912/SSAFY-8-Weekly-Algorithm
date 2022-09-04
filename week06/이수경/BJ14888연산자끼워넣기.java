package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14888연산자끼워넣기2 {

	static int N;
	static int A[];
	static int plus_cnt,minus_cnt,mul_cnt,div_cnt;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int sum;
	static int start;
	static int oper[] = new int[4];
	static int current[] = new int[4];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		A = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N-1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(start, 0); // 누적합 , 연산자 개수 
		
		//결과가 최대인 것과 최소인 것
		System.out.println(max);
		System.out.println(min);
		
	}
	
	 static void dfs(int sum, int depth) {
		 if( depth == N-1 ) {
			 max = Math.max(max, sum);
			 min = Math.min(min, sum);
			 return;
		 }
		for (int i = 0; i < 4; i++) {
			// 연산자 개수 1개 이상인 경우
			if (oper[i] > 0) {
	        
				// 해당 연산자를 1 감소 
				oper[i]--;
					
				if( i == 0 ) dfs(sum + A[depth], depth + 1);
				if ( i == 1 ) dfs(sum - A[depth], depth + 1);
				if( i == 2 ) dfs(sum * A[depth], depth + 1);
				if ( i == 3 ) dfs(sum / A[depth], depth + 1);
				
				// 연산자 개수 복구
				oper[i]++;
			}
		}
	}
	
	

}
