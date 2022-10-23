import java.util.*;
import java.io.*;

public class Main {
	static int n, m, INF = Integer.MAX_VALUE;
	static int[][] memoi;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		memoi = new int[n+1][n+1];
		
		init();
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			memoi[n1][n2] = Math.min(memoi[n1][n2], c);
		}
		
		floyd();
		print();
	}
	static void init() {
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				memoi[i][j] = INF;
			}
		}
	}

	private static void floyd() {
		for (int k = 1; k < n+1; k++) {
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < n+1; j++) {
					if(memoi[i][k]==INF || memoi[k][j]==INF) continue;
					if(i==j) {
						memoi[i][j] = 0;
						continue;
					}
					memoi[i][j] = Math.min(memoi[i][j], memoi[i][k]+memoi[k][j]);
				}
			}
		}
		
	}
	static void print() {
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
                if(memoi[i][j] == INF) memoi[i][j] = 0;
				System.out.print(memoi[i][j]+" ");
			}
			System.out.println();
		}
	}
}
