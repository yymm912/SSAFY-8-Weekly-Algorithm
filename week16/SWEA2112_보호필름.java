import java.io.*;
import java.util.*;

public class SWEA2112_보호필름 {
	static int T, D, W, K, ans;
	static int[][] film;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 보호필름 두께
			W = Integer.parseInt(st.nextToken()); // 가로 크기
			K = Integer.parseInt(st.nextToken()); // 합격 기준
			film = new int[D][W];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MAX_VALUE;
			Testing(0, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}
	static void Testing(int rowNum, int injectCnt) {
		if (isPass()) { // 합격 여부 통과시 최소 약품 투입 횟수 저장
			ans = Integer.min(ans, injectCnt);
			return;
		}
		if (rowNum == D) return; // 열 전체 검사 완료 시 종료
		if (injectCnt > W) return; // 약물 주입 횟수가 두께를 넘어갈 경우 종료
		
		int[] copy = new int[W]; // 복사본 생성
		
		for (int i = 0; i < W; i++) {
			copy[i] = film[rowNum][i];
		}
		Testing(rowNum+1, injectCnt); // 약품 투입이 없는 경우
		
		for (int i = 0; i < W; i++) {
			film[rowNum][i] = 0; // 특성 A로 변환
		}
		Testing(rowNum+1, injectCnt+1); // 약품 투입으로 A로 변환된 경우
		
		for (int i = 0; i < W; i++) {
			film[rowNum][i] = 1; // 특성 B로 변환
		}
		Testing(rowNum+1, injectCnt+1); // 약품 투입으로 B로 변환된 경우
		
		for (int i = 0; i < W; i++) {
			film[rowNum][i] = copy[i]; // 원복
		}
	}
	static boolean isPass() {
		for (int i = 0; i < W; i++) {
			int sum = 1;
			boolean isPass = false;
			for (int j = 1; j < D; j++) { // 세로줄 기준 합격여부 판단
				if (film[j-1][i] == film[j][i]) sum++; // 이전 행과 다음 행이 같은 특성인 경우
				else sum = 1; // 다른 특성인 경우 다시 count 시작
				
				if (sum == K) { // 합격 기준에 통과한 경우
					isPass = true; // 통과
					break;
				}
			}
			if (!isPass) return false; // 불합격 
		}
		return true; // 합격
	}
}
