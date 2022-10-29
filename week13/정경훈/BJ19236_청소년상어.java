package baekjoon.청소년상어_19236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int max;
	static Shark shark;
	static Fish[][] map;
	
	// 상부터 1, 반시계로 45도씩 이동
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new Fish[4][4];
		max = 0;
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[i][j] = new Fish(a, b);
				map[i][j].addYX(i, j);
			}
		}
		
		shark = new Shark();
		start();
		
		System.out.println(max);
	}
	
	static void start() {
		// 상어 입장 -> 0, 0 자리 버-억
		sharkEnter();
		
		// 물고기 무빙
		fishMove();
		
		// 상어 무빙
		sharkMove();
	}
	
	static void sharkEnter() {
		shark.eat += map[0][0].n;
		map[0][0].die = true;
		shark.d.offerLast(map[0][0].d.peekLast());
		shark.point.offerLast(new Node(0,0));
	}
	
	static void sharkEat(int y, int x) {
		shark.eat += map[y][x].n;
		map[y][x].die = true;
		shark.d.offerLast(map[y][x].d.peekLast());
		shark.point.offerLast(new Node(y,x));
	}
	
	static void sharkBack2Eat(int ny, int nx, int y, int x, int d) {
		shark.eat -= map[ny][nx].n;
		map[ny][nx].die = false;
		shark.d.pollLast();
		shark.point.pollLast();
	}
	
	static void sharkMove() {
		Node sn = shark.point.peekLast();
		int y = sn.y;
		int x = sn.x;
		int d = shark.d.peekLast();
		int ny = y;
		int nx = x;
		while(true) {
			ny = ny + dy[d];
			nx = nx + dx[d];
			
			if(ny<0 || nx<0 || ny>=4 || nx>=4) {
				max = Math.max(max, shark.eat);
				return;
			}
			// 상어가 지나는 경로에 물고기가 살아있다면
			if(!map[ny][nx].die) {
				// 일단 먹고
				sharkEat(ny, nx);
				fishMove();
				sharkMove();
				fishBack2Move();
				sharkBack2Eat(ny, nx, y, x, d);
				
			}
		}
		
	}
	
	static void fishMove() {
		
		PriorityQueue<Fish> pq = new PriorityQueue<>((f1, f2)->f1.n - f2.n);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(!map[i][j].die)pq.offer(map[i][j]);
			}
		}
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			Fish f1 = pq.poll();
			int ny, nx;
			int y = f1.point.peekLast().y;
			int x = f1.point.peekLast().x;
			int d = f1.d.peekLast();
			Node sn = shark.point.peekLast();
			int sy = sn.y;
			int sx = sn.x;
			while(true) {
				ny = y + dy[d];
				nx = x + dx[d];
				// 이동하려는 칸에 
				// #1 상어가 있다면?
				if(ny == sy && nx == sx) {
					d = d == 8 ? 1 : d+1;
					continue;
				}
				// #2 벽이라면?
				if(ny<0 || nx<0 || ny>=4 || nx>=4) {
					d = d == 8 ? 1 : d+1;
					continue;
				}
				// 모두 아니라면 자리를 옮김
				break;
			}
			swapFish(y, x, ny, nx);
			f1.d.offerLast(d);
			map[y][x].addYX(y, x);
			map[ny][nx].addYX(ny, nx);
		}
	}
	
	static void fishBack2Move() {
		PriorityQueue<Fish> pq = new PriorityQueue<>((f1, f2)->f2.n - f1.n);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(!map[i][j].die)pq.offer(map[i][j]);
			}
		}
		
		int size = pq.size();
		
		for (int i = 0; i < size; i++) {
			Fish f1 = pq.poll();
			Node cn = f1.point.pollLast();
			Node pn = f1.point.peekLast();
			int cy = cn.y;
			int cx = cn.x;
			int py = pn.y;
			int px = pn.x;
			map[py][px].point.pollLast();
			swapFish(cy, cx, py, px);
			f1.d.pollLast();
			
		}
	}
	
	static void swapFish(int y1, int x1, int y2, int x2){
		Fish tmp = map[y1][x1];
		map[y1][x1] = map[y2][x2];
		map[y2][x2] = tmp;
	}
	
	static class Shark{
		int eat;
		Deque<Node> point = new ArrayDeque<>();
		Deque<Integer> d = new ArrayDeque<>();
		
		public void addYX(int y, int x){
			point.offerLast(new Node(y,x));
		}
	}
	
	static class Fish{
		int n;
		Deque<Node> point = new ArrayDeque<>();
		Deque<Integer> d = new ArrayDeque<>();
		boolean die = false;
		
		Fish(int n, int d){
			this.n = n;
			this.d.offerLast(d);
		}
		
		public void addYX(int y, int x){
			point.offerLast(new Node(y,x));
		}
	}
	
	static class Node{
		int y, x;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
