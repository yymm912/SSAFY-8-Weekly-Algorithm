package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_16928 {
	static int N, M;
	static int[] game = new int[101];
	static boolean[] visited = new boolean[101];
	
	// 이동횟수를 정렬기준으로 삼는 우선순위 큐
	static PriorityQueue<Info> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		// 게임판 세팅 
		// 기본값을 0으로 모두 사다리나 뱀이 설치되지 않았다고 가정
		Arrays.fill(game, 0);
		Arrays.fill(visited, false);
		
		for(int i = 0, here, there; i < N + M; i++) {
			stk = new StringTokenizer(br.readLine());
			here = Integer.parseInt(stk.nextToken());
			there = Integer.parseInt(stk.nextToken());
			
			// 사다리 혹은 뱀이 어디와 이어져있는지 표기
			game[here] = there;
		}
		
		
		pq.add(new Info(1, 0));
		while(!pq.isEmpty()) {
			Info now = pq.poll();
			
			// 보드를 벗어났거나 이미 방문했던 위치라면 컨티뉴
			if(now.pos > 100 || visited[now.pos]) continue;
			
			// 현재 위치에 방문표시
			visited[now.pos] = true;
			
			// 목적지에 도착했으면 출력
			if(now.pos == 100) {
				System.out.println(now.cnt);
				break;
			}
			
			// 만약 현재위치가 사다리나 뱀의 시작점이면 끝점으로 이동
			if(game[now.pos] != 0) now.pos = game[now.pos];
			
			// 주사위를 던져서 나오는 모든 경우를 체크
			for(int i = 1; i <= 6; i++) {
				
				// 이미 방문했던 지점이면 패스
				if(now.pos + i <= 100 && visited[now.pos + i]) continue;
				pq.add(new Info(now.pos + i, now.cnt + 1));
			}
			
		}
		
	}
	
	static class Info implements Comparable<Info>{
		int pos;
		int cnt;
		public Info(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Info o) {
			return cnt - o.cnt;
		}
	}
}
