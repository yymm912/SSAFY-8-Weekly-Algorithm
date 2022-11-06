package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BJ1405미친로봇 {

	static int N;
	static int dy[] = { 0,   0, 1, -1 }; // 동 서 남 북 
	static int dx[] = {  1, -1, 0,  0 };
	static double per[] = new double[4];
	static int map[][] = new int[30][30];
	static boolean visit[][] = new boolean[30][30];
	static double ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 4; i++) {
			per[i] = Integer.parseInt(st.nextToken()) * 0.01;
		}
		
		// 넉넉잡아 30으로 잡고 ..
		
		visit[15][15] = true;
		dfs(0, 1, 15, 15);
		
		
		System.out.println(ans);
		
	}
	static void dfs(int cnt, double percent, int y, int x) {
		if(cnt == N) { // 기저 조건
			ans += percent;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if( py < 0 || px < 0 || py >= 30 || px >= 30 || visit[py][px]) continue;
			
			visit[py][px] = true;
			dfs(cnt + 1, percent * per[d] , py, px);
			visit[py][px] = false;
			
		}
	
		
	}

}
