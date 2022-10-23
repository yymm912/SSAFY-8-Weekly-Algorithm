package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * dp[M] : M원을 만들 수 있는 총 가짓수
 *  
 * 
 */
public class BOJ9084 {

	static final int INF=987654321;
	static int T,N,M;
	static int[] coin, dp;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
	
			coin=new int[N+1];			
			
			st=new StringTokenizer (br.readLine());
			for (int i=1; i<=N; i++) {
				coin[i]=Integer.parseInt(st.nextToken());
			}

			M=Integer.parseInt(br.readLine());
			dp=new int[M+1];
			
			// 0원을 만드는 방법은 아무것도 선택하지 않는 방법 1가지
			dp[0]=1;
			
			// 자신의 현재 동전 가치보다 작은 금액은 만들 수 없다
			for (int i=1; i<=N; i++) {
				for (int j=coin[i]; j<=M; j++) {
					dp[j]+=dp[j-coin[i]];
				}
			}
			System.out.println(dp[M]);
		}		
	}
}
