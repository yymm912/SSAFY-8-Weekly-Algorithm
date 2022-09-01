package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class SW_1767_프로세서연결하기 {
	
	static int N, ans, maxCnt;
	static int[][] map;
	static ArrayList<int[]> List;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			List = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i>0 && j > 0 && i <N-1 && j < N-1 && map[i][j]==1) List.add(new int[] {i,j});
				}
			}
			ans = 0;
			maxCnt = 0;

			dfs(0, 0, 0);
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int depth, int sum, int cnt) {
		
		Queue<int[]> que = new ArrayDeque<>();
		
		if(depth == List.size()) {
			if(maxCnt<cnt) {
				maxCnt = cnt;
				ans = sum;
			}
			else if(maxCnt==cnt) {
				ans = Math.min(ans, sum);
			}
			
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nr = List.get(depth)[0];
			int nc = List.get(depth)[1];
			int length = 0;
			
			while(true) {
				nr += dr[i]; 
				nc += dc[i];
				
				if(nr<0 || nc < 0 || nr>=N || nc>=N) {
					dfs(depth+1, sum+length, cnt+1);
					break;
				}
				
				if(map[nr][nc]!=0) break;
				map[nr][nc] = 1;
				length++;
				que.add(new int[] {nr,nc});
				
			}
			
			while(!que.isEmpty()) {
				int rc[] = que.poll();
				map[rc[0]][rc[1]] = 0;
			}
			
		}
		
		dfs(depth+1, sum, cnt);
		
	}
	
	
}