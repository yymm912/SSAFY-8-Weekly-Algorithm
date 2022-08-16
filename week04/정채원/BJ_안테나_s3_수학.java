import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
11
1 3 4 6 6 7 8 8 9 10 150
 */
public class Main{
	// 중앙값? 평균과 가장 가까운 값?
	// 평균값 X -> 이상치에 영향을 많이 받기 때문
	static int N;
	static int[] houses;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		houses = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			houses[i] = Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(houses);
		
		// 최대 2e^10은 2^30 ~= 10^9 => int범위 넘음
		int mid = (N-1)/2;
		if(N % 2 == 0 || N == 1) { // 짝수이거나 N이 1개이면 -> 중앙값
			System.out.println(houses[mid]);
			return;
		}
		
		// N이 홀수개면, 합이 가장 작은 것
		long sum1 = 0;
		for(int i=0; i<N; i++) {
			sum1 += (long)Math.abs(houses[i] - houses[mid]);
		}
		long sum2 = 0;
		mid = (N+1)/2;
		for(int i=0; i<N; i++) {
			sum2 += (long)Math.abs(houses[i] - houses[mid]);
		}
		
		if(sum1 <= sum2) System.out.println(houses[mid-1]);
		else System.out.println(houses[mid]);
	}
}