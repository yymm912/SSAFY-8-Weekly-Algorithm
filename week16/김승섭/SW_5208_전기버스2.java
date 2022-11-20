import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, min;
	static int[] M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			N = Integer.parseInt(st.nextToken()); // 정류장 개수
			M = new int[N];						  // 충전지 배열
			M[0] = 0;
			for (int i = 1; i <= N-1; i++) {
				M[i] = Integer.parseInt(st.nextToken());
			}
			
			min = 1000;	// 최소 교환 횟수
			
//			dfs(1,M[1], 0);	
			dfs2(1, -1);
			System.out.println("#"+t+" "+min);
		}
	}
	
//	private static void dfs(int idx, int dis, int cnt) { // 현재 위치, 현재 위치에서 최대로 갈 수 있는 범위, 현재까지 교환 횟수
//		
//		
//		for (int i = M[idx]; i > 0; i--) {
//			if(cnt > min) continue; // 가지치기
//			
//			// 최대로 갈 수 있는 범위의 가장 멀리서부터
//			if(idx + i >= N) {				// 그 범위가 도착지를 충분히 넘는다면
//				min = Math.min(cnt, min);	// 최소 교환 횟수를 갱신
//				continue;
//			}
//
//			if(cnt < min) {					// 최소 횟수보다 작으면
//				dfs(idx + i, dis + M[idx+i], cnt+1);
//			}
//		}
//	}
	
	private static void dfs2(int cur, int cnt) {
		if(cur >= N) {
			min = Math.min(min, cnt);
			return;
		}
		
		if(min <= cnt) {
			return;
		}
		
		for(int i = 1; i <= M[cur]; i++) {
			dfs2(cur+i, cnt+1);
		}
		
	}
}
