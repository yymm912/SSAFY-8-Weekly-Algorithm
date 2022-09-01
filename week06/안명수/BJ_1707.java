package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1707 {
	static int V, E, K;
	static char[] nodeInfo;
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> vList = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		while(K > 0) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			V = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());
			
			nodeInfo = new char[V + 1];
			visit = new boolean[V + 1];
			Arrays.fill(visit, false);

			vList.clear();
			for(int i = 0; i <= V; i++)
				vList.add(new ArrayList<>());
			
			for(int i = 0; i < E; i++) {
				stk = new StringTokenizer(br.readLine());
				int here = Integer.parseInt(stk.nextToken());
				int there = Integer.parseInt(stk.nextToken());
				
				vList.get(here).add(there);
				vList.get(there).add(here);
			}
			
			String ANS = "YES";
			for(int i = 1; i <= V; i++) {
				if(visit[i]) continue;
				if(!dfs(i, 'A')) {
					ANS = "NO";
					break;
				}
			}
			
			System.out.println(ANS);
			K--;
		}
	}
	
	static boolean dfs(int now, char team) {
		nodeInfo[now] = team;
		visit[now] = true;

		for(int next : vList.get(now)) {
			if(visit[next] && nodeInfo[next] == team) return false;
			if(visit[next]) continue;
			
			char thereTeam = team == 'A' ? 'B' : 'A';
			if(!dfs(next, thereTeam)) return false;
		}
		
		return true;
	}
}
