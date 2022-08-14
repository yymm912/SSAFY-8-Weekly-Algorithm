package BOJ;

import java.io.*;
import java.util.*;

public class  BOJ_13549_2{
	static int[] dx = {-1, 1};
	public static void main(String[] args) throws IOException{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
        bfs(n, k);
	}
	
	static void bfs(int st, int dest) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[100001];
		
		q.add(new int[]{st,0});
		visited[st] = true;
		
		while(!q.isEmpty()) {
			int[] x = q.poll();
			int locate = x[0];//위치
			int move = x[1];//움직임 수
			if(locate == dest) {//목표값과 일치
				System.out.println(move);
				return;
			}
			int jump = locate*2;//위치 두 배로
			if(jump < 100001 && !visited[jump]) {
				visited[jump] = true;
				q.add(new int[]{jump,move});//순간이동은 횟수 안 셈
			}

			for(int i=0; i<2; i++) {//-1 or 1
				int next = locate + dx[i];
				if (next>=0 && next <100001 && !visited[next]) {
					visited[next] = true;
					q.add(new int[]{next,move+1});
				}
			}
		}
	}
}