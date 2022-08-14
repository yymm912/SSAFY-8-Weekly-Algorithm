package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;
	
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class TC_음료수얼려먹기 {
	
	static int N, M, ans;
	static char[][] map;
	static boolean[][] checked;
	static Queue<Pair> q;
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		checked = new boolean[N][M];
		q = new ArrayDeque<>();
		ans = 0;
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!checked[i][j] && map[i][j] == 0) {
					q.add(new Pair(i, j));
					checked[i][j] = true;
					bfs();
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs() {
		while (!q.isEmpty()) {
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				Pair current = q.poll();
				for (int j = 0; j < 4; j++) {
					int nextX = current.x + dx[j];
					int nextY = current.y + dy[j];
					if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !checked[nextX][nextY] && map[nextX][nextY] == 0) {
						checked[nextX][nextY] = true;
						q.add(new Pair(nextX, nextY));
					}
				}
			}
		}
	}
}
