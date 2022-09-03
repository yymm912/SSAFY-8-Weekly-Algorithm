package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17471_게리맨더링 {
	
	static int N, ans, all;
	static ArrayList<ArrayList<Integer>> List;
	static Queue<Integer> que;
	static boolean[] visited, check;
	static int[] tgt,src;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		all = 0;
		List = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		src = new int[N+1];
		List.add(new ArrayList<>());
		que = new ArrayDeque<>();
		
		for(int i=1; i<N+1; i++) {
			src[i] = Integer.parseInt(st.nextToken());
			List.add(new ArrayList<>());
			all += src[i];
		}
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) List.get(i).add(Integer.parseInt(st.nextToken()));
		}
		
		ans = all;
		
		for(int i=1; i<N; i++) {
			visited = new boolean[N+1];
			tgt = new int[i];
			comb(1, 0, i);
			
		}

		System.out.print( ans == all ? -1 : ans);
		
	}
	
	
	static void comb(int srcIdx, int tgtIdx, int finalDepth) {
		
		if(tgtIdx==finalDepth) {
			check(tgtIdx);
			return;
		}
		
		if(srcIdx==N+1) return;
		
		tgt[tgtIdx] = srcIdx;
		visited[srcIdx] = true;
		comb(srcIdx+1, tgtIdx+1, finalDepth);
		visited[srcIdx] = false;
		comb(srcIdx+1, tgtIdx, finalDepth);		
	}
	
	static void check(int depth) {
		
		int sum = 0, sum2=0;
		check = new boolean[N+1];
		for(int i=1; i<N+1; i++) {
			if(visited[i]) {
				check[i] = true;
				sum +=src[i];
			}
			else sum2 += src[i];
		}
		
		if(bfs(depth)) return;
		
		int cnt=0;
		for(int i=1; i<=N; i++) { // 고립 체크
			if(!check[i]) { // 아직 안 가본 노드들 중에서
				cnt++;
				if(cnt>=2) return;
				isolated(i);
			}
		}
		if(cnt==0) return;
		
		ans = Math.min(ans, Math.abs(sum-sum2));
		return;
		
	}
	
	static boolean bfs(int depth) { // 연결 체크
		que.clear();
		que.add(tgt[0]);
		boolean tmp[] = new boolean[N+1];
		tmp[tgt[0]] = true;
		int cnt = 0;
		
		while(!que.isEmpty()) {
			int val = que.poll();
			cnt++;
			for(int b : List.get(val)) {
				if(visited[b] && !tmp[b]) {
					tmp[b] = true;
					que.add(b); 
				}
			}
			
		}
		if(cnt==depth) return false;
		return true;
	}
	
	static void isolated(int i) { // 연결 체크
		que.clear();
		que.add(i);
		check[i] = true;
		
		while(!que.isEmpty()) {
			int val = que.poll();
			for(int nextNode : List.get(val)) {
				if(!check[nextNode]) {
					check[nextNode] = true;
					que.add(nextNode); 
				}
			}
			
		}
	}
	
}