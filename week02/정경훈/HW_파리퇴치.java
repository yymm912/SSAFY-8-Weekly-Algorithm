package swea.파리퇴치_2001;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T,N,M;
	static int[][] area;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			area = new int[N+1][N+1];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					area[i][j] = area[i-1][j] + area[i][j-1] -area[i-1][j-1] + Integer.parseInt(st.nextToken());
				}
			}
			int max = 0;
			for(int i=M;i<=N;i++) {
				for(int j=M;j<=N;j++) {
					max = Math.max(max, area[i][j]-area[i-M][j]-area[i][j-M]+area[i-M][j-M]);
				}
			}
			
			System.out.println("#"+t+" "+max);
		}
		
	}

}
