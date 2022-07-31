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

public class backjoon_7562 {
	
	static int TC, N, ans;
	static int[][] map;
	static boolean[][] checked;
	static Pair cur, target;
	static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1};
	static int[] dy = { 2, 1, -1, -2, -2, -1, 1, 2};
	static Queue<Pair> q;
	
	public static void main(String[] args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		
		// test_case
		for (int i = 0; i < TC; i++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			checked = new boolean[N][N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cur = new Pair(x, y);
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			target = new Pair(x, y);
			
			q = new LinkedList<>();
			ans = 0;
			bfs();
		}
	}
	
	public static void bfs() {
		q.add(cur);
		checked[cur.x][cur.y] = true;
		
		while (!q.isEmpty()) {
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				Pair curP = q.poll();
				int j;
				for (j = 0; j < 8; j++) {
					int nextX = curP.x + dx[j];
					int nextY = curP.y + dy[j];
					
					if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !checked[nextX][nextY]) {
						checked[nextX][nextY] = true;
						q.add(new Pair(nextX, nextY));
					}
					
					if (checked[target.x][target.y] == true) {
						if (cur.x == target.x && cur.y == target.y) {
							System.out.println(0);
							q.clear();
							break;
						}else {
							System.out.println(ans + 1);
							q.clear();
							break;
						}
					} 
				}
				if (j < 8) {
					break;
				}
			}
			
			ans++;
		}
	}	
}