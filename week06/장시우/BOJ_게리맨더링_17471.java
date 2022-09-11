package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_게리맨더링_17471 {
	
	
	static int N, ans, sum1, sum2;
	// 부분집합을 위한 src와 select
	static int[] src;
	static boolean[] select;
	
	static boolean[] checked;
	
	static int[] populations;
	static List<Integer>[] cities;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		src = new int[N];
		select = new boolean[N];
		
		checked = new boolean[N + 1];
		
		populations = new int[N + 1];
		cities = new ArrayList[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			populations[i + 1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			cities[i + 1] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				cities[i + 1].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < N; i++) {
			src[i] = i + 1;
		}
		
		ans = Integer.MAX_VALUE;
		subset(0);
		
		if (ans == Integer.MAX_VALUE) {
			ans = - 1;
		}
		
		System.out.println(ans);
	}
	
	static void check() {
		Arrays.fill(checked, false);
		int trueCnt = 0;
		int falseCnt = 0;
		sum1 = 0;
		sum2 = 0;
		
		for (int i = 0; i < select.length; i++) {
			// true에 대한 bfs
			if (select[i] && !checked[src[i]]) {
				dfs_true(src[i]);
				trueCnt++;
			}
						
			if (!select[i] && !checked[src[i]]) {
				dfs_false(src[i]);
				falseCnt++;
			}
		}
				
		if (trueCnt == 1 && falseCnt == 1) {
			ans = Math.min(ans, Math.abs(sum1 - sum2));
		} else {
			ans = Math.min(ans, Integer.MAX_VALUE);
		}
	}
	
	static void dfs_true(int i) {
		sum1 += populations[i];
		checked[i] = true;
		for (int j = 0; j < cities[i].size(); j++) {
			if (!checked[cities[i].get(j)] && select[cities[i].get(j) - 1]) {
				dfs_true(cities[i].get(j));
			}
		}
		
		return;
	}
	
	static void dfs_false(int i) {
		sum2 += populations[i];
		checked[i] = true;
		for (int j = 0; j < cities[i].size(); j++) {
			if (!checked[cities[i].get(j)] && !select[cities[i].get(j) - 1]) {
				dfs_false(cities[i].get(j));
			}
		}
		
		return;
	}
	
	static void subset(int srcIdx) {
		if (srcIdx == src.length) {
			check();
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1);
		
		select[srcIdx] = false;
		subset(srcIdx + 1);
	}
}
