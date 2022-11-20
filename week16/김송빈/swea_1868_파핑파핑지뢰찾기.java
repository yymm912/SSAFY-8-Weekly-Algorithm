package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class swea_1868_파핑파핑지뢰찾기 {
	static int n;
	static char[][] map;
	static List<node> list;
	static List<int[]> vg;
	static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 }, dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static boolean[][] visited;
	static int ans = 0;

	static class node {
		int y, x, n;

		node(int y, int x, int n) {
			this.y = y;
			this.x = x;
			this.n = n;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/swea_1868_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			ans = 0;
			map = new char[n][n];
			visited = new boolean[n][n];
			list = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();
				
			
			}
			
			for(int i=0;i<n;i++) {
				for (int j = 0; j < n; j++){
					if (map[i][j] == '.') {
						if(!visited[i][j])check(i, j);
					}
				}
			}
			
			//System.out.println(list.size());
			Collections.sort(list, (l1, l2) -> l2.n - l1.n);

			for (int i = 0; i < list.size(); i++) {
				int y = list.get(i).y;
				int x = list.get(i).x;
				vg = new ArrayList<>();
				vg.add(new int[] { y, x });
				boolean fail = false;
				if(visited[y][x])continue;
				visited[y][x] = true;
				for (int d = 0; d < 8; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
						continue;
					}
					
					visited[ny][nx] = true;

				}

				ans++;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == '.' && !visited[i][j])
						ans++;
				}
			}

			System.out.println("#" + t + " " + ans);
		}

	}

//	static void check(int y, int x) {
//		
//		boolean flag = true;
//		int cnt = 0;
//		for (int i = 0; i < 8; i++) {
//			int ny = y + dy[i];
//			int nx = x + dx[i];
//			
//			if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
//				continue;
//			}
//			if (map[ny][nx] != '.') {
//				
//				flag = false;
//				break;
//			}
//			cnt++;
//		}
//		if (flag) {
//			//System.out.println(cnt);
//			//System.out.println(y+" "+x);
//			list.add(new node(y, x, cnt));
//		}
//	}
	static void check(int y,int x) {
		Queue<int[]>q=new ArrayDeque<int[]>();
		q.add(new int[] {y,x});
		visited[y][x]=true;
		while(!q.isEmpty()) {
			int []now=q.poll();
			vg.clear();
			boolean flag=true;
			for (int i = 0; i < 8; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
					continue;
				}
				if (map[ny][nx] != '.') {
					
					flag = false;
					break;
				}else {
					
				}
				
			}
			if (!flag) {
				
			}
		}
	}
}
