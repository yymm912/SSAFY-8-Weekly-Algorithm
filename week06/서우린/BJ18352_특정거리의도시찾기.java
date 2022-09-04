package _6주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ18352_특정거리의도시찾기 {
	static int N,M,K,X;
	static List<Integer> [] adj;
	static int [] dist;
	static List<Integer> ans;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
        dist = new int[N+1];
        ans = new ArrayList<>();
        for(int i = 0;i<N+1;i++) adj[i] = new ArrayList<>();
        
        for(int i = 0;i<M;i++){
        	st = new StringTokenizer(br.readLine());
        	adj[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }
        bfs();
        Collections.sort(ans);
        if(ans.isEmpty()) System.out.print(-1);
        else
	        for(int i = 0;i<ans.size();i++)
	        	System.out.println(ans.get(i));
	}
	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(X);
		dist[X] = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(dist[cur] - 1 == K) ans.add(cur);
			
			for(int next : adj[cur]) {
				if(dist[next] == 0) {
					dist[next] = dist[cur] + 1;
					q.add(next);
				}
			}
		}
	}

}

