import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// bfs
public class SWEA1227_미로2_BFS {

	static int N = 100, res;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			
			map = new int[N][N];
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
			bfs(node[0], node[1]);
			System.out.println("#" + t + " " + res);
		}
	}

	static void bfs(int x, int y) {
		Queue<Node> qu = new ArrayDeque<>();
		visit = new boolean[N][N];
		qu.offer(new Node(x, y));
		visit[x][y] = true;
		
		while(!qu.isEmpty()) {
			Node n = qu.poll();
			
			if(map[n.x][n.y] == 3) {
				res = 1;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int tx = n.x + dx[d];
				int ty = n.y + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= N || map[tx][ty] == 1 || visit[tx][ty]) continue;
				
				visit[tx][ty] = true;
				qu.offer(new Node(tx, ty));
			}
		}
	}
	
}
