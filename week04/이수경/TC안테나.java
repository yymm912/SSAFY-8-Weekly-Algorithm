package tc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TC안테나 {

	static int N;
	static int house[];
	static int Min;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		house = new int[N];
		for(int i=0;i<N;i++) {
			house[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		Arrays.sort(house); // 오름차순 정렬 
		
		if(N%2 == 0) { // 짝수일 때
			ans = house[N/2 - 1];
			
		} else { // 홀수 일 때 
			ans = house[N/2];
		}
		
	
		System.out.println(ans);
		
	}

}
