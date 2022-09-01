package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5014 {
	static int F, S, G, U, D;
	static boolean[] visit = new boolean[1_000_001];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(stk.nextToken());
		S = Integer.parseInt(stk.nextToken());
		G = Integer.parseInt(stk.nextToken());
		U = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken());
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {S, 0});
		
		// bfs
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			// 아파트 층을 벗어나면 무시
			if(now[0] > F || now[0] < 1) continue;
			
			// 이미 방문했어도 무시
			if(visit[now[0]]) continue;
			visit[now[0]] = true;

			// 목표지점에 다다르면 출력하고 프로그램 종료
			if(now[0] == G) {
				System.out.println(now[1]);
				return;
			}
			
			
			q.add(new int[] {now[0] + U, now[1] + 1});
			q.add(new int[] {now[0] - D, now[1] + 1});
		}
		
		System.out.println("use the stairs");
	}
}
