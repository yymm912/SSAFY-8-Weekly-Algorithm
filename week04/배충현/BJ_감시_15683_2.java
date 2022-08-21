package ssafy.algorithm.study.w04;

import java.io.*;
import java.util.*;

public class BJ_감시_15683_2 {
	
	static StringBuilder sb = new StringBuilder();
	static int N, M, d, result, ans = Integer.MAX_VALUE;
	static int[] dir, dl = new int[0];
	static int[][] input, demo, dt = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static List<int[]> cl;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		input = new int[N][M];
		cl = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(stk.nextToken());
				input[i][j] = tmp;
				if (tmp != 0 && tmp != 6) cl.add(new int[] {i, j, tmp});
			}
		}
		
		dir = new int[cl.size()];	// 5대 --> 1: 5대  [0, 0, 0, 0, 0] ~ [3, 3, 3, 3, 3]
		perm(0);
		
		System.out.println(ans);
		
	}			
	
	static void see() {
		
		for (int i = 0, size = cl.size(); i < size; i++) {
			int[] ct = cl.get(i);	// y, x, type
			d = dir[i];	// 순열로 나온 랜덤 0~3  - 3 3 3 3 3
			
			if (ct[2] == 1) dl = new int[] {d};
			else if (ct[2] == 2) dl = new int[] {d, (d+2)%4};
			else if (ct[2] == 3) dl = new int[] {d, (d+1)%4};
			else if (ct[2] == 4) dl = new int[] {d, (d+1)%4, (d+2)%4};
			else if (ct[2] == 5) dl = new int[] {d, (d+1)%4, (d+2)%4, (d+3)%4};
			
			for (int dd : dl) {
				int ny = ct[0];
				int nx = ct[1];
				while (true) {
					ny += dt[dd][0];
					nx += dt[dd][1];
					
					if (ny<0 || nx<0 || ny>=N || nx>=M) break;
					if (demo[ny][nx] == 6) break;
					
					demo[ny][nx] = '#';
				}
			}
		}
	}
	
	static void perm(int cnt) {
		if (cnt == cl.size()) {
			// 만들어진 수열로 로직 실행
			demo = copy();
			see();
			result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (demo[i][j] == 0) result++;
				}
			}
			ans = Math.min(ans, result);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			dir[cnt] = i;
			perm(cnt+1);
		}
	}
	
	static int[][] copy() {
		int[][] tmp = new int[N][];
		
		for (int i = 0; i < N; i++) {
			tmp[i] = input[i].clone();
		}
		
		return tmp;
	}
	
}
