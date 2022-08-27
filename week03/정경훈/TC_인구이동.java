package baekjoon.인구이동_16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,L,R,ans;
	static int[][] A;
	static Queue<Node> q = new ArrayDeque<>();
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][] visit;
	static List<Node> list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		ans = 0;
		
		A = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			visit =  new boolean[N][N];
			boolean ok = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visit[i][j]) continue;
					list = new ArrayList<>();
					q.add(new Node(i,j));
					list.add(new Node(i,j));
					visit[i][j] = true;
					int sum = bfs();
					
					// 인구가 변동된 상태로 다른 연합이랑 비교하는 경우가 발생
					if(list.size() > 1) {
						int avg = sum/list.size();
						for(Node n : list) {
							A[n.y][n.x] = avg;
						}
						ok = true;
					}
				}
			}
			if(!ok) break;
			ans++;
		}
		System.out.println(ans);
	}
	
	static int bfs() {
		while(!q.isEmpty()) {
			Node node = q.poll();
			int y = node.y;
			int x = node.x;
			
			for(int i=0;i<4;i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if(ny<0 || nx<0 || ny>=N || nx>=N || visit[ny][nx]) continue;
				int diff = Math.abs(A[y][x] - A[ny][nx]);
				if(diff>=L && diff<=R) {
					visit[ny][nx] = true;
					list.add(new Node(ny,nx));
					q.add(new Node(ny, nx));
				}
			}
		}
		int sum = 0;
		for(Node n : list) {
			sum += A[n.y][n.x];
		}
		return sum;
	}
	
	static class Node{
		int x;
		int y;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
