package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_넓이우선탐색2_24445 {
	
	static int N, M, R;
	static List<Integer> [] list;
	static boolean[] checked;
	static int[] ans;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		checked = new boolean[N + 1];
		ans = new int[N + 1];
		q = new ArrayDeque<>();
		
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			list[u].add(v);
			list[v].add(u);
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(list[i], Collections.reverseOrder());
		}
		
		bfs(R);
		
		for (int i = 1; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}
	
	static void bfs(int start) {
		int order = 1;
		q.add(start);
		checked[start] = true;
		ans[start] = order++;
		
		while (!q.isEmpty()) {
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				int current = q.poll();
				
				for (int j = 0; j < list[current].size(); j++) {
					if (!checked[list[current].get(j)]) {
						q.add(list[current].get(j));
						checked[list[current].get(j)] = true;
						ans[list[current].get(j)] = order++;
					}
				}
			}
		}
	}
}
