import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// dfs
public class SWEA1227_미로2_DFS {

	static int N = 100, res;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			
			map = new int[N][N];
			visit = new boolean[N][N];
			int[] node = new int[2];
			
			for (int i = 0; i < N; i++) {
				char[] ch = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = ch[j] - '0';
					if(map[i][j] == 2) {
						node[0] = i;
						node[1] = j;
					}
				}
			} // 입력 끝
			
			res = 0;
			visit[node[0]][node[1]] = true;
			dfs(node[0], node[1]);
			System.out.println("#" + t + " " + res);
		}
	}

	static void dfs(int x, int y) {
		if(res == 1) return;
		
		if(map[x][y] == 3) {
			res = 1;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= N || map[tx][ty] == 1 || visit[tx][ty]) continue;
			
			visit[tx][ty] = true;
			dfs(tx, ty);
		}
	}
	
}
