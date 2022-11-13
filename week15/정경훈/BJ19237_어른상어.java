package baekjoon.어른상어_19237;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K, time, cnt;
	static Node[][] map;
	static Shark[] sharks;
	
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		time = 0;
		cnt = M; // 남은 상어의 수
		map = new Node[N+1][N+1];
		sharks = new Shark[M+1];
		
		// 맵 정보 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n!=0) {
					map[i][j] = new Node(n,K);
					sharks[n] = new Shark(i,j,n);
				}else {
					map[i][j] = new Node(n,0);
				}
			}
		}
		
		// 상어의 방향 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken());
		}
		
		// 우선순위 방향 입력
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					sharks[i].pd[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		start();
		
		System.out.println(time);
		
		
	}
	
	static void start() {
		while(time<1000) {
			time++;
			// 상어를 움직여보자.
			sharkMove();
			mapUpdate();
			if(check()) {
				return;
			}
			
		}
		time = -1;
	}
	
	static void sharkMove() {
		for (int i = 1; i <= M; i++) {
			if(sharks[i].out) continue; // 격자밖으로 나갔으면 끝
			
			int y = sharks[i].y;
			int x = sharks[i].x;
			int ty = 0;
			int tx = 0;
			int td= 0;
			for (int j = 0; j < 4; j++) {
				int d = sharks[i].pd[sharks[i].d][j];
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny<=0 || nx<=0 || ny>N || nx>N) continue;
				if(map[ny][nx].n == 0) {
					ty = ny;
					tx = nx;
					td = d;
					break;
				}else if(map[ny][nx].n == sharks[i].n && ty==0) { // 우선순위에서 제일 처음만
					ty = ny;
					tx = nx;
					td = d;
					continue;
				}
			}
			sharks[i].y = ty;
			sharks[i].x = tx;
			sharks[i].d = td;
		}
	}
	
	static void mapUpdate() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Node node = map[i][j];
				if(node.n == 0) continue;
				else {
					node.smell--;
					if(node.smell == 0) {
						node.n = 0;
					}
				}
			}
		}
		for (int i = 1; i <= M; i++) {
			if(sharks[i].out) continue;
			int y = sharks[i].y;
			int x = sharks[i].x;
			if(map[y][x].smell == K) {
				sharks[i].out = true;
			}else {
				map[y][x].n = sharks[i].n;
				map[y][x].smell = K;
			}
			
		}
	}
	
	static boolean check() {
		for (int i = 2; i <= M; i++) {
			if(!sharks[i].out) return false;
		}
		return true;
	}
	static class Node{
		int n; // 상어 번호  0이면 빈곳
		int smell; // 냄세 남은 횟수
		Node(int n, int smell){
			this.n = n;
			this.smell = smell;
		}
	}
	
	static class Shark{
		int[][] pd = new int[5][4];
		int y,x,n;
		int d;
		boolean out = false;
		Shark(int y, int x, int n){
			this.y = y;
			this.x = x;
			this.n = n;
		}
	}
}
