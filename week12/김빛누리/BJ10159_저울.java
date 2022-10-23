import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static boolean[][] memoi;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		memoi = new boolean[N+1][N+1];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			memoi[n1][n2] = true;
			//memoi[n2][n1] = true;
		}
		
		floyd();
		//print();
		count();
	}
	private static void count() {
		for (int i = 1; i < N+1; i++) {
			int cnt = N-1;
			for (int j = 1; j < N+1; j++) {
				if(memoi[i][j]||memoi[j][i]) cnt--;
			}
			System.out.println(cnt);
		}
		
	}
	private static void floyd() {
		for (int k = 0; k < N+1; k++) {
			for (int i = 0; i < N+1; i++) {
				for (int j = 0; j < N+1; j++) {
					if(memoi[i][j]) continue;
					if(memoi[i][k] && memoi[k][j]) {
						memoi[i][j] = true;
					}
				}
			}
		}
		
	}
	static void print() {
		for (int i = 0; i < N+1; i++) {
			for (int j = 0; j < N+1; j++) {
				System.out.print(memoi[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("***********");
	}
}
