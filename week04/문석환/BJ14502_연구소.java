package BOJ.그래프탐색.연구소_14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 번호 : 14502
// 난이도 : 골드 4
// 제목 : 연구소
// https://www.acmicpc.net/problem/14502
// 0인 공간을 담는 배열을 만들고 해당 idx에 대한 벽을 세울 조합 3개를 만들 때마다 bfs 돌리기

public class Main {
	static int N,M,ans = Integer.MIN_VALUE;
	static int[][] map;
	static List<Point> zeroSpace;
	static Point[] tgt;
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		zeroSpace = new ArrayList<>();
		map = new int[N][M];
		tgt = new Point[3];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					zeroSpace.add(new Point(i,j));
				}
			}
		}

		// 조합으로 3개 뽑아 내기
		comb(0,0);

		System.out.println(ans);
	}

	static void comb(int idx,int start) {
		if(idx == 3) {
			// complete code
			// 3개의 공간에 벽을 세웠으므로 해당 맵으로 bfs 돌리기
			int[][] newMap = new int[N][M];
			for(int i=0;i<N;i++) {
				newMap[i] = map[i].clone();
			}
			for(int i=0;i<3;i++) {
				newMap[tgt[i].y][tgt[i].x] = 1;
			}
			int result = bfs(newMap);
			ans = Math.max(ans, result);
			return;
		}

		for(int i=start;i<zeroSpace.size();i++) {
			tgt[idx] = zeroSpace.get(i);
			comb(idx+1,i+1);
		}
	}

	static int bfs(int[][] nm) {
		Queue<Point> Q = new ArrayDeque<>();

		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 2) {
					Q.offer(new Point(i,j));
				}
			}
		}

		while(!Q.isEmpty()) {
			Point np = Q.poll();
			for(int[] adj : dir) {
				int ny = np.y + adj[0];
				int nx = np.x + adj[1];
				if(ny < 0 || nx < 0 || ny >= N || nx >=M)continue;
				if(nm[ny][nx] != 0)continue;
				nm[ny][nx] = 2;
				Q.offer(new Point(ny,nx));
			}
		}

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(nm[i][j] == 0) {
					sum++;
				}
			}
		}
		return sum;
	}

	static class Point{
		int y,x;
		public Point(int y,int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + "]";
		}
	}


}
