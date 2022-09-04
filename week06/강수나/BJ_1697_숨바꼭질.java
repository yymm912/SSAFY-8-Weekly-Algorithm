

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리초과
 * 중복탐색을 하나? 왔던 곳 다시가나
 * 왔던 곳을 다시간다. 지금 내가 a인데 다시 돌아돌아서 a로 오는 경우가 발생한다.
 * */
public class BOJ_1697_숨바꼭질 {

	static int N, K;
	static int[] map;
	static int[] dir = {-1, 1, 2};
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		//입력 끝
		
		/*
		if (N > K) {
			int sec = 0;
			while (true) {
				if (N == K) {
					System.out.println(sec);
					return;
				}
				N--;
				sec++;
			}
		}
		else bfs();
		*/
		bfs();
	}
	
	static void bfs() {
		Queue<Node> queue = new ArrayDeque<> ();
		queue.offer(new Node(N, 0));
		visited[N] = true;
		
		int np = 0;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			//check
			if (node.pos == K) { 
				System.out.println(node.d);
				break;
			}
			
			for (int d = 0; d < 3; d++) {
				if (d == 2) np = node.pos*dir[d];
				else np = node.pos+dir[d];
				if (np > 10_0000 || np < 0 || visited[np]) continue; //범위체크
				queue.offer(new Node(np, node.d+1));
				visited[np] = true;
			}
		}
	}
	
	static class Node {
		int pos, d;
		public Node(int pos, int d) {
			this.pos = pos;
			this.d = d;
		}
	}
}
