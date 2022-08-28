import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14501_퇴사 { // DP 이용
	static int N, max;
	static int[][] cons;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cons = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cons[i][0] = Integer.parseInt(st.nextToken()); // 상담시간
			cons[i][1] = Integer.parseInt(st.nextToken()); // 상담비용
		}
		max = Integer.MIN_VALUE;
		int[] dp = new int[N+1]; // 퇴사일의 비용을 체크하기 위한 크기 선언
		
		for (int i = 0; i < N; i++) { // dp로써 각 날짜의 상담 비용을 다음 배열 번호에 저장해가며 진행한다. 
			if(i + cons[i][0] <= N) { // 해당 날짜에 상담시간을 더한 다음 날짜가 퇴사일을 초과한다면 다음 날 누적된 비용을 최댓값으로 채택
				dp[i + cons[i][0]] = Math.max(dp[i + cons[i][0]], dp[i] + cons[i][1]);
			}
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		
		System.out.println(dp[N]);// 퇴사일에 받을 수 있는 최대 상담 비용
		
		
	}

}
