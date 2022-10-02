package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_미세먼지안녕_17144 {
	
	static int R, C, T, ans;
	static int[][] map;
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	static List<Node> list = new ArrayList<>();
	
	static int x1 = -1, x2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1 && x1 == -1) {
					x1 = i;
				} else if (map[i][j] == -1 && x1 != -1) {
					x2 = i;
				}
			}
		}
				
		// T초동안 발생한다.
		while(T-- > 0) {
			list.clear();
			// 미세먼지(1)
			// map을 순회하면서 퍼뜨릴 미세먼지의 양, 퍼뜨릴 방향 수, 해당 미세먼지의 좌표를 List에 저장한다.
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] > 0) {
						int cntDir = 0;
						for (int d = 0; d < 4; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							
							if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
								cntDir++;
							}
						}
						list.add(new Node(i, j, map[i][j] / 5, cntDir));
						// 미세먼지를 퍼뜨리고 남는 미세먼지 양을 map[i][j]에 갱신한다.
						map[i][j] -= (map[i][j] / 5) * cntDir;
					}
				}
			}
			
			// list를 순회하면서 미세 먼지를 퍼뜨린다.
			for (int i = 0; i < list.size(); i++) {
				Node n = list.get(i);
				
				int x = n.x;
				int y = n.y;
				int c = n.c;
				
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
						map[nx][ny] += c;
					}
				}
			}
			
			// 공기청정기(2)
			// 공기청정기 윗칸 - 1 (상 -> 하)
			for (int i = x1 - 1; i > 0; i--) {
				map[i][0] = map[i - 1][0];
			}
			// 공기청정기 윗칸 - 2 (우 -> 좌)
			for (int i = 0; i < C - 1; i++) {
				map[0][i] = map[0][i + 1];
			}
			// 공기청정기 윗칸 - 3 (하 -> 상)
			for (int i = 0; i < x1; i++) {
				map[i][C - 1] = map[i + 1][C - 1];
			}
			// 공기청정기 윗칸 - 4 (좌 -> 우)
			for (int i = C - 1; i > 0; i--) {
				if (i == 1) map[x1][i] = 0;
				else map[x1][i] = map[x1][i - 1];
			}
			
			// 공기청정기 아랫칸 - 1 (하 -> 상)
			for (int i = x2 + 1; i < R - 1; i++) {
				map[i][0] = map[i + 1][0];
			}
			// 공기청정기 아랫칸 - 2 (우 -> 좌)
			for (int i = 0; i < C - 1; i++) {
				map[R - 1][i] = map[R - 1][i + 1];
			}
			// 공기청정기 아랫칸 - 3 (상 -> 하)
			for (int i = R - 1; i > x2; i--) {
				map[i][C - 1] = map[i - 1][C - 1];
			}
			// 공기청정기 아랫칸 - 4 (좌 -> 우)
			for (int i = C - 1; i > 0; i--) {
				if (i == 1) map[x2][i] = 0;
				else map[x2][i] = map[x2][i - 1];
			}
		}
		
		// 미세먼지 양을 계산한다.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) ans += map[i][j];
			}
		}
		
		System.out.println(ans);
	}
	
	static class Node {
		int x;
		int y;
		int c;
		int cntDir;
		
		public Node(int x, int y, int c, int cntDir) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.cntDir = cntDir;
		}
	}
}
