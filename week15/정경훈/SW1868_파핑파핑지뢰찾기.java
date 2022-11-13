package swea.파핑파핑지뢰찾기_1868;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	static int T, N, ans;
	static char[][] map;
	static int[][] mineCnt;
	
	static boolean[][] visit;
	static Queue<Node> q = new ArrayDeque<>();
	
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;
			map = new char[N][N]; // 원래 입력 맵
			mineCnt = new int[N][N]; // 주변에 있는 지뢰 수
			visit = new boolean[N][N]; // 이게 bfs 방문했는지 == 클릭을했는지
			
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			makeMineMap();
			
			clickMap();
			
			System.out.println("#" + t + " " + ans);
		}
	}
	static void clickMap() {
		zeroClick();
		restClick();
	}
	
	static void zeroClick() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[i][j] && mineCnt[i][j] == 0 && map[i][j] != '*') {
					bfs(i,j);
					ans++;
				}
			}
		}
	}
	
	static void restClick() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if(visit[i][j] || map[i][j] == '*') continue;
				visit[i][j] = true;
				ans++;
				
			}
		}
	}
	
	static void bfs(int y, int x) {
		q.clear();
		q.add(new Node(y, x));
		visit[y][x] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for (int d = 0; d < 8; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=N || visit[ny][nx] || map[ny][nx] == '*') continue;
				visit[ny][nx] = true;
				if(mineCnt[ny][nx] == 0) {
					q.add(new Node(ny, nx));
				}
			}
		}
	}
	static void makeMineMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == '.') {
					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
						if(map[ny][nx] == '*') cnt++;
					}
					mineCnt[i][j] = cnt;
				}
			}
		}
	}
	
	static class Node{
		int y,x;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
