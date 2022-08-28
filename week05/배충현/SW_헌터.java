package ssafy;

import java.io.*;
import java.util.*;

// 방문조합 --> 몬스터 이후 집
// 몬스터는 양수, 집은 음수 --> 리스트에 넣고 오름/내림차순 정렬?
// 몬스터를 잡으면 방문 리스트에 집이 들어간다.
// 헌터는 0, 0 시작
public class SW_헌터 {
	
	static StringBuilder sb = new StringBuilder();
	static int T, N, ans;
	static int[][] input;
	static List<int[]> visit, hl;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			visit = new ArrayList<>();
			hl = new ArrayList<>();
			input = new int[N][N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					input[i][j] = Integer.parseInt(stk.nextToken());
					if (input[i][j] > 0) visit.add(new int[] {i, j, input[i][j]});
					if (input[i][j] < 0) hl.add(new int[] {i, j, input[i][j]});
				}
			}
			Collections.sort(hl, (h1, h2) -> h2[2]-h1[2]);	// 집을 내림차순 (몬스터 번호 순으로 정렬)
			
			solve(visit, 0, 0, 0);
			
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		
		System.out.print(sb);
		
	}
	
	static void solve(List<int[]> visit, int hy, int hx, int sum) {
		if (visit.isEmpty()) {
			ans = Math.min(ans, sum);
			return;
		}
		if (sum > ans) return;
		
		List<int[]> tv = new ArrayList<>();
		for (int i = 0; i < visit.size(); i++) {
			tv.clear();
			tv.addAll(visit);
			int[] tmp = tv.remove(i);
			if (tmp[2] > 0) tv.add(hl.get(tmp[2]-1));
			solve(tv, tmp[0], tmp[1], sum+getDist(hy, hx, tmp[0], tmp[1]));
		}
	}
	
	static int getDist(int hy, int hx, int ty, int tx) {
		return Math.abs(hy - ty) + Math.abs(hx - tx);
	}
}
