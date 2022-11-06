package baekjoon.스타트택시_19238;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, F, ty, tx, ans;
	static int[][] map;
	static PriorityQueue<Passenger> pq = new PriorityQueue<>((p1, p2) -> p1.d == p2.d ? (p1.sy == p2.sy ? p1.sx - p2.sx : p1.sy - p2.sy) : p1.d - p2.d);
	static Passenger[] list;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1]; // 0 dummy
		list = new Passenger[M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		ty = Integer.parseInt(st.nextToken());
		tx = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			list[i] = new Passenger(sy, sx, ey, ex, 0);
		}
		
		start();
		
		System.out.println(F);
		
	}
	
	static void start() {
		while(findPass()) {
			drive();
			if(F == -1) break;
		}
		
	}
	
	static boolean findPass() {
		pq.clear();
		boolean[][] visit = new boolean[N+1][N+1];
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(ty, tx, 0));
		visit[ty][tx] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < M; i++) {
				if(list[i].ok) continue;
				if(node.y == list[i].sy && node.x == list[i].sx) {
					list[i].d = node.d;
					pq.add(list[i]);
					break;
				}
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				
				if(ny<=0 || nx<=0 || ny>N || nx>N || visit[ny][nx] || map[ny][nx]==1) continue;
				visit[ny][nx] = true;
				q.add(new Node(ny, nx, node.d+1));
			}
		}
			
		
		if(pq.isEmpty()) {
			for (int i = 0; i < M; i++) {
				if(!list[i].ok) {
					F = -1;
					break;
				}
			}
			return false;
		}
		return true;
	}
	
	static void drive() {
		if(pq.isEmpty()) {
			F = -1;
			return;
		}
		Passenger p = pq.poll();
		if(p.d > F) {
			F = -1;
			return;
		}
		ty = p.sy;
		tx = p.sx;
		F -= p.d;
		
		boolean[][] visit = new boolean[N+1][N+1];
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(ty, tx, 0));
		visit[ty][tx] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.y == p.ey && node.x == p.ex) {
				ty = p.ey;
				tx = p.ex;
				p.ok = true;
				if(node.d > F) {
					F = -1;
					return;
				}else {
					F += node.d; 
				}
				return;
			}
			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				
				if(ny<=0 || nx<=0 || ny>N || nx>N || visit[ny][nx] || map[ny][nx]==1) continue;
				visit[ny][nx] = true;
				q.add(new Node(ny, nx, node.d+1));
			}
		}
		F=-1;
	}
	
	static class Node{
		int y, x, d;
		Node(int y, int x, int d){
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	
	static class Passenger{
		int sy, sx, ey, ex, d;
		boolean ok = false;
		Passenger(int sy, int sx, int ey, int ex, int d){
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
			this.d = d;
		}
	}
}
