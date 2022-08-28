package boj.아기상어_16236;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static FastReader sc = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int N,sy,sx,sSize,curEatCnt,ans;
	static int[][] map;
	static boolean[][] visit;
	static PriorityQueue<Point> fish = new PriorityQueue<>((o1,o2) -> o1.d == o2.d ? o1.y == o2.y  ? o1.x - o2.x : o1.y - o2.y : o1.d - o2.d);
	static int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};
	static void input() {
		N = sc.nextInt();
		map = new int[N][N];
		visit = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9) {
					sy = i;
					sx = j;
					map[i][j] = 0;
				}
			}
		}

	}

	static void pro() {
		// 상어가 물고기를 잡아 먹을 때마다 다시 bfs를 돌리면서 먹을 수 있는 물고기 찾기
		sSize = 2;
		curEatCnt = 0; // 현재 먹은 물고기의 개수 -> 현재 사이즈 만큼의 물고기를 먹은 경우 sSize++하고 curEatCnt를 0으로 초기화
		while(true) {
			visit = new boolean[N][N];
			fish.clear();
			int time = bfs(sy,sx);
			if(time == 0)break;
			ans += time;
		}

		System.out.println(ans);
	}

	static int bfs(int y,int x) {
		Queue<Point> Q = new ArrayDeque<>();
		Q.offer(new Point(y,x,0));
		visit[y][x] = true;
		int time = 0;
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			for(int[] adj : dir) {
				int ny = p.y + adj[0];
				int nx = p.x + adj[1];
				if(ny < 0 || nx < 0 || ny>=N || nx>=N || visit[ny][nx])continue;
				if(map[ny][nx] > sSize)continue;
				Q.offer(new Point(ny,nx,p.d + 1));
				visit[ny][nx] = true;
				if(map[ny][nx] != 0 && map[ny][nx] < sSize) {
					// 먹을 수 있는 생선
					fish.offer(new Point(ny,nx,p.d+1));
				}
			}
		}
		if(!fish.isEmpty()) {
			// 먹을 생선이 존재
			Point f = fish.poll();
			curEatCnt++;
			time = f.d;
			map[f.y][f.x] = 0;
			map[sy][sx] = 0;
			sy = f.y;
			sx = f.x;
			if(curEatCnt == sSize) {
				curEatCnt = 0;
				sSize++;
			}

		}
		return time;
	}

	static class Point{
		int y,x,d;
		public Point(int y,int x, int d) {
			this.y=y;
			this.x=x;
			this.d=d;
		}
	}

	public static void main(String[] args) {
		input();
		pro();
	}

	static class FastReader{
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) throws FileNotFoundException{
			br = new BufferedReader(new FileReader(new File(s)));
		}

		String next() {
			if(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}catch(IOException e) {
					e.printStackTrace();
				}
			}

			return st.nextToken();
		}

		Integer nextInt() {
			return Integer.parseInt(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			}catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
