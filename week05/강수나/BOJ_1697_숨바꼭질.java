package algo_study._8월4주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697_숨바꼭질2 {

	static int[] dir = {1, -1, 2};
	static int[] map;
	static int N, sister, ans, min;
	static boolean[] visited;
	static Queue<Node> queue = new ArrayDeque<> ();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		sister = Integer.parseInt(st.nextToken());
		map = new int[100_000+1];
		visited = new boolean[100_000+1];
		
		
		if (N >= sister) ans = N - sister; //N >= sister 경우 갈 수 있는 방법은 -1밖에 없다
		else bfs(N);
		System.out.println(ans);
		
	}
	
	static void bfs(int start) {
		queue.add(new Node(start, 0));
		visited[start] = true;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int next = 0;
			for (int d = 0; d < 3; d++) { //델타 사용
				if (d == 2)  next = node.x * 2;
				else next = node.x + dir[d];
				if (next < 0 || next > 100000 || visited[next]) continue;
				if (next == sister) {
					ans = node.depth + 1; //여기시 +1 (초는 1초부터, 깊이는 0부터 시작하므로)
					return;
				}
				visited[next] = true;
				queue.add(new Node(next, node.depth+1)); //큐에 깊이+1을 저장
			}
		}
	}
	
	static class Node {
		int x, depth;
		public Node(int x, int depth) { //시간 == 깊이
			this.x = x;
			this.depth = depth;
		}
	}
}
