package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952수영장2 {

	static int T;
	static int inp[] = new int[13];
	static int dp[] = new int[13];
	static int day, month, month3, year;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			
			day = Integer.parseInt(st.nextToken()); // 하루 
			month = Integer.parseInt(st.nextToken()); // 1달 
			month3= Integer.parseInt(st.nextToken()); // 3달 
			year = Integer.parseInt(st.nextToken()); // 1년
			
			st = new StringTokenizer(br.readLine());
			
			inp = new int[13];
			for (int i = 1; i <= 12 ; i++) {
				inp[i] = Integer.parseInt(st.nextToken()); // 12월 간 이용 계획 
			}
			
			// 비용의 최솟값 
			
			for (int i = 1; i <= 12; i++) {
				
				// 1일 이용권만 사용하는 경우 
				dp[i] = dp[i-1] + inp[i]*day;
				
				
				// + 1달 사용권 추가 
				dp[i] = Math.min(dp[i], dp[i-1] + month);
				
				// + 3달 사용권 추가 
				
				if( i >= 3 ) {
					dp[i] = Math.min(dp[i], dp[i-3] + month3);
				}
				
			}
			
			dp[12] = Math.min(dp[12], year);
			
			System.out.println("#" + t + " " + dp[12]);
			
		}

	}

}
