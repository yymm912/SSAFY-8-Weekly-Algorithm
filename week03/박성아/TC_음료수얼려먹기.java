package study;
import java.util.*;
import java.io.*;

public class Main {

	public static int n, m, res = 0;
	public static int [][] arr = new int[1001][1001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for(int j=0; j<m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(dfs(i, j)) res++;
			}
		}
		
		System.out.println(res);
	}

	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
    
	static boolean dfs(int x, int y) {
		if(x <= -1 || x >= n || y <= -1 || y >= m) return false;

		if(arr[x][y] == 0) {
			arr[x][y] = 1;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				dfs(nx, ny);
			}
			return true;
		}
		
		return false;
	}
}
