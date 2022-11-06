package a22_11_06;

import java.io.*;
import java.util.*;

public class BOJ_2234_성곽 {

	static int M, N, ans1, ans2, ans3;
	static int[][] map, wall;
	static List<List<Integer>> side = new ArrayList<> (); // 방 별 인접한 방 번호 저장
	static List<Integer> area = new ArrayList<> (); // 방 별 넓이 저장 
	
	static int[] dy= {0,-1,0,1}; //서북동남
	static int[] dx= {-1,0,1,0}; //서북동남
	static int[] dir= {1,2,4,8};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		wall = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				wall[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		area.add(0); //0 dummy
		side.add(new ArrayList<> ()); //0 dummy
		solution();
		System.out.println(ans1-1);
		System.out.println(ans2);
		System.out.println(ans3);
	}
	
	static void solution() {
		ans1 = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 0) {
					bfs(i, j, ans1++);
				}
			}
		}
		
//		System.out.println(area);
//		System.out.println(side);
		for (int n=1; n<ans1; n++) {
			for (Integer sideNum : side.get(n)) {
				int sum = area.get(n) + area.get(sideNum);
				ans3 = Math.max(ans3, sum);
			}
		}
	}
	
	static void bfs(int y, int x, int num) {
		int res = 0;
		List<Integer> tmp = new ArrayList<> ();
		
		Queue<Pos> queue = new ArrayDeque<> ();
		queue.offer(new Pos(y,x));
		map[y][x] = num;
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			res++;
			for (int d=0; d<4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if (ny<0 || ny>=N || nx<0 || nx>=M) continue;
				if ( map[ny][nx] != 0 && map[ny][nx] != num ) {
					tmp.add(map[ny][nx]); //인접한 방 번호 추가
				}
				if ( map[ny][nx] == 0 && (wall[cur.y][cur.x] & dir[d]) == 0 ) {
					map[ny][nx] = num;
					queue.offer(new Pos(ny, nx));
				}
			}
		}
		ans2 = Math.max(ans2, res);
		side.add(tmp); // 인접한 방 번호 저장
		area.add(res); // 방 별 넓이
	}
	
	static class Pos {
		int y, x;
		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
