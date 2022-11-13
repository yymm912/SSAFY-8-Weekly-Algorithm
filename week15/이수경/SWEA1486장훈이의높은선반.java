package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1486장훈이의높은선반 {

	static int T, N, B;
	static int inp[];
	static int min, ans;
	static int src[];
	static boolean select[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			min = Integer.MAX_VALUE;
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken()); // 선반의 높이 

			st = new StringTokenizer(br.readLine());
			inp = new int[N];
			select = new boolean[N];
			for (int i = 0; i < N; i++) {
				inp[i] = Integer.parseInt(st.nextToken());
			}
			
			// 탑의 높이가 B이상인 것 중에서 가장 작은 출력
			// dfs로 모든 합이 될 수 있는 경우의 수를 만든다.(완탐) 그중에서 B이상인 제일 작은 것 출력  
			
			subset(0);
			
			System.out.println("#" + t + " " + (min-B));
		} // testcase

	}
	static void subset(int srcIdx) {
		
		if(srcIdx == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if(select[i])sum += inp[i];
			}
			if(sum >= B) min = Math.min(min, sum);
			
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1);
		select[srcIdx] = false;
		subset(srcIdx + 1);
		
	}

}
