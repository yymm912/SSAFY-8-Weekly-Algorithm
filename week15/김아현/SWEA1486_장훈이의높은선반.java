import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1486_장훈이의높은선반 {
	
	static int T, N, B, res;
	static int[] hights;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			hights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				hights[i] = Integer.parseInt(st.nextToken());
			} // 입력 끝
			
			res = Integer.MAX_VALUE;
			dfs(0, 0);
			
			System.out.println("#" + t + " " + (res-B));
		}
	}

	static void dfs(int idx, int sum) {
		
		if(sum >= B) {
			res = Math.min(res, sum);
			return;
		}
		
		if(sum > res) return;
		if(idx == N) return;
		
		bfs(idx + 1, sum + hights[idx]); // 포함하거나
		bfs(idx + 1, sum); // 포함하지 않거나
	}
}
