package baekjoon.뱀과사다리게임_16928;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans;
	static int[] board = new int[101];
	static Queue<Node> q = new LinkedList<>();
	static boolean[] visit = new boolean[101];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());	
		
		for(int i=0;i<N+M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y; // 사다리와 뱀 추가
		}
		
		q.offer(new Node(1,0));
		visit[1] = true;
		bfs();
		
		System.out.println(ans);
		
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			if(node.no == 100) {
				ans = node.cnt;
				break;
			}
			
			for(int i=1;i<=6;i++) {
				int nextNo = node.no+i;
				if(nextNo>100) continue;
				if(!visit[nextNo]) {
					visit[nextNo] = true;
					if(board[nextNo]!=0) {
						if (!visit[board[nextNo]]) {
							visit[board[nextNo]] = true;
							q.offer(new Node(board[nextNo],node.cnt+1));
						}
					}else {
						q.offer(new Node(nextNo, node.cnt+1));
					}
				}
			}
		}
	}
	
	static class Node{
		int no;
		int cnt;
		
		public Node(int no, int cnt) {
			super();
			this.no = no;
			this.cnt = cnt;
		}
		
	}
}
