package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11811_데스스타 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/**
		 * N개의 원소로 이루어진 수열 a
		 * 각 수열 원소는 aij : ai와 aj가 and 비트 연산을 수행한 결과값이다.
		 * ex) 1 & 3 => 0001 & 0011 = 0001(1)
		 * 
		 * 문제는 각 행과 열의 요소를 AND 연산한 결과
		 * => 각 행을 OR 연산하면 된다.
		 */
		int N = Integer.parseInt(br.readLine());
		
		
		int[][] map = new int[N][N]; // 수열이 만족하는 식 2차원 배열
		int[] ar = new int[N]; // 정답의 수열
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i == j) continue;// 대각선은 없는 내용이니 패스
				ar[i] = ar[i] | map[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(ar[i] + " ");
		}
		
		
		
	}

}
