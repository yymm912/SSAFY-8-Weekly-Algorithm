package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11055_가장큰증가부분수열 {

	static int N, answer;
	static int[] arr, DP, sum;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		DP = new int[N];
		
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			DP[i] = arr[i];
			for(int j=0; j<i; j++) {
				
				if(arr[j]<arr[i]) {
					if(DP[i] < DP[j]+arr[i]) {
						DP[i] = DP[j]+arr[i];
					}
				}
				
			}
			answer = Math.max(DP[i], answer);
		}
		System.out.println(answer);
		
	}

	

}