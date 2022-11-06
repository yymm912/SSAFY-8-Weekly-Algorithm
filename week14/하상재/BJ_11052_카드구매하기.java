package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11052_카드구매하기 {
	
	static int N, answer;
	static int[] arr;
	static int[] dp1, dp2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		dp1 = new int[N+1];
		dp2 = new int[N+1];
		
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp2[i] = (N/i)*arr[i];
			answer = Math.max(answer, dp2[i]);
		}
		
	 	
		dp1[0] = 0;
		dp1[1] = arr[1];
		
		int n = N;
		for(int i=1; i<=N; i++, n--) {
			dp1[i] = arr[i];
			for(int j=i; j>0; j--) {
				dp1[i] = Math.max(dp1[i], dp1[j]+dp1[i-j]);
			}
			answer = Math.max(answer, dp1[i]);
			
			if(N%n!=0) answer = Math.max(answer, dp2[n]+dp1[N%n]);
			else answer = Math.max(answer, dp2[n]);
		}
		
		System.out.println(answer);
		
	}

}
