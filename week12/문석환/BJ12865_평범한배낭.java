package BOJ.DP.동전_2293;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 제목 : 동전
// 번호 : 2293
// 난이도 :
/*
 * INFO
 * n개의 종류 동전 , 이 동전을 사용해 그 가치의 합이 k원이 되도록하는 경우의 수 구하기
 * 순서 X -> (100, 50 ,10) 과 (50,100,10)은 같은 경우
 */
public class Main {
	static int N,K;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[K+1];
		dp[0] = 1;

		for(int i=0;i<N;i++) {
			int c = Integer.parseInt(br.readLine());
			for(int j=1;j<K+1;j++) {
				if(j >= c)
					dp[j] += dp[j-c];
			}
		}

		System.out.println(dp[K]);
	}
}
