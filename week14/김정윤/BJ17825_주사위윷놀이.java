package week14;

import java.io.*;
import java.util.*;

public class BJ17825_주사위윷놀이 {
	static int score = 0;
	static int[][] map = {
			{0, 2, 4, 6, 8, 10},
			{10, 13, 16, 19, 25}, // 파랑 10
			{10, 12, 14, 16, 18, 20},
			{20, 22, 24, 25}, // 파랑 20
			{20, 22, 24, 26, 28, 30},
			{30, 28, 27, 26, 25}, // 파랑 30
			{30, 32, 34, 36, 38, 40},
			{25, 30, 35, 40},
			{40, 0}
	};
	static boolean[][] v = {
		{false, false, false, false, false, false},
		{false, false, false, false, false},
		{false, false, false, false, false, false},
		{false, false, false, false},
		{false, false, false, false, false, false},
		{false, false, false, false, false},
		{false, false, false, false, false, false},
		{false, false, false, false},
		{false, false},
	};
	static int[] dice = new int[10];
	static int[][] pieces = new int[4][2];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(0, 0);
		
		System.out.println(score);
	}
	static void perm(int depth, int sum) {
		if (depth >= 10) { // 주사위 다 던진 경우 최댓값 갱신
			score = Math.max(score, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			// 도착 지점에 도착한 말일 경우 Pass
			if (pieces[i][0] == 8 && pieces[i][1] == 1) continue;
			
			int cr = pieces[i][0];
			int cc = pieces[i][1];
			int nr = pieces[i][0];
			int nc = pieces[i][1] + dice[depth];
			
			if (nc == map[nr].length - 1) {
				if (nr < 6 && nr%2 == 0) { // 0 to 1, 2 to 3, 4 to 5
					nr++;
					nc = 0;
				} else if (nr == 6 || nr == 7) { // 6 to 8, 7 to 8
					nr = 8;
					nc = 0;
				} else if (nr%2 == 1) { // 1 to 7, 3 to 7, 5 to 7
					nr = 7;
					nc = 0;
				} else { // 8
					nc = 1; // 도착 지점
				}
			} else if (nc >= map[nr].length) { // 최외각으로 돌아야 할 경우
				nc = nc - map[nr].length+1;
				if (nr%2 == 0) { // 최외각
					nr += 2;
					if (nr >= 8) {
						nr = 8;
						if (nc >= 1) {
							nc = 1;
						}
					}
				} else if (nr == 7) { // 7 to 8
					nr = 8;
					if (nc >= 1) {
						nc = 1;
					}
				} else { // 1, 3, 5 to 7
					nr = 7;
					if (nc >= map[nr].length) { // 도착
						nr = 8;
						nc = 1;
					} else if (nc == map[nr].length-1) { // 40
						nr = 8;
						nc = 0;
					}
				}
			}
			
			if (nr == 8 && nc == 1 || !v[nr][nc]) {
				v[cr][cc] = false;
				v[nr][nc] = true;
				pieces[i][0] = nr;
				pieces[i][1] = nc;
				
				perm(depth+1, sum + map[nr][nc]);
				
				v[cr][cc] = true;
				v[nr][nc] = false;
				pieces[i][0] = cr;
				pieces[i][1] = cc;
			}
		}
	}
}
