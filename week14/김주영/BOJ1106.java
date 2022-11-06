package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 처음에 dp를 2차원 배열로 두고 해결하려고 했음 --> 실패
 * 
 * dp[i] : 고객 i명을 늘리는데 필요한 비용
 * (비용, 늘어나는 고객)와 같은 홍보 쌍을 여러 개 사용할 수 있다는 점에서 
 * 고객 수를 늘어나는 고객으로 나누고 그 몫과 비용을 곱해야 하나? 생각 
 * --> bottom-up 방식으로 차례대로 생각하면 그렇게 계산해주지 않아도 됨
 * 
 * 
 */
public class BOJ1106 {

	static final int INF=987654321;
	static int C,N, ans;
	static int [] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		C=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());

		dp=new int[C+101];
		Arrays.fill(dp, INF);
		dp[0]=0;
		
		for (int i=1; i<=N; i++) {
			st=new StringTokenizer (br.readLine());
			int cost=Integer.parseInt(st.nextToken());
			int customer=Integer.parseInt(st.nextToken());
			
			for (int j=customer; j<C+101; j++) {
				dp[j]=Math.min(dp[j], dp[j-customer]+cost);
			}
		}
		
		ans=Integer.MAX_VALUE;
		for (int j=C; j<C+101; j++) {
			ans=Math.min(ans, dp[j]);
		}
		
		System.out.println(ans);

	}

}
