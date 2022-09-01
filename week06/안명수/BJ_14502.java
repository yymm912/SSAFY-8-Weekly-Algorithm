package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14502 {
	static int N, M, max;
	static int[][] lab;
	static List<int[]> virus = new ArrayList<>();
	static List<int[]> empty = new ArrayList<>();
	// delta
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		lab = new int[N][M];
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M; j++) {
				lab[i][j] = Integer.parseInt(stk.nextToken());
				if(lab[i][j] == 2) 
					virus.add(new int[] {i,j});
				else if(lab[i][j] == 0)
					empty.add(new int[] {i,j});
			}
		}
		
		max = 0;
		test(0,0);
		
		System.out.println(max);
	}
	
	public static void test(int Idx, int cnt) {
		if(cnt == 3) {
			BFS();
			return;
		}
		
		
		for(int i = Idx; i < empty.size(); i++) {
			int[] now = empty.get(i);
			lab[now[0]][now[1]] = 1;
			test(i + 1, cnt + 1);
			lab[now[0]][now[1]] = 0;
		}
	}
	
	public static void BFS() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		int[][] subLab = new int[N][M];
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				subLab[i][j]= lab[i][j];
		
		for(int[] ar : virus) {
			subLab[ar[0]][ar[1]] = 0;
			q.add(ar);
		}
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if(subLab[now[0]][now[1]] == 2) continue;
			subLab[now[0]][now[1]] = 2;
			
			for(int d = 0; d < 4; d++) {
				int ny = now[0] + dy[d];
				int nx = now[1] + dx[d];
				if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if(subLab[ny][nx] == 1 || subLab[ny][nx] == 2) continue;
				q.add(new int[] {ny,nx});
			}
		}
		
		
		int sum = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(subLab[i][j] == 0) sum++;
		
		max = Math.max(max, sum);
	}
}
