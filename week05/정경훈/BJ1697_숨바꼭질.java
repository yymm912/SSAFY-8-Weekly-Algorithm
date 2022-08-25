package baekjoon.숨바꼭질_1697;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K, ans;
	static boolean[] visit = new boolean[100001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		bfs();
		
		System.out.println(ans);
	}
	
	static void bfs() {
		visit[N] = true;
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(N,0));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			if(n.x == K) {
				ans = Math.min(ans, n.t);
				continue;
			}
			if(n.x + 1 <=100000 && !visit[n.x + 1]) {
				visit[n.x + 1] = true;
				q.offer(new Node(n.x+1, n.t+1));
			}
			if(n.x - 1 >= 0 && !visit[n.x - 1]) {
				visit[n.x - 1] = true;
				q.offer(new Node(n.x-1, n.t+1));
			}
			if(n.x * 2 <=100000 && !visit[n.x * 2]) {
				visit[n.x * 2] = true;
				q.offer(new Node(n.x*2, n.t+1));
			}
		}
	}
	
	static class Node{
		int x, t;
		
		public Node(int x, int t) {
			this.x = x;
			this.t = t;
		}
	}

}
