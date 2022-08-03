import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	static int T;
	static int N, M;
	static int[][] arr;
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					arr[i][j] = arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1] + Integer.parseInt(st.nextToken());
				}
			}

			int maxSum = 0;
			for(int i=M; i<=N; i++) {
				for(int j=M; j<=N; j++) {
					int curSum = arr[i][j] - arr[i-M][j] - arr[i][j-M] + arr[i-M][j-M];
					maxSum = Math.max(maxSum, curSum);
				}
			}
			System.out.println("#" + t + " " + maxSum);
		}
	}
}