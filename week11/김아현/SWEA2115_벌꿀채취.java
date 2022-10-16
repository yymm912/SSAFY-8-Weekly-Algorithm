package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 1. 채집할 수 있는 모든 경우에 대한 최대 이익 미리 구하기 => subset
// 2. 일꾼 1이 채집할 벌통 선정
// 3. 일꾼 2가 채집할 수 있는 벌통의 경우 모두 따져보기 => 조합 (최댓값 갱신)

public class SWEA2115_벌꿀채취 {

	static int T, N, M, C, max;
	static int[][] map;
	static int[][] profit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());   // N*N 모양의 벌통
			M = Integer.parseInt(st.nextToken());	// 수확할 가로로 연속된 M개의 벌통
			C = Integer.parseInt(st.nextToken());	// 한 용기의 용량
			
			map = new int[N][N];
			profit = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = Integer.MIN_VALUE;
			
			solve();
			
			System.out.println("#" + t + " " + max);
		}
	}

	static void solve() {
		
		setMaxProfit();
		// 일꾼 1이 채취할 벌통 구하기
		for(int i=0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				// 일꾼 2가 채취할 벌통 구하기
				comb(i, j + M, 1, profit[i][j]);
			}
		}
	}
	
	static void comb(int x, int y, int cnt, int sum) {
		
		if(cnt == 2) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = x; i < N; i++) {
			for (int j = y; j <= N-M; j++) {
				comb(i, j, cnt + 1, sum + profit[i][j]);
			}
			y = 0;
		}
	}
	
	static void setMaxProfit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				subset(i, j, 0, 0, 0);
			}
		}
	}
	
	static void subset(int x, int y, int cnt, int sum, int total) {
		if(sum > C) return;
		
		if(cnt == M) {
			profit[x][y-M] = Math.max(profit[x][y-M], total);
			return;
		}
		
		// 이 꿀 채취해보기
		subset(x, y + 1, cnt + 1, sum + map[x][y], total + map[x][y] * map[x][y]);
		// 안할래
		subset(x, y + 1, cnt + 1, sum, total);
	}
}
