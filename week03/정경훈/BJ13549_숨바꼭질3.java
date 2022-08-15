package baekjoon.숨바꼭질3_13549;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K, ans;
	static Queue<Node> q = new ArrayDeque<>();
	static boolean[] visit = new boolean[100001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		q.add(new Node(N,0));
		visit[N] = true;
		bfs();
		
		System.out.println(ans);
		
	}
	
	
	static void bfs() {
		while(!q.isEmpty()) {
			Node node = q.poll();
			int point = node.no;
			if(point == K) {
				ans = node.time;
				break;
			}
			if(point*2<=100000 && !visit[point*2]) {
				visit[point*2] = true;
				q.add(new Node(point*2,node.time));
			}
			if(point-1>=0 && !visit[point-1]) {
				visit[point-1] = true;
				q.add(new Node(point-1,node.time+1));
			}
			if(point+1<=100000 && !visit[point+1]) {
				visit[point+1] = true;
				q.add(new Node(point+1,node.time+1));
			}
		}
	}
	
	static class Node{
		int no;
		int time;
		
		public Node(int no, int time) {
			super();
			this.no = no;
			this.time = time;
		}
	}

}
