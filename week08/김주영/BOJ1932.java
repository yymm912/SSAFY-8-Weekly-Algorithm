package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1932 {

	static int N, ans;
	static int[][] triangle; 
	static int[][] cache;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		triangle=new int[N+1][N+1];
		cache=new int[N+1][N+1];
		
		for (int i=0; i<=N; i++)
			Arrays.fill(cache[i], -1);
		
		for (int i=1; i<=N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=1; j<=i; j++) {
				triangle[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		//#1. To-down
//		cache[1][1]=triangle[1][1];
//		
//		for (int i=1; i<=N; i++) {
//			int ret=dp(N, i);
//			ans=Math.max(ans, ret);
//		}
//		
//		System.out.println(ans);
		
		
		//#2. Bottom-up
		System.out.println(dp(1,1));
	}
	
	// top-down 방식 ==> 288ms
	private static int memoi (int y, int x) {
		
		if (cache[y][x]!=-1) return cache[y][x];

		if (x!=1 && x!=y) {
			cache[y][x]=Math.max(memoi(y-1, x-1), memoi(y-1,x))+triangle[y][x];
		} else if (x==1) {
			cache[y][x]=memoi(y-1,x)+triangle[y][x];
		} else if (x==y) {
			cache[y][x]=memoi(y-1, x-1)+triangle[y][x];
		}
		
		return cache[y][x];
	}
	
	
	//bottom-up ==> 308ms
	private static int dp (int y, int x) {

		if (y==N) 
			return cache[y][x]=triangle[y][x];
		
		if (cache[y][x]!=-1) return cache[y][x];	
		return cache[y][x]=Math.max(dp(y+1,x), dp(y+1, x+1))+triangle[y][x];

	}
	
	
	private static void print () {
		
		System.out.println("==========================================");
		for (int i=0; i<=N; i++) {
			for (int j=0; j<=N; j++) {
				System.out.printf("%3d", cache[i][j]);
			}
			System.out.println();
		}
	}

}
