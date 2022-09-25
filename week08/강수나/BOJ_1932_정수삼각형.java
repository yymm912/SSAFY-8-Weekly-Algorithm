

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932_정수삼각형 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] tree = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				tree[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = n-1; i >= 1; i--) {
			for (int j = 0; j < i; j++) {
				tree[i-1][j] = Math.max( tree[i][j] + tree[i-1][j], tree[i][j+1] + tree[i-1][j]);
			}
		}
		
		System.out.println(tree[0][0]);
	}

}
