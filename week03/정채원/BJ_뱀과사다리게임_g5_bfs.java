import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static int[] board = new int[101]; // 0이 아닌 숫자 -> 그 쪽으로 이동함
	static int minRollCnt = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) { // 사다리
			st = new StringTokenizer(br.readLine());		
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y;
		}
		for(int i=0; i<M; i++) { // 뱀
			st = new StringTokenizer(br.readLine());	
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y;
		}
		
		/**
		 * 고려할 것
		 * 1. 다시 뱀으로 거슬러올라갈 수도 있음
		 * 2. 한 곳에 사다리 끝이 여러개 있을 수 있
		 * 3. bfs로 찾으면, 이미 간 곳은 다시 안가도록 visit 체크
		 * */
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Node> queue = new ArrayDeque<Node>();
		boolean[] visit = new boolean[101];
		queue.add(new Node(1, 0)); // 1번칸에서 출발, 주사위 횟수
		visit[1] = true;
		
		int minCnt = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(cur.n == 100) {
				minCnt = Math.min(cur.cnt, minCnt);
				continue;
			}
			
			for(int i=1; i<=6; i++) {
				int nxt = cur.n + i;
				if(nxt > 100) break;
				if(visit[nxt]) continue;
				// 사다리, 뱀의 시작 지점에 visit 체크를 함.
				// nx == 100인 경우는 visit에 체크하면 X -> 도착지점이 걸러지기 떄문에
				if(nxt != 100) visit[nxt] = true; 
				if(board[nxt] > 0) nxt = board[nxt];
				// 한 칸에 사다리 또는 뱀의 출발 & 도착 지점이 같이 있을 수도 있음. 모호함.
				queue.add(new Node(nxt, cur.cnt+1));
			}
		}
		return minCnt;
	}
}
class Node{
	int n, cnt;
	public Node(int n, int cnt) {
		this.n = n;
		this.cnt = cnt;
	}
}