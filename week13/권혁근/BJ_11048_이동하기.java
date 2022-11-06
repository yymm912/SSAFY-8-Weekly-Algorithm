
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	

	
	static int H,W;
	static int[][] mat,dp;
	static int[][] dirs= {{1,0},{0,1}};
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn=new StringTokenizer(br.readLine());
		H=Integer.parseInt(stn.nextToken());
		W=Integer.parseInt(stn.nextToken());

		mat=new int[H][W];
		dp=new int[H][W];
		for(int y=0; y<H; y++) {
			stn=new StringTokenizer(br.readLine());
			for(int x=0; x<W; x++) {
				mat[y][x]=Integer.parseInt(stn.nextToken());
			}
		}
		
		dp[0][0]=mat[0][0];
		for(int x=1; x<W; x++) {
			dp[0][x]=dp[0][x-1]+mat[0][x];
		}
		for(int y=1; y<H; y++) {
			dp[y][0]=dp[y-1][0]+mat[y][0];
		}
		answer=0;
		for(int y=1; y<H; y++) {
			for(int x=1; x<W; x++) {
				dp[y][x]=Math.max(dp[y][x-1] , dp[y-1][x])+mat[y][x];
			}
		}
		System.out.println(dp[H-1][W-1]);
		
		
	}
}
