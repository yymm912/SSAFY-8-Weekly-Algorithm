import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
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

public class backjoon_7576 {
	
	static int N, M;
	static int[][] map;
	static Queue<Pair> q;
	static int ans;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		ans = 0;
		map = new int[N][M];
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					q.add(new Pair(i, j));
				}
			}
		}
		
		bfs();
		
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					flag = false;
					break;
				}
			}
			
			if (!flag) {
				break;
			}
		}
		
		if (!flag) {
			ans = 0;
		}
		
		System.out.println(ans - 1);
	}
	
	public static void bfs() {		
		while (!q.isEmpty()) {
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				Pair cur = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int nextX = cur.x + dx[j];
					int nextY = cur.y + dy[j];
					
					if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && map[nextX][nextY] == 0) {
						map[nextX][nextY] = 1;
						q.add(new Pair(nextX, nextY));
					}
				}
			}
			ans++;
		}
	}
}