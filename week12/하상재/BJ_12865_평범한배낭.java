package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭 {
	
	static int N,K;
	static int[][] stuffs;
	static int[][] DP;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		stuffs = new int[N+1][2];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			stuffs[i][0] = Integer.parseInt(st.nextToken());
			stuffs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		DP = new int[N+1][K+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				
				if(stuffs[i][0]>j) {
					DP[i][j] = DP[i-1][j]; 
				}
				else {
					DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-stuffs[i][0]] + stuffs[i][1]);
				}
				
			}
		}
		
		
		System.out.println(DP[N][K]);

	}
	
}