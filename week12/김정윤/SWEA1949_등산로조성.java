package week12.김정윤;

import java.io.*;
import java.util.*;

public class SWEA1949_등산로조성 {
	static int T, N, K, startPoint, ans;
	static int[][] map;
	static List<Pos> start;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 한 변의 길이
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이
			map = new int[N][N];
			startPoint = 0;
			ans = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > startPoint) { // 최고 높이(시작점 높이) 저장
						startPoint = map[i][j];
					}
				}
			}
			
			start = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == startPoint) // 시작 봉우리 정보 저장
						start.add(new Pos(i, j));
				}
			}
			
			for (int k = 0; k <= K; k++) { // 공사 가능 깊이
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] - k < 0) // 깎았을때 0이 되는 경우 패스
							continue;
						else { // 최대 깊이인 K까지 깎아보기
							map[i][j] -= k;
						}
						for (int l = 0; l < start.size(); l++) {
							bfs(start.get(l).x, start.get(l).y); // 등산로 탐색 시작
						}
						map[i][j] += k; // 원상태로 되돌리기
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	static void bfs(int x, int y) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(x, y));
		
		int sum = 0; // 등산로 길이
		while (!q.isEmpty()) {
			sum++;
			int s = q.size();
			for (int i = 0; i < s; i++) {
				Pos p = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					// 지도 내에 위치하면서 이전 높이가 이후 높이보다 높을 경우
					if (nx >= 0 && ny >= 0 && nx < N && ny < N && (map[p.x][p.y] > map[nx][ny])) {
						q.offer(new Pos(nx, ny));
					}
				}
			}
		}
		if (ans < sum) { // 등산로 최대 길이 저장
			ans = sum;
		}
	}
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		
	}
}
