package algorithm.TC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_음료수얼려먹기 {
	static int N, M, ans;
	static char[][] iceMAP;
	
	//delta 상하좌우
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk  = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		iceMAP = new char[N][];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			iceMAP[i] = str.toCharArray();			
		}
		
		
		// 빈공간을 발견하면 DFS 탐색 + 카운트 증가
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(iceMAP[i][j] == '0') {
					ans++;
					dfs(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int y, int x) {
		// 0인 경우에만 탐색을 진행하기때문에 기저조건 필요없음
		
		// 현재 위치를 얼음으로 변환
		iceMAP[y][x] = '1';
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			// 범위를 벗어나거나 해당 위치가 이미 얼음이면 패스
			if(ny < 0 || ny >= N || nx < 0 || nx >= M || iceMAP[ny][nx] == '1')continue;
			dfs(ny,nx);
		}
		
	}
}
