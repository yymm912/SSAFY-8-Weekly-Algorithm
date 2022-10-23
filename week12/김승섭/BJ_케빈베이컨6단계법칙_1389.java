package 월말평가;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_케빈베이컨6단계법칙_1389 {
	static int[][] relat;
	static final int INF = 999;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 유저의 수
		int M = Integer.parseInt(st.nextToken());	// 관계 수
		
		relat = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i == j) relat[i][j] = 0;
				else relat[i][j] = INF;
			}
		}
		
		for (int bus = 0; bus < M; bus++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			relat[start][end] = relat[end][start] = 1;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.printf("%4d", relat[i][j]);
			}System.out.println();
		}System.out.println();
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					relat[i][j] = Math.min(relat[i][j], relat[i][k]+relat[k][j]);
				}
			}
		}
		int min = INF;
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			int total = 0;
			for (int j = 1; j <= N; j++) {
				total += relat[i][j];
			}
			if(min > total) {
				min = total;
				idx = i;
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.printf("%4d", relat[i][j]);
			}System.out.println();
		}
		System.out.println(idx);
	}

}
