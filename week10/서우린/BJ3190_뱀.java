package _10주차;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ3190_뱀 {
	static int N,K,L;
	static boolean [][] board,visited;
	static int [][] d = {{-1,0},{0,1},{1,0},{0,-1}};
	static int dir = 1;
	static int [] x;
	static Deque<Point> snake = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new boolean[N][N];
		visited = new boolean[N][N];
		x = new int[10001];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
		}
		L = Integer.parseInt(br.readLine());
		
		for(int i = 0;i <L;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			char b = st.nextToken().charAt(0);
			if(b == 'D')
				x[a] = 1;
			else x[a] = -1;
		}
		snake.add(new Point(0,0));
		visited[0][0] = true;
		int ans = 0;
		while(true) {
			dir = (dir + x[ans] + 4) % 4;
			ans++;
			Point head = snake.peekFirst();
			int nr = head.y + d[dir][0];
			int nc = head.x + d[dir][1];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N)
				break;
			if(visited[nr][nc])
				break;
			if(board[nr][nc]) {
				board[nr][nc] = false;
			}else {
				Point tale = snake.pollLast();
				visited[tale.y][tale.x] = false;
			}
			visited[nr][nc] = true;
			snake.addFirst(new Point(nc,nr));
		}
		
		System.out.println(ans);

	}

}
