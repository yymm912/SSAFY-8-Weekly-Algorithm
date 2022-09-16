package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14500테트로미노 {

	static int N, M;
	static int map[][];
	static boolean visit[][];
	static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우 
	static int dx[] = {  0, 0,-1, 1 };
	static int max = Integer.MIN_VALUE;
	static int sum1, sum2, sum3, sum4, sum5;
	static int last;
	static int mid;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완 
		
		// 5가지경우 테트로미노
		// 완전 탐색으로 4가지 경우 , 마지막 경우 ㅜ 는 불가능 
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				dfs(i, j, map[i][j], 1);
				chk(i, j);
				visit[i][j] = false;
				
			}
			
		}
		
		System.out.println(max);
		
	}
	
	static void dfs(int y, int x, int sum, int depth) {
		
		if(depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if(py < 0 || py >= N || px < 0 || px >= M || visit[py][px])continue;
			
			
			visit[py][px] = true;
			dfs(py, px, sum+map[py][px], depth + 1);
			visit[py][px] = false;
			
		}
		
		
	}
	
	static void chk(int y, int x) {
		
		int sum = 0;
		
		if( y+2 < N && y >= 0 && x+1 < M && x >= 0) {
		    //  ㅏ
	    	sum = map[y][x] + map[y+1][x] + map[y+2][x] + map[y+1][x+1];
	        max = Math.max(max, sum);			
		}


		if( y+1 < N && y-1 >= 0 && x+1 < M && x >= 0 ) {
		    //  ㅓ
	    	sum = map[y-1][x+1] + map[y][x+1]+ map[y+1][x+1] + map[y][x] ; 
	        max = Math.max(max, sum);			
		}
        
		if( y+1 < N && y >= 0 && x+2 < M && x >= 0) {
			//  ㅜ
	    	sum = map[y][x] + map[y][x+1] + map[y][x+2] + map[y+1][x+1];
	        max = Math.max(max, sum);
		}

		if( y < N && y-1 >= 0 && x+2 < M && x >= 0) {
		    //  ㅗ
	    	sum = map[y][x] + map[y][x+1] + map[y][x+2] + map[y-1][x+1];
	        max = Math.max(max, sum);			
		}

	    
	}
	
	
	
	
}
