package study;
import java.util.*;
import java.io.*;

public class Main {

	public static int n, m, res = -1;
	public static int [][] arr;
	public static boolean [] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		arr = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		dfs(1);
		
		System.out.println(res);
	}

	
	static void dfs(int x) {
		visited[x] = true;
		res++;
		for(int i=1; i<=n; i++) {
			if(!visited[i] && arr[x][i] == 1) {
				dfs(i);
			}
		}
	}
}
