package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_15683_감시 {

	static int N, M, zero, answer;
	static int[][] map;
	static List<int[]> list;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {-1,0,1,0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>(); 
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp>0 && tmp<6) list.add(new int[] {i,j,tmp});
				if(tmp==0) zero++;
				map[i][j] = tmp;
			}
		}
		
		Collections.sort(list, (e1,e2)->e2[2]-e1[2]);
		
		while(!list.isEmpty() && list.get(0)[2]==5) {
			
			int rc[] = list.get(0);
			
			for(int i=0; i<4; i++) {
				
				int nr=rc[0], nc=rc[1];
				
				while(true) {
					
					nr+=dr[i];
					nc+=dc[i];
					
					if(nr <0 || nr>=N || nc<0 || nc>=M || map[nr][nc] > 5) break;
					if(map[nr][nc]==0) zero--;
					map[nr][nc] = 1;
				}
				
			}
			list.remove(0);
		}
		answer = zero;
		backtrack(0,zero);
		
		System.out.println(answer);
		
	}
	
	static void backtrack(int depth, int val) {
		if(depth==list.size()) {
			answer = Math.min(answer, val);
			return;
		}
		
		switch (list.get(depth)[2]) {
		case 1:
			bfs(list.get(depth)[0], list.get(depth)[1], val, depth, 1);
			break;
		case 2:
			twoBfs(list.get(depth)[0], list.get(depth)[1],val,depth);
			break;
		case 3:
			bfs(list.get(depth)[0], list.get(depth)[1], val, depth, 3);
			break;
		case 4:
			bfs(list.get(depth)[0], list.get(depth)[1], val, depth, 4);
			break;
		}
		
		
	}
	
	static void twoBfs(int r, int c, int val, int depth) {
		Queue<int[]> que = new ArrayDeque<>();
		
		for(int k=0; k<2; k++) {
			for(int i=k; i<4; i+=2) {
				int nr = r;
				int nc = c;
				
				while(true) {
					
					nr+=dr[i];
					nc+=dc[i];
					if(nr <0 || nr>=N || nc<0 || nc>=M || map[nr][nc] > 5) break;

					if(map[nr][nc]==0) {
						map[nr][nc] = 1;
						que.add(new int[] {nr,nc});
						val--;
					}
					
					
				}
			}
			backtrack(depth+1, val);
			while(!que.isEmpty()) {
				int[] rc = que.poll();
				map[rc[0]][rc[1]] = 0;
				val++;
			}
		}
		
	}
	
	static void bfs(int r, int c, int val, int depth, int mode) {
		
		Queue<int[]> que = new ArrayDeque<>();
		
		if(mode == 1) {
			for(int i=0; i<4; i++) {
				int nr = r;
				int nc = c;
				
				while(true) {
					
					nr+=dr[i];
					nc+=dc[i];
					if(nr <0 || nr>=N || nc<0 || nc>=M || map[nr][nc] > 5) break;
					
					if(map[nr][nc]==0) {
						map[nr][nc] = 1;
						que.add(new int[] {nr,nc});
						val--;
					}
				}
				
				backtrack(depth+1, val);
				while(!que.isEmpty()) {
					int[] rc = que.poll();
					map[rc[0]][rc[1]] = 0;
					val++;
				}
				
			}
		}
		else if (mode==3) {
			
			for(int k=0; k<4; k++) {
				for(int i=k; i<k+2; i++) {
					int nr = r;
					int nc = c;
					
					while(true) {
						
						nr+=dr[i%4];
						nc+=dc[i%4];
						if(nr <0 || nr>=N || nc<0 || nc>=M || map[nr][nc] > 5) break;
						
						if(map[nr][nc]==0) {
							map[nr][nc] = 1;
							que.add(new int[] {nr,nc});
							val--;
						}
					}
				}
				backtrack(depth+1, val);
				while(!que.isEmpty()) {
					int[] rc = que.poll();
					map[rc[0]][rc[1]] = 0;
					val++;
				}
			}
			
		}
		else if(mode==4) {
			
			for(int k=0; k<4; k++) {
				for(int i=k; i<k+3; i++) {
					int nr = r;
					int nc = c;
					
					while(true) {
						
						nr+=dr[i%4];
						nc+=dc[i%4];
						if(nr <0 || nr>=N || nc<0 || nc>=M || map[nr][nc] > 5) break;
						
						if(map[nr][nc]==0) {
							map[nr][nc] = 1;
							que.add(new int[] {nr,nc});
							val--;
						}
						
					}
				}
				backtrack(depth+1, val);
				while(!que.isEmpty()) {
					int[] rc = que.poll();
					map[rc[0]][rc[1]] = 0;
					val++;
				}
			}
		}
	}
}