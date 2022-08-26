package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1697 {
	static int N, K;
	static boolean[] visited = new boolean[100_001];
	static PriorityQueue<ITEM> dq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		dq.add(new ITEM(N, 0));
		while(!dq.isEmpty()) {
			ITEM now = dq.poll();
			if(now.num < 0 || now.num > 100_000 || visited[now.num])continue;
			visited[now.num] = true;
			
			if(now.num == K) {
				System.out.println(now.sec);
				break;
			}
			
			dq.add(new ITEM(now.num * 2, now.sec + 1));
			dq.add(new ITEM(now.num + 1, now.sec + 1));
			dq.add(new ITEM(now.num - 1, now.sec + 1));
		}
	}
	
	static class ITEM implements Comparable<ITEM>{
		int num;
		int sec;
		public ITEM(int num, int sec) {
			this.num = num;
			this.sec = sec;
		}
		
		@Override
		public int compareTo(ITEM o) {
			return this.sec - o.sec;
		}	
	}
}
