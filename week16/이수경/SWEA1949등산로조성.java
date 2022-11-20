package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1949등산로조성2 {
	

	static int T;
	static int N, K;
	static int map[][];
	static int mapCopy[][];
	static boolean visit[][];
	static int topValue;
	static List<Dist> top = new ArrayList<>();
	
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			topValue = Integer.MIN_VALUE;
			ans = Integer.MIN_VALUE;
			top.clear();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			
			map = new int[N][N];
			mapCopy = new int[N][N];
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mapCopy[i][j] = Integer.parseInt(st.nextToken());
					topValue = Math.max(topValue, mapCopy[i][j]); // 봉우리 찾기 (가장 높은)
				}
			}
			
		
			// 가장 높은 봉우리에서 시작 
			// 높 -> 낮 지형 
			// 대각선불가능 
			// 딱 한곳을 정해서 최대 K깊이만큼 깎을 수 있음
			
			copyOfMap();
			// 봉우리 위치 저장 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if( map[i][j] == topValue ) {
						top.add(new Dist(i, j));
					}
				}
			}
			
			for (int i = 0; i < top.size(); i++) { // 시작점은 항상 봉우리
				visit = new boolean[N][N];
				copyOfMap();
				visit[top.get(i).y][top.get(i).x] = true;
				dfs(top.get(i).y, top.get(i).x, 1, 0, topValue); // 딱 한 곳만 정해서 최대 깊이만큼 지형 깎을 수 있음
				visit[top.get(i).y][top.get(i).x] = false;
			}
			
			System.out.println("#" + t + " " + (ans+1));
			
		} // testcase
		
		
		
	}
	static void dfs(int y, int x, int count, int depth, int topValue ) { // count는 남은 깎는 횟수 (항상1) / k는 현재 봉우리 인덱스 / depth는 거리 
		
		ans = Math.max(ans, depth); // 최대 깊이 출력 
		
		for (int d = 0; d < 4; d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if( py < 0 || px < 0 || py >= N || px >= N || visit[py][px] )continue;
			
			// 봉우리보다 더 작은 경우는 깎지 않아도 통과 
			if( topValue > map[py][px] ) {
				
				visit[py][px] = true;
				dfs(py, px, count, depth + 1, map[py][px] );
				visit[py][px] = false;
				
			}
			// 깎아서 봉우리보다 작아질 수 있다면? 깎기, 그래도 크거나 같다면 통과x
			else {
				// 1부터 k까지 다 깎아보는건가?
				if( count > 0 && topValue > (map[py][px] - K)) {
					visit[py][px] = true;
					// K만큼 깎을 수는 있지만, ( 일단if문에서 통과할 수 있다는것은 보장되었으니) 1만큼만 깎아서 다음에 갈 수 있는 최대 길이 확보
					dfs(py, px, count - 1, depth + 1, topValue - 1 );
					visit[py][px] = false;
				}
				
			}
			
		}
		
	}
	static void copyOfMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = mapCopy[i][j];
			}
		}
	}
	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	

	
}

/*

10
5 1
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
3 2
1 2 1
2 1 2
1 2 1
5 2
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
4 4
8 3 9 5
4 6 8 5
8 1 5 1
4 9 5 5
4 1
6 6 1 7
3 6 6 1
2 4 2 4
7 1 3 4
5 5
18 18 1 8 18
17 7 2 7 2
17 8 7 4 3
17 9 6 5 16
18 10 17 13 18
6 4
12 3 12 10 2 2
13 7 13 3 11 6
2 2 6 5 13 9
1 12 5 4 10 5
11 10 12 8 2 6
13 13 7 4 11 7
7 3
16 10 14 14 15 14 14
15 7 12 2 6 4 9
10 4 11 4 6 1 1
16 4 1 1 13 9 14
3 12 16 14 8 13 9
3 4 17 15 12 15 1
6 6 13 6 6 17 12
8 5
2 3 4 5 4 3 2 1
3 4 5 6 5 4 3 2
4 5 6 7 6 5 4 3
5 6 7 8 7 6 5 4
6 7 8 9 8 7 6 5
5 6 7 8 7 6 5 4
4 5 6 7 6 5 4 3
3 4 5 6 5 4 3 2
8 2
5 20 15 11 1 17 10 14
1 1 11 16 1 14 7 5
17 2 3 4 5 13 19 20
6 18 5 16 6 7 8 5
10 4 5 4 9 2 10 16
2 7 16 5 8 9 10 11
12 19 18 8 7 11 15 12
1 20 18 17 16 15 14 13
*/