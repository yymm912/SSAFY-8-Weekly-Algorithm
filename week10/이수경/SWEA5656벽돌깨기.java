package sw;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA5656벽돌깨기4 {

	static int T, N, W, H;
	static int map[][];
	static int mapCopy[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static boolean visit[][];
	static int tgt[];
	static int min, sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			mapCopy = new int[H][W];
			visit = new boolean[H][W];
			tgt = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			perm(0); // 중복 순열 
			
			
			System.out.println("#" + t + " " + min);
		} // testcase

	}
	
	static void perm(int tgtIdx) {
		
		if(tgtIdx == N) {
			// 배열 복사 
			mapCopy = new int[H][W];

			copy();
			
			// 시뮬레이션 
			go();
			
			// 최솟값 갱신 
			update();
			return;
		}
		
		for (int i = 0; i < W; i++) { // 열 뽑기 
			tgt[tgtIdx] = i;
			perm(tgtIdx + 1);
		}
		
		
	}
	static void update() {
		int sum = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(mapCopy[i][j] > 0) sum++;
			}
		}
		min = Math.min(min, sum);
		
	}
	static void go() {
		
		for (int x : tgt) { // 뽑은 열에 대하여 
			
			// 제일 위에 있는 벽돌 찾기 
			int y = 0;
			while(y < H && mapCopy[y][x] == 0) y++;
			
			if( y == H ) continue; // 부술 벽돌이 없으면 넘어가기 
			else {
				// 벽돌 부수기 
				bomb(y, x);
				
				// 아래로 내리기 
				down();
			}
			
			
		}
		
	}
	static void down() {
		
		Stack<Integer> st = new Stack<>();
		
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if(mapCopy[j][i] != 0 ) {
					st.push(mapCopy[j][i]);
					mapCopy[j][i] = 0;
				}
			}
			
			int idx = H-1;
			while(!st.isEmpty()) {
				mapCopy[idx--][i] = st.pop();
			}
		}
		
	}
	static void bomb(int y, int x) {
		
		Deque<Dist> q = new ArrayDeque<>();
		
		if( mapCopy[y][x] > 1) {
			q.offer(new Dist(y, x, mapCopy[y][x]));
		}
		mapCopy[y][x] = 0;
		
		while(!q.isEmpty()) {
			Dist e = q.poll();
			for (int d = 0; d < 4; d++) {
				int py = e.y;
				int px = e.x;
				for (int i = 0; i < e.cnt - 1; i++) {
					py += dy[d];
					px += dx[d];
					if(py < 0 || px < 0 || py >= H || px >= W) continue;

					if( mapCopy[py][px] > 1) {
						q.offer(new Dist(py, px, mapCopy[py][px]));
					}
					mapCopy[py][px] = 0;
					
				}
			}
			
		}
		
	}
	


	static void copy() {
		// 배열 복사 
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				mapCopy[i][j] = map[i][j];
			}
		}
	}
	
	
	static class Dist {
		int y, x;
		int cnt;
		public Dist(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
	}

}
/*
5
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1
2 9 10
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
1 1 0 0 1 0 0 0 0
1 1 0 1 1 1 0 1 0
1 1 0 1 1 1 0 1 0
1 1 1 1 1 1 1 1 0
1 1 3 1 6 1 1 1 1
1 1 1 1 1 1 1 1 1
3 6 7
1 1 0 0 0 0
1 1 0 0 1 0
1 1 0 0 4 0
4 1 0 0 1 0
1 5 1 0 1 6
1 2 8 1 1 6
1 1 1 9 2 1
4 4 15
0 0 0 0 
0 0 0 0 
0 0 0 0 
1 0 0 0 
1 0 0 0 
1 0 0 0 
1 0 0 0 
1 0 5 0 
1 1 1 0 
1 1 1 9 
1 1 1 1 
1 6 1 2 
1 1 1 5 
1 1 1 1 
2 1 1 2 
4 12 15
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
*/
