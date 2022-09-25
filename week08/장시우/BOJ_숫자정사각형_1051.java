package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_숫자정사각형_1051 {
	
	static int N, M, min;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		min = Math.min(N, M);
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String t = br.readLine();
			for (int j = 0; j < t.length(); j++) {
				map[i][j] = Integer.parseInt(Character.toString(t.charAt(j)));
			}
		}
		
		while (min > 0) {
			boolean hasAnswer = false;
			for (int i = 0; i <= N - min; i++) {
				for (int j = 0; j <= M - min; j++) {
					if (map[i][j] == map[i + min - 1][j] && map[i + min - 1][j] == map[i + min - 1][j + min - 1] && map[i + min - 1][j + min - 1] == map[i][j + min - 1]) {
						hasAnswer = true;
						break;
					}
				}
				
				if (hasAnswer) break;
			}
			
			if (hasAnswer) break;
			min--;
		}
		
		System.out.println((int)Math.pow(min, 2));
	}
}
