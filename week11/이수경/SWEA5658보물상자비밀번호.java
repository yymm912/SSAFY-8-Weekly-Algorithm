package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA5658보물상자비밀번호 {

	static int T, N, K;
	static String num;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 숫자 개수 
			K = Integer.parseInt(st.nextToken()); // 찾는 숫자 위치 
			
			String arr[] = br.readLine().split("");
			
			// 숫자들을 회전 시켜야 함 ! 
			
			TreeSet<String> set = new TreeSet<>(Collections.reverseOrder());
			
			for (int k = 0; k < N/4; k++) { // 회전은 N번까지 하면 됨 
				// 숫자 회전
				String temp = arr[N-1];
				for (int i = N-1; i > 0; i--) {
					arr[i] = arr[i-1];
				}
				arr[0] = temp;
				
				// 숫자 뽑아내기 
				for (int i = 0; i < N; i+=(N/4)) {
					StringBuilder sb = new StringBuilder();
					for (int j = i; j < i+(N/4); j++) {
						sb.append(arr[j]);
					}
					set.add(sb.toString());
				}
				
				
			}
			
			// set의 K 번째 수를 10진수로 변경 
			
			String result[] = set.toArray(new String[set.size()]);
			System.out.println("#" + t + " " + Integer.parseInt(result[K-1], 16));
			
			
		} // testcase
		
	}

}
/*
5
12 10
1B3B3B81F75E
16 2
F53586D76286B2D8
20 14
88F611AE414A751A767B
24 16
044D3EBA6A647B2567A91D0E
28 11
8E0B7DD258D4122317E3ADBFEA99
*/