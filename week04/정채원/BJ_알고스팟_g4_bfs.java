import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static char[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int result = bfs();
		System.out.println(result);
	}
	static class E{
		int x, y, cnt;
		public E(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int bfs() {
		// Queue : x, y, cnt(부순 수)
		// breakCnt[][] : 현재 칸까지 부순 수 : 최소 cnt -> 최적 결과
		Queue<E> queue = new ArrayDeque<>();
		int[][] breakCnt = new int[N][M];
		for(int i=0; i<N; i++) {
			Arrays.fill(breakCnt[i], 987654321);			
		}
		queue.add(new E(0,0,0));
		breakCnt[0][0] = 0;
		
		while(!queue.isEmpty()) {
			E cur = queue.remove();
//			if(cur.x == M-1 && cur.y == N-1) break;
			
			for(int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				int ncnt = cur.cnt;				
				if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
				if(map[ny][nx] == '1') ncnt++;
				if(breakCnt[ny][nx] <= ncnt) continue;
				queue.add(new E(nx, ny, ncnt));
				breakCnt[ny][nx] = ncnt;
			}
		}
		return breakCnt[N-1][M-1];
	}
}