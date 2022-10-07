package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17822 {
	static int N, M, T;
	static int x, d, k;
	
	static int[][] circle;
	static boolean[][] erase;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		T = Integer.parseInt(stk.nextToken());
		
		circle = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				circle[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		while(T > 0) {
			stk = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(stk.nextToken());
			d = Integer.parseInt(stk.nextToken());
			k = Integer.parseInt(stk.nextToken());
			
			// 상 우 하 좌 순으로 1234
			for(int i = 1; x*i <= N; i++) {
				spin(x*i, d, k % M);
			}
			
			
			removeNum();
			
			
			T--;
		}
		
		
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				sum += circle[i][j];
			}
		}
		System.out.println(sum);
	}
	
	static void spin(int tgt, int clock, int cnt) {
		while(cnt > 0) {
			if(clock == 0) { // 시계방향
				int save = circle[tgt][M];
				for(int i = M; i > 1; i--) {
					circle[tgt][i] = circle[tgt][i - 1];
				}
				
				circle[tgt][1] = save;
			}else { // 반시계 방향
				int save = circle[tgt][1];
				for(int i = 1; i < M; i++) {
					circle[tgt][i] = circle[tgt][i + 1];
				}
				
				circle[tgt][M] = save;
			}
			cnt--;
		}
	}
	
	static void removeNum() {
		erase = new boolean[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				typeOne(i, j);
				typeTwo(i, j);
			}
		}
		
		boolean eraseSome = false;
		int sum = 0;
		int cnt = 0;
		for(int i = 1 ; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(erase[i][j]) {
					circle[i][j] = 0;
					eraseSome = true;
				}
				
				if(circle[i][j] != 0) cnt++;
				sum += circle[i][j];
			}
		}
		
		if(eraseSome) return;
		
		int avg = sum / cnt;
		int mod = sum % cnt;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(circle[i][j] == 0) continue;
				
				if(circle[i][j] > avg) circle[i][j]--;
				else if(mod != 0 && circle[i][j] == avg || circle[i][j] < avg) circle[i][j]++;
				
				
			}
		}
	}
	
	static void typeOne(int i, int j) {
		if(circle[i][j] == 0) return;
		int plus = j + 1;
		int minus = j - 1;
		
		if(plus == M + 1) plus = 1;
		if(minus == 0) minus = M;
		
		if(circle[i][j] == circle[i][plus])
			erase[i][j] = erase[i][plus] = true;

		if(circle[i][j] == circle[i][minus])
			erase[i][j] = erase[i][minus] = true;
	}
	
	static void typeTwo(int i, int j) {
		if(circle[i][j] == 0) return;
		int plus = i + 1;
		int minus = i - 1;
		
		if(plus <= N && circle[i][j] == circle[plus][j])
			erase[i][j] = erase[plus][j] = true;

		if(minus >= 1 && circle[i][j] == circle[minus][j])
			erase[i][j] = erase[minus][j] = true;
	}
}
