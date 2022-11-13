import java.io.*;
import java.util.*;

class Main{
	static int V, E;
	static List<int[]>[] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V+1];
		for(int i=0; i<V+1; i++) graph[i] = new ArrayList<>();
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new int[] {b, c});
		}
		/*
		 * dfs로 사이클 확인 -> visit체크
		 * 사이클 없으면 visit안한 곳에서 다시 확인
		 * 사이클 있으면 최소값 갱신
		 */
		for(int startn=1; startn<=V; startn++) {
			int[] wsum = new int[V+1];
			boolean[] visit = new boolean[V+1];
			dfs(startn, 0, wsum, visit);
		}
		if(minW == 987654321) System.out.println(-1);
		else System.out.println(minW);
		
	}
	static int[] totalVisit;
	static int minW = 987654321;
	static void dfs(int node, int w, int[] wsum, boolean[] visit) { // wsum : 현재 노드에 도달하기까지의 가중치 합
		if(w > minW) return;
		if(visit[node] == true) { // 사이클
			minW = Math.min(minW, w-wsum[node]);
			return;
		}
		
		wsum[node] = w;
		visit[node] = true;
		
		for(int[] nxt : graph[node]) {
			dfs(nxt[0], w+nxt[1], wsum, visit);
		}
		visit[node] = false;
		wsum[node] = 0;
		
		return;
	}
}