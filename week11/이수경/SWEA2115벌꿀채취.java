package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SWEA2115벌꿀채취6 {

	static int T;
	static int N, M, C;
	static int map[][];
	static int select[][];
	static Dist selected[];
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			max = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			select = new int[N][N];
			selected = new Dist[M*2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(1, 0);
			
			
			System.out.println("#" + t + " " + max);
		} // testcase

		
		
	}
	static void setMax() {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < selected.length; i++) {
			if(selected[i].select) {
				
				if( select[selected[i].y][selected[i].x] == 1 ) {
					sum1 += map[selected[i].y][selected[i].x];
				}
				else if( select[selected[i].y][selected[i].x] == 2 ) {
					sum2 += map[selected[i].y][selected[i].x];
				}
				
			}
				
		}
		int sum = 0;
		
		if( sum1 <= C && sum2 <= C) {
			for (int i = 0; i < selected.length; i++) {
				if(selected[i].select) {
					sum += map[selected[i].y][selected[i].x] * map[selected[i].y][selected[i].x] ;
				}
					
			}
		}
		
		max = Math.max(max, sum);
		
	}
	static void subset(int srcIdx) {
		
		if( srcIdx == selected.length ) {

			setMax();
			
			return;
		}
		
		// 선택하고 
		selected[srcIdx].select = true;
		subset(srcIdx + 1);
		
		// 선택하지 않고 
		selected[srcIdx].select = false;
		subset(srcIdx + 1);
		
		
	}
	static void dfs(int cnt, int idx) {
		
		if(cnt == 3) {
			
			int k = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(select[i][j] != 0) {
						selected[k] = new Dist(i, j, false);
						k++;
					}
				}
			}
			
			subset(0);
			
			return;
		}
		if(idx == N*N)return;
		
		int y = idx/N;
		int x = idx%N;
		
		if( x + M > N ) {
			// 한 줄을 넘어가면 
			dfs(cnt, (idx+M) - (idx+M)%N );
			
		}
		else {
			setSelect(cnt, y, x);
			dfs(cnt+1, idx + M);
			setSelect(cnt, y, x);
			dfs(cnt, idx+1);
		}
		
		
		
	}

	static void setSelect(int cnt, int y, int x) {
		for (int i = 0; i < M; i++) {
			if( select[y][i+x] == 0 ) {
				select[y][i+x] = cnt;
			}
			else {
				select[y][i+x] = 0;
			}
		}
	}
	
	static class Dist {
		int y, x;
		boolean select;
		public Dist(int y, int x, boolean select) {
			super();
			this.y = y;
			this.x = x;
			this.select = select;
		}
		
	}
	
	
}
/*
10
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7
3 3 10
7 2 9
6 6 6
5 5 7
4 1 10
8 1 8 2
4 6 1 6
4 9 6 3
7 4 1 3
4 3 12
8 8 6 5
5 2 7 4
8 5 1 7
7 8 9 4
5 2 11
7 5 9 9 6
7 3 7 9 3
1 7 1 4 5
1 7 9 2 6
6 6 8 3 8
6 3 20
8 5 2 4 3 1
4 3 6 1 1 8
4 4 1 2 3 1
1 7 4 9 6 1
6 5 1 2 8 4
3 1 4 5 1 3
7 2 11
2 8 2 5 2 8 6
2 3 7 4 6 4 8
3 7 8 3 9 4 4
8 8 5 9 3 6 9
9 7 6 2 4 1 3
2 9 2 8 9 7 3
2 1 7 2 7 8 3
8 3 12
9 1 6 7 5 4 6 7
9 5 1 8 8 3 5 8
5 2 6 8 6 9 2 1
9 2 1 8 7 5 2 3
6 5 5 1 4 5 7 2
1 7 1 8 1 9 5 5
6 2 2 9 2 5 1 4
7 1 1 2 5 9 5 7
9 4 20
5 2 4 8 3 7 6 2 1
7 9 8 5 8 2 6 3 6
1 9 4 6 7 5 3 1 1
4 4 7 6 2 2 8 1 7
9 6 8 5 7 3 7 9 5
7 3 1 4 1 1 8 5 3
4 6 8 9 4 5 3 8 8
1 3 4 2 4 1 1 3 6
5 9 2 3 5 2 4 8 5
10 5 30
7 4 7 5 9 3 6 4 6 7
8 9 8 4 5 7 8 9 2 9
6 5 3 4 6 4 7 6 3 2
4 7 4 3 4 7 3 3 4 3
3 5 6 4 8 8 2 1 8 6
3 7 9 7 1 7 6 2 8 9
3 6 1 6 8 9 7 7 5 1
4 3 5 6 2 1 2 6 3 6
3 4 9 2 1 5 9 9 6 3
9 9 7 3 7 5 5 5 8 4
*/