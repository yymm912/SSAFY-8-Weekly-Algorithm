package baekjoon.스타트링크_5014;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int F, S, G, U, D, ans;
	static boolean[] visit;
	static Queue<Node> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		visit = new boolean[F+1]; // 0 dummy
		
		q.offer(new Node(S, 0));
		visit[S] = true;
		find();
		
		System.out.println(ans == Integer.MAX_VALUE ? "use the stairs" : ans);

	}
	
	static void find() {
		if(S < G && U == 0) {
			return;
		}
		if( S > G && D == 0) {
			return;
		}
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			if(node.f == G) {
				ans = Math.min(ans, node.cnt);
				return;
			}
			if(node.f + U <= F && !visit[node.f+U]) {
				visit[node.f+U] = true;
				q.offer(new Node(node.f+U, node.cnt+1));
			}
			if(node.f - D > 0 && !visit[node.f-D]) {
				visit[node.f-D] = true;
				q.offer(new Node(node.f-D, node.cnt+1));
			}
		}
		
	}
	
	static class Node{
		int f;
		int cnt;
		
		public Node(int f, int cnt) {
			this.f = f;
			this.cnt = cnt;
		}
	}

}
