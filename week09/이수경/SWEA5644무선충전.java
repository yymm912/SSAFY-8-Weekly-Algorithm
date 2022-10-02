package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sw.SW_무선충전_5644.BC;

public class SWEA5644무선충전 {

	static int T, M, A;
	static int user[][];
	static Dist BC[];
	static int dy[] = { 0, -1, 0, 1, 0 }; // 제자리 상 우 하 좌 
	static int dx[] = { 0,  0, 1, 0,-1 };
	static int ans;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken()); // 이동한 거리 
			A = Integer.parseInt(st.nextToken()); // BC 개수
			user = new int[2][M];
			BC = new Dist[A];
			ans = 0;
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					user[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				
				BC[i] = new Dist(Y, X, C, P);
			}
			// 	입력 완 
		
			int uy = 1;
			int ux = 1;
			int vy = 10;
			int vx = 10;
			// 맨 처음거도 충전 
			ans += cal(uy, ux, vy, vx);
			for (int i = 0; i < M; i++) {
				uy += dy[user[0][i]];
				ux += dx[user[0][i]];
				
				vy += dy[user[1][i]];
				vx += dx[user[1][i]];
				
				ans += cal(uy, ux, vy, vx);
//				check(uy, ux, vy, vx);
				
			}
			
			
			
			System.out.println("#" + t + " " + ans);	
		} // testcase
		
		
	}
	
	
	static int cal(int uy, int ux, int vy, int vx) {
		
		int max = 0;
		// user1의 충전소 
		for (int i = 0; i < BC.length; i++) {
			// user2의 충전소 
			for (int j = 0; j < BC.length; j++) {
				int sum = 0;
				int amountA = getPower(uy, ux, i);
				int amountB = getPower(vy, vx, j);
				
				if( i == j ) { // 같은 충전소를 쓴다면 ? 
					sum = Math.max(amountA, amountB);
				}
				else if ( i != j ) { // 다른 충전소를 쓴다면 ?
					sum = amountA + amountB;
				}
				
				if( max < sum) max = sum;
				
			}
		}
		
		return max;
		
	}
	
	static int getPower(int y, int x, int idx) {
		
		if( (Math.abs(y - BC[idx].y ) + Math.abs(x - BC[idx].x)) <= BC[idx].c ) {
			return BC[idx].p;
		}
		
		return 0;
		
	}
	
	static class Dist {
		int y, x;
		int c;
		int p;
		public Dist(int y, int x, int c, int p) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
		
	}
}

/*
5
20 3
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
4 4 1 100
7 10 3 40
6 3 2 70
40 2
0 3 0 3 3 2 2 1 0 4 1 3 3 3 0 3 4 1 1 3 2 2 2 2 2 0 2 3 2 2 3 4 4 3 3 3 2 0 4 4 
0 1 0 3 4 0 4 0 0 1 1 1 0 1 4 4 4 4 4 3 3 3 0 1 0 4 3 2 1 4 4 3 2 3 2 2 0 4 2 1 
5 2 4 140
8 3 3 490
60 4
0 3 3 3 0 1 2 2 2 1 2 2 3 3 4 4 0 3 0 1 1 2 2 3 2 2 3 2 2 0 3 0 1 1 1 4 1 2 3 3 3 3 3 1 1 4 3 2 0 4 4 4 3 4 0 3 3 0 3 4 
1 1 4 1 1 1 1 1 1 4 4 1 2 2 3 2 4 0 0 0 4 3 3 4 3 3 0 1 0 4 3 0 4 3 2 3 2 1 2 2 3 4 0 2 2 1 0 0 1 3 3 1 4 4 3 0 1 1 1 1 
6 9 1 180
9 3 4 260
1 4 1 500
1 3 1 230
80 7
2 2 2 2 2 2 0 2 2 0 4 0 2 3 3 2 3 3 0 3 3 3 4 3 3 2 1 1 1 0 4 4 4 1 0 2 2 2 1 1 4 1 2 3 4 4 3 0 1 1 0 3 4 0 1 2 2 2 1 1 3 4 4 4 4 4 4 3 2 1 4 4 4 4 3 3 3 0 3 3 
4 4 1 1 2 1 2 3 3 3 4 4 4 4 4 1 1 1 1 1 1 1 1 0 3 3 2 0 4 0 1 3 3 3 2 2 1 0 3 2 3 4 1 0 1 2 2 3 2 0 4 0 3 4 1 1 0 0 3 2 0 0 4 3 3 4 0 4 4 4 4 0 3 0 1 1 4 4 3 0 
4 3 1 170
10 1 3 240
10 5 3 360
10 9 3 350
9 6 2 10
5 1 4 350
1 8 2 450
100 8
2 2 3 2 0 2 0 3 3 1 2 2 2 2 3 3 0 4 4 3 2 3 4 3 3 2 3 4 4 4 2 2 2 0 2 2 4 4 4 4 1 1 1 2 2 2 4 3 0 2 3 3 4 0 0 1 1 4 1 1 1 1 2 2 1 1 3 3 3 0 3 2 3 3 0 1 3 3 0 1 1 3 3 4 0 4 1 1 2 2 4 0 4 1 1 2 2 1 1 1 
4 4 4 0 4 1 1 4 1 1 1 1 3 2 1 2 1 1 4 4 1 0 2 3 4 4 4 4 4 0 1 0 2 2 2 0 2 2 2 2 2 2 3 0 0 1 4 3 3 2 0 0 4 4 4 0 2 0 4 1 1 2 2 0 4 4 0 0 2 0 2 3 3 0 2 3 0 3 4 0 4 3 4 4 3 4 1 1 2 2 2 0 0 1 0 4 1 1 1 4 
3 4 2 340
10 1 1 430
3 10 4 10
6 3 4 400
7 4 1 80
4 5 1 420
4 1 2 350
8 4 4 300

*/