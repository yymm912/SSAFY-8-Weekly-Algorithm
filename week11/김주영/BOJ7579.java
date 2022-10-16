package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {

	static int N,M, sum;
	static int []m, c;
	static int[][] memoi;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer  st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		m=new int[N+1];
		c=new int[N+1];

		
		st=new StringTokenizer (br.readLine());
		for (int i=1; i<=N; i++) {
			m[i]=Integer.parseInt(st.nextToken());
		}
		
		st=new StringTokenizer (br.readLine());
		for (int i=1; i<=N; i++) {
			c[i]=Integer.parseInt(st.nextToken());
			sum+=c[i];
		}
		
		memoi=new int[N+1][sum+1];
		
		// y축 : 메모리가 1개부터 ~ N개 있을 때까지
		// x축 : 최소 비용부터 최대 비용까지
		for (int i=1; i<=N; i++) {
			for (int j=0; j<=sum; j++) {
				if (j-c[i]>=0) 
					memoi[i][j]=Math.max(memoi[i][j], memoi[i-1][j-c[i]]+m[i]);
				memoi[i][j]=Math.max(memoi[i][j], memoi[i-1][j]);
			}
		}
		
		for (int i=0; i<=sum; i++) {
			if (memoi[N][i]>=M) {
				System.out.println(i);
				break;
			}
		}
	}
	
//	static void subSum (int tgtIdx, int sum, int cost) {
//		if (tgtIdx==N+1) {
//			if (sum>=M) ans=Math.min(ans, cost);
//			return ;
//		}
//		
//		subSum(tgtIdx+1, sum+m[tgtIdx], cost+c[tgtIdx]);
//		subSum(tgtIdx+1, sum, cost);
//	}

}
