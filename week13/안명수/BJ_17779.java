package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17779 {
	static int N, ANS = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] areaMAP;
	
	static int[] area = new int[5];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N + 1][N + 1];
		areaMAP = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
	
		for(int x = 1; x <= N; x++) {
			for(int y = 1; y <= N; y++) {
				for(int d1 = 1; d1 <= N; d1++) {
					for(int d2 = 1; d2 <= N; d2++) {
						if(x + d1 + d2 > N) continue;
						if(y - d1 < 1 || y + d2 > N) continue;
						gerrymandering(x,y,d1,d2);
					}
				}
			}
		}
		
		System.out.println(ANS);
	}
	
	static void gerrymandering(int x, int y, int d1, int d2) {
		Arrays.fill(area, 0);
		for(int i = 1; i <= N; i++) Arrays.fill(areaMAP[i], 0);
		
		int tx = x, ty = y;
		while(tx <= x + d1 && ty >= y - d1) {
			if(areaMAP[tx][ty] == 0) {
				areaMAP[tx][ty] = 5;
				area[4] += map[tx][ty];
			}
			tx++; ty--;
		}
		
		tx = x; ty = y;
		while(tx <= x + d2 && ty <= y + d2) {
			if(areaMAP[tx][ty] == 0) {
				areaMAP[tx][ty] = 5;
				area[4] += map[tx][ty];
			}
			tx++; ty++;
		}
		
		tx = x + d1; ty = y - d1;
		while(tx <= x + d1 + d2 && ty <= y - d1 + d2) {
			if(areaMAP[tx][ty] == 0) {
				areaMAP[tx][ty] = 5;
				area[4] += map[tx][ty];
			}
			tx++; ty++;
		}
		
		tx = x + d2; ty = y + d2;
		while(tx <= x + d1 + d2 && ty >= y + d2 - d1) {
			if(areaMAP[tx][ty] == 0) {
				areaMAP[tx][ty] = 5;
				area[4] += map[tx][ty];
			}
			tx++; ty--;
		}
		// 경계선 종료
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(areaMAP[i][j] == 5) {
					if(i == x && j == y || i == x + d1 + d2 && j == y + d2 - d1) continue;
					j++;
					while(j <= N && areaMAP[i][j] == 0) {
						areaMAP[i][j] = 5;
						area[4] += map[i][j];
						j++;
					}
					break;
				}
			}
		}
		
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(areaMAP[i][j] != 0) continue;
				
				if(i < x + d1 && j <= y) {
					areaMAP[i][j] = 1;
					area[0] += map[i][j];
				}else if(i <= x + d2 && y < j) {
					areaMAP[i][j] = 2;
					area[1] += map[i][j];
				}else if(x + d1 <= i && j < y - d1 + d2) {
					areaMAP[i][j] = 3;
					area[2] += map[i][j];
				}else if(x + d2 < i && y - d1 + d2 <= j) {
					areaMAP[i][j] = 4;
					area[3] += map[i][j];
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		
		for(int i = 0; i < 5; i++) {
			min = Math.min(min, area[i]);
			max = Math.max(max, area[i]);
		}
		ANS = Math.min(ANS, max - min);
	}
}
