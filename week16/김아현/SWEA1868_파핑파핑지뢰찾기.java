package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA1868_파핑파핑지뢰찾기 {

	static int T, N, res;
	static char[][] map;
	static boolean[][] visit;
	static int[][] boomCnt;
	
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new char[N][];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			} // 입력 끝
			
			countAround();
			
			res = 0;
			for (int i = 0; i < N; i++) { // 8방에 폭탄이 없는 곳 먼저 클릭
				for (int j = 0; j < N; j++) {
					if(map[i][j] == '.' && boomCnt[i][j] == 0) {
						map[i][j] = 0;
						click(i, j);
						res++;
					}
				}
			}
			
			for (int i = 0; i < N; i++) { // 클릭 못한 나머지 카운트
				for (int j = 0; j < N; j++) {
					if(map[i][j] == '.') {
						res++;
					}
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}
	
	static void countAround() {
		boomCnt = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				int sum = 0;
				for (int d = 0; d < 8; d++) {
					int tx = i + dx[d];
					int ty = j + dy[d];
					
					if(tx < 0 || ty < 0 || tx >= N || ty >= N) continue;
					if(map[tx][ty] == '*') sum += 1; // 인접한 지뢰 수 카운트
				}
				boomCnt[i][j] = sum;
			}
		}
	}

	static void click(int x, int y) { // bfs
		Queue<int[]> qu = new ArrayDeque<>();
		qu.offer(new int[] {x, y});
		
		while(!qu.isEmpty()) {
			int[] n = qu.poll();
			
			for (int d = 0; d < 8; d++) {
				int tx = n[0] + dx[d];
				int ty = n[1] + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= N || map[tx][ty] != '.') continue;
				if(boomCnt[tx][ty] == 0) // 0 이면 큐에 넣기
					qu.offer(new int[] {tx, ty});
				map[tx][ty] = (char)(boomCnt[tx][ty] + '0'); // 0과 인접한 칸에 숫자 넣기
			}
		}
	}
	
}
