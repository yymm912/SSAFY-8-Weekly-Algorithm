package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17142_연구소3 {
	static int n, m;
	static int[][] lab;
	static List<node> virus;
	static int[] dy = { 1, 0, -1, 0 }, dx = { 0, 1, 0, -1 };
	static int[] tgt;
	static int[][] visited, visitedcpy;
	static int min = Integer.MAX_VALUE;
	static int cnt;
	static Queue<node> q = new ArrayDeque<>();

	static class node {
		int y, x;

		public node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		virus = new ArrayList<>();
		lab = new int[n][n];
		visited = new int[n][n];
		visitedcpy = new int[n][n];
		tgt = new int[m];
		cnt = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int x = Integer.parseInt(st.nextToken());
				lab[i][j] = x;
				if (x == 2) {
					virus.add(new node(i, j));
				} else if (x == 1) {
					visitedcpy[i][j] = visited[i][j] = 1;
				} else {
					cnt++;
					visitedcpy[i][j] = visited[i][j] = -1;
				}
			}
		}
		if (cnt == 0) {
			System.out.println(0);
			return;
		}
		comb(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void comb(int tgtidx, int srcidx) {
		if (tgtidx == m) {
			q.clear();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					visited[i][j] = visitedcpy[i][j];
			}
			for (int i = 0; i < m; i++) {
				int y = virus.get(tgt[i]).y;
				int x = virus.get(tgt[i]).x;
				q.offer(new node(y, x));
			}
			bfs();
			return;
		}

		for (int i = srcidx; i < virus.size(); i++) {
			tgt[tgtidx] = i;
			comb(tgtidx + 1, i + 1);
		}
	}

	static void bfs() {
		int ans = Integer.MAX_VALUE;
		Queue<node> q2 = new ArrayDeque<>();
		boolean flag = false;
		int qcnt=0;
		while (true) {
			while (!q.isEmpty()) {
				node nd = q.poll();
				for (int i = 0; i < 4; i++) {
					int ny = nd.y + dy[i];
					int nx = nd.x + dx[i];
					if (ny < 0 || nx < 0 || ny >= n || nx >= n)
						continue;
					
					boolean pass=false;
					for(int j=0;j<m;j++) {
						if(ny==virus.get(tgt[j]).y&&nx==virus.get(tgt[j]).x) {
							pass=true;
							break;
						}
					}
					
					if(pass)continue;
					if (visited[ny][nx] == -1||visited[ny][nx]==0) {
						if(visited[ny][nx]==-1)qcnt++;
						visited[ny][nx] = visited[nd.y][nd.x] + 1;
						ans = visited[ny][nx];
						q2.offer(new node(ny, nx));
					}
					
					if(qcnt==cnt) {
						min=Math.min(min, ans);
						return;
					}
				}
			}
			if(q2.isEmpty())break;
			q = new ArrayDeque<>(q2);
			q2.clear();
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == -1) {
					ans = Integer.MAX_VALUE;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		min = Math.min(min, ans);
	}
}
