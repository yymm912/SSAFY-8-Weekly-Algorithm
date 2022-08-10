package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_13549 {
	static int N, K;
	
	// 방문배열
	static boolean[] visited = new boolean[100_001];
	
	// 이동시간을 정렬기준으로 삼는 우선순위 큐
	static PriorityQueue<ITEM> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		// 현재 지점에서 시작한다는 의미
		pq.add(new ITEM(N, 0));
		
		// pq가 빈다면 종료
		while(!pq.isEmpty()) {
			ITEM now = pq.poll();
			
			// 점의 위치를 벗어났거나 이미 방문했으면 패스
			if(now.num < 0 || now.num > 100_000 || visited[now.num])continue;
			visited[now.num] = true;
			
			// 현재 위치가 동생의 위치와 같으면 출력 후 종료
			if(now.num == K) {
				System.out.println(now.sec);
				break;
			}
			
			// 내가 갈 수 있는 모든 방법 체크
			// *2 순간이동, +1, -1
			pq.add(new ITEM(now.num * 2, now.sec));
			pq.add(new ITEM(now.num + 1, now.sec + 1));
			pq.add(new ITEM(now.num - 1, now.sec + 1));
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
