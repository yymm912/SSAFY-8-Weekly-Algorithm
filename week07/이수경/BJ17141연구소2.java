package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17141연구소2 {
	
	static int N, M;
	static int map[][];
	static int mapCopy[][];
	static List<Dist> virusAvail = new ArrayList<>();
	static int time;
	static int min = Integer.MAX_VALUE;
	static Dist tgt[];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static boolean visit[][];
	static boolean visitCopy[][];
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		mapCopy = new int[N][N];
		visit = new boolean[N][N];
		visitCopy = new boolean[N][N];
		tgt = new Dist[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2 ) { // 바이러스 가능성 있는 목
					virusAvail.add(new Dist(i, j));
				}
				if(map[i][j] == 1) visitCopy[i][j] = true;
			}
		}
		// 입력
		
		// 빈 칸0, 벽 1, 바이러스 가능 2
		
		comb(0, 0);
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		
	}
	static void comb(int srcIdx, int tgtIdx) {
		if(tgtIdx == M ) {
			// 뽑힌 바이러스 리스트들에 대해 BFS
			flag = false;
			time = 0;
			bfs(); 
			if(!flag) min = Math.min(min, time-1);
			return;
		}
		
		if( srcIdx == virusAvail.size() ) return;
		
		tgt[tgtIdx] = virusAvail.get(srcIdx);
		comb( srcIdx + 1, tgtIdx + 1 );
		comb( srcIdx + 1, tgtIdx );
		
	}
	
	static void bfs() {
		// 현재 tgt 안에 바이러스 목록 인덱스 M개 들어있음.
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visit[i][j] = visitCopy[i][j];
				
			}
		}
		
		Deque<Dist> q = new ArrayDeque<>();
		for (int i = 0; i < tgt.length; i++) {
			q.offer(tgt[i]);
			visit[tgt[i].y][tgt[i].x] = true;
		}
		
		while(!q.isEmpty()) {
			
			int q_size = q.size();
			time++;
			for (int i = 0; i < q_size; i++) {
				
				Dist e = q.poll();
				for (int d = 0; d < 4; d++) {
					int py = dy[d] + e.y;
					int px = dx[d] + e.x;
					if(py < 0 || px < 0 || px >= N || py >= N || visit[py][px] ) continue;
					visit[py][px] = true;
					q.offer(new Dist(py, px));
					
				}

			}
			
		}
		
		// 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1 출력
		// : 0이 남아있는 경우에 -1
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					flag = true;
					break;
				}
			}
		}
		
		
	}
	
	static class Dist{
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

}
