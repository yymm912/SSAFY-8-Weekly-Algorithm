import java.io.*;
import java.util.*;

// 숫자는 지형의 높이
// 최대한 긴 등산로
// 시작은 가장 높은 봉우리 -> 입력과동시에 저장
// 높 -> 낮 (4방)
// 같은 곳, 대각선 X
// 딱 한곳 K 만큼 지형을 깎을 수 있음

public class SW1949_등산로조성 {
	
	static int T, N, K, max, answer;
	static List<P> start;
	static int[][] map;
	static boolean[][] v;
	
	static class P{
		int y;
		int x;
		public P(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			max = 0;
			start = new ArrayList<>();
			map = new int[N][N];
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					if (map[y][x] >= max) {
						if (map[y][x] > max) {
							max = map[y][x];
							start.clear();
						}		
						// 시작점 추가					
						start.add(new P(y, x));						
					}
				}
			}
			
			answer = 0;
			for (P s : start) {
				v = new boolean[N][N];
				v[s.y][s.x] = true;
				dfs(s.y, s.x, 1, false);
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {-1, 1, 0, 0};
	static void dfs(int y, int x, int d, boolean useBroke) {	
		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N || v[nextY][nextX]) continue;
			
			// 부수지 않고 그냥 탐색
			if (map[nextY][nextX] < map[y][x]) {

				v[nextY][nextX] = true;
				dfs(nextY, nextX, d + 1, useBroke);				
				v[nextY][nextX] = false;

			}
			// 안썻을 때 부수고 탐색
			else if (!useBroke) {		
				for (int b = 1; b <= K; b++) {
					if (map[nextY][nextX] - b < map[y][x]) {
						
						map[nextY][nextX] -= b;
						v[nextY][nextX] = true;
						
						dfs(nextY, nextX, d + 1, true);
						
						v[nextY][nextX] = false;
						map[nextY][nextX] += b;
						
						break;
					}
				}
			}
		}
		answer = Math.max(answer, d);
	}
}