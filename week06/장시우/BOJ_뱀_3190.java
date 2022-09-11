package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

class Pair {
	int time;
	char dir;
	
	Pair(int time, char dir) {
		this.time = time;
		this.dir = dir;
	}
}

class Snake {
	int x;
	int y;
	
	Snake(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_뱀_3190 {
	
	static int N, appleCnt, appleX, appleY, moveCnt, pTime, dir, ans, time;
	static char pDir;
	static int[][] map;
	static List<Pair> list;
	static Deque<Snake> dq;
	static Pair p;
					   //우, 하, 좌, 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws Exception {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<>();
		
		appleCnt = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < appleCnt; i++) {
			st = new StringTokenizer(br.readLine());
			appleX = Integer.parseInt(st.nextToken()) - 1;
			appleY = Integer.parseInt(st.nextToken()) - 1;
			map[appleX][appleY] = 1;
		}
		
		moveCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < moveCnt; i++) {
			st = new StringTokenizer(br.readLine());
			pTime = Integer.parseInt(st.nextToken());
			pDir = st.nextToken().charAt(0);
			
			p = new Pair(pTime, pDir);
			list.add(p);
		}
		
		// Logic
		map[0][0] = -1;
		dir = 0;
		dq = new ArrayDeque<>();
		dq.add(new Snake(0, 0));
		time = 0;
		ans = 0;
		
		while (true) {
			ans++;
			Snake head = dq.peekLast();
			int nextHeadX = head.x + dx[dir];
			int nextHeadY = head.y + dy[dir];
			
			if (nextHeadX < 0 || nextHeadX >= N || nextHeadY < 0 || nextHeadY >= N || hit(nextHeadX, nextHeadY)) {
				break;
			}
			
			dq.add(new Snake(nextHeadX, nextHeadY));
			if (map[nextHeadX][nextHeadY] == 1) {
				map[nextHeadX][nextHeadY] = -1;
			} else {
				dq.removeFirst();
			}
			
			time += 1;
			for (int i = 0; i < list.size(); i++) {
				if (time == list.get(i).time) {
					turn(list.get(i).dir);
				} 
			}
		}
		
		System.out.println(ans);
	}
	
	public static void turn(char c) {
		if (c == 'D') {
			if (dir == 3) {
				dir = 0;
			} else {
				dir += 1;
			}
		} else if (c == 'L') {
			if (dir == 0) {
				dir = 3;
			} else {
				dir -= 1;
			}
		}
	}
	
	public static boolean hit(int nextX, int nextY) {
		Iterator<Snake> itr = dq.iterator();
		while (itr.hasNext()) {
			Snake snake = itr.next();
			if (snake.x == nextX && snake.y == nextY) {
				return true;
			}
		}
		
		return false;
	}
}