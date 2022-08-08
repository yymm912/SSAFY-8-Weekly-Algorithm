package algorithm.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14889 {
	static int N, minValue = Integer.MAX_VALUE;
	static int[][] arr, score;
	static boolean[] selected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		

		// 대각선을 기준으로 우측상단 삼각형에 조합별 모든 점수 저장
		score = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) 
			for(int j = i + 1; j <= N; j++) 
				score[i][j] = arr[i][j] + arr[j][i];
			
		
		
		// N/2 명을 구해서 그때의 점수차가 최소인 조합 구하기
		selected = new boolean[N + 1];
		solve(1, 0);
		
		System.out.println(minValue);
	}
	
	static void solve(int idx, int cnt) {
		// 기저조건 : cnt가 N / 2명인가?
		if(cnt == N / 2) {
			int Ateam, Bteam;
			Ateam = Bteam = 0;
			
			// 선택된 팀원은 A팀에 저장
			// 선택되지 못한 팀원은 B팀에 저장
			for(int i = 1; i <= N; i++) {
				for(int j = i + 1; j <= N; j++) {
					if(selected[i] && selected[j]) Ateam += score[i][j];
					else if(!selected[i] && !selected[j]) Bteam += score[i][j];
				}
			}
			
			minValue = Math.min(minValue, Math.abs(Ateam - Bteam));
			return;
		}
		
		for(int i = idx; i <= N; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			solve(i + 1, cnt + 1);
			selected[i] = false;
		}
	}
}
