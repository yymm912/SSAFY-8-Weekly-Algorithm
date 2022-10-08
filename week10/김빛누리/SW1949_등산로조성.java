package week10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1949_등산로조성 {
	
	static class Node{
		int y, x, h;

		public Node(int y, int x, int h) {
			super();
			this.y = y;
			this.x = x;
			this.h = h;
		}
		
	}

	static int N, K, top, result;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 초기화
			//topList.clear();
			result = 0;
			top = 0;
			
			// 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int input = Integer.parseInt(st.nextToken());
					map[i][j] = input;
					if(input > top) {
						top = input;
					}
				}
			}
			
			// 봉우리 dfs
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == top) {
						visit[i][j] = true;
						dfs(new Node(i, j, map[i][j]), true, 1);
						//topList.add(new Node(i, j, map[i][j]));
						visit[i][j] = false;
					}
				}
			}
			
			System.out.println("#"+t+" "+result);
			
		}
		
		
	}
	private static void dfs(Node node, boolean canRemove, int len) {
		result = Math.max(result, len);
		for (int d = 0; d < 4; d++) {
			int ny = node.y + dy[d];
			int nx = node.x + dx[d];
			
			if(ny < 0|| ny >= N || nx < 0 || nx >= N || visit[ny][nx]) continue;
			
			if(node.h > map[ny][nx]) {
				visit[ny][nx] = true;
				dfs(new Node(ny, nx, map[ny][nx]), canRemove, len+1);
				visit[ny][nx] = false;
				
			} else if(canRemove && node.h > map[ny][nx]-K) {
				visit[ny][nx] = true;
				dfs(new Node(ny, nx, node.h-1), false, len+1);
				visit[ny][nx] = false;
			}
			
		}
		
	}
	

}
