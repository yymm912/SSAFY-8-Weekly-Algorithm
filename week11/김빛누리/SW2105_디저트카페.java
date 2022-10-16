package week012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 디저트카페
public class Bswea_2105_bnuri00 {
	static int N, sumMax;
	static int[][] map;
	static ArrayList<Integer> visit = new ArrayList<>();
	
	// 시계방향 대각선 
	// 0, 2 -> 대각선 1
	// 1, 3 -> 대각선 2
	// 우하, 좌하, 좌상, 우상
	static int[] dy = {1, 1, -1, -1};
	static int[] dx = {1, -1, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// init
			sumMax = 0;
			visit.clear();
			
			N = Integer.parseInt(br.readLine());
			
			// 입력받기
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 대각선 길이 합의 최대
			int m = N-1;
			
			for (int i = 0; i < N; i++) {
				if(sumMax == m) break;
				for (int j = 1; j < N-1; j++) {	// 양 끝 열은 검사할 필요 X
					if(sumMax == m) break;
					
					int cross1 = Math.min(N-i-1, N-j-1);	// 현재 위치에서 첫번째 대각선 최댓값(아래 혹은 오른쪽 남은 칸)
					int cross2 = Math.min(N-i-1, j);	// 현재 위치에서 첫번째 대각선 최댓값(아래 혹은 왼쪽 남은 칸)
					
					int nc1 = cross1;
					int nc2 = cross2;
					
					while(true) {
						if(go(i, j, nc1, nc2)) {
							sumMax = Math.max(sumMax, nc1+nc2);
							break;
						}
						
						nc2--;
						if(nc2==0 || nc1+nc2 <= sumMax) {
							nc2 = cross2;
							nc1--;
						}
						
						if(nc1 == 0) break;
						
//						if(cross1 == 1 || cross2 == 1) {
//							if(cross1==cross2) break;
//							else if(cross1==1) cross2--;
//							else if(cross2==1) cross1--;
//						}
//						
					}
					
				}
			}
			sb.append("#").append(t).append(" ");
			if(sumMax == 0) sb.append(-1).append("\n");
			else sb.append(sumMax*2).append("\n");
			//System.out.println("#"+t+ " " + sumMax*2);
		}
		System.out.println(sb.toString());
	}

	private static boolean go(int y, int x, int cross1, int cross2) {
		visit.clear();
		visit.add(map[y][x]);
		
		for (int d = 0; d < 4; d++) {
			int len = 0;
			
			if(d%2 == 0) len = cross1;
			else len = cross2;
			
			if(d==3) len--;
			
			for (int k = 0; k < len; k++) {
				y += dy[d];
				x += dx[d];
				
				if(x < 0 || x >= N || y < 0 || y >= N) return false;
				if(!visit.isEmpty() && visit.contains(map[y][x])) return false;
				
				visit.add(map[y][x]);
			}
		}
		return true;
	}

}
