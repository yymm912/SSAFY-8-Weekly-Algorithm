package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952수영장 {

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
				inp[i] = Integer.parseInt(st.nextToken());
			}
			
			dp[1] = Math.min(day*inp[1], month); // 1일과 한달 비교
			
			for (int i = 1; i <= 12; i++) {
			 
				// 하루와 한 달 비교 
				dp[i] = Math.min( day*inp[i] + dp[i-1]  , month + dp[i-1]  );

				if(i > 2) {
					
					// 세 달 이상일 경우 min( 위에서 갱신된 dp값 또는, 세 달 ) 
					dp[i] = Math.min(  month3 + dp[i-3], dp[i] ); 

				}

			}

			//세달과 1년 비교
			dp[12] = Math.min( dp[12] ,  year );
						
			
			System.out.println("#" + t + " " + dp[12]);
			
			
			
		}

	}

}
