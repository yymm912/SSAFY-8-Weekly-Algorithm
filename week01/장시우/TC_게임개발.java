import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;
	
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class TC_게임개발 {
	
	static Pair location;
	static int N, M, direction, ans, maxTurn;
	static int[][] map;
	static boolean[][] checked;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		checked = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		location = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		direction = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		checked[location.x][location.y] = true;
		maxTurn = 0;
		ans = 1;
		
		while (true) {
			turn();
			
			int nextX = location.x + dx[direction];
			int nextY = location.y + dy[direction];
			if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && map[nextX][nextY] == 0 && !checked[nextX][nextY]) {
				location = new Pair(nextX, nextY);
				ans++;
				checked[nextX][nextY] = true;
				maxTurn = 0;
			} else {
				maxTurn++;
			}
			
			if (maxTurn >= 4) {
				nextX = location.x - dx[direction];
				nextY = location.y - dy[direction];
				
				if (map[nextX][nextY] == 1) {
					break;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	public static void turn() {
		if (direction == 0) {
			direction = 3;
		} else {
			direction--;
		}
	}
}