package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1916 {
	static int N, M;
	static int[] cost;
	static ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		
		for(int i = 0; i <= N; i++)
			list.add(new ArrayList<>());
		
		StringTokenizer stk;
		for(int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			int cost = Integer.parseInt(stk.nextToken());
			
			list.get(start).add(new int[] {end, cost});
		}
		
		stk = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(stk.nextToken());
		int e = Integer.parseInt(stk.nextToken());
		
		// cost값 오름차순 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[1] - o2[1]);
		
		for(int[] info : list.get(s)) 
			pq.add(info);
		
		
		// 정점 방문 시 코스트 저장
		cost = new int[N + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			// 현재 비용보다 기존 비용이 더 싸면 무시
			if(now[1] > cost[now[0]]) continue;
			cost[now[0]] = now[1];
		
			
			for(int[] info : list.get(now[0])) {
				// 현재 경로를 통한 비용이 기존 비용보다 저렴하면 갱신 후 진행
				if(cost[info[0]] > now[1] + info[1]) {
					pq.add(new int[] {info[0], now[1] + info[1]});
				}
			}
		}
		
		System.out.println(cost[e]);
	}
}
