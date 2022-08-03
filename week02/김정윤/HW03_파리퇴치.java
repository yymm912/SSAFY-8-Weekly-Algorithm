package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_파리퇴치_2001 {
	private static int T, N, M, sum, max;
	private static int[][] fly;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트 케이스
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 파리 영역
			M = Integer.parseInt(st.nextToken()); // 파리채 영역
			
			fly = new int[N+1][N+1];
			
			// 가로 & 세로 구간 합 설정
			setFlyArray(br);
			
			// 최댓값 초기화
			max = 0;
			sb.append("#").append(tc).append(" ").append(getMaxCaughtFly()).append("\n");
		}
		// 결과 출력
		System.out.println(sb);
	}
	
	// 최대로 잡을 수 있는 파리 개수
	private static int getMaxCaughtFly() {
		for (int i = 1; i <= N-M+1; i++) { // 파리채 영역
			for (int j = 1; j <= N-M+1; j++) { // 파리채 영역
				int fromX = i;
				int fromY = j;
				int toX = i+M-1;
				int toY = j+M-1;
				// toX, toY까지의 구간 합 - fromY줄 세로 합 - fromX줄 가로 합 + 중복으로 빼낸수
				int sum = fly[toX][toY] - fly[toX][fromY-1] - fly[fromX-1][toY] + fly[fromX-1][fromY-1];
				max = Math.max(max, sum);
			}
		}
		return max;
	}
	
	private static void setFlyArray(BufferedReader br) throws IOException {
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				fly[i][j] = fly[i-1][j] + fly[i][j-1] - fly[i-1][j-1] + Integer.parseInt(st.nextToken());
			}
		}
	}
}
