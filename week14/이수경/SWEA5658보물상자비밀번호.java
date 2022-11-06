package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA5658보물상자비밀번호2 {

	static int T, N, K;
	static String num;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 숫자 개수 
			K = Integer.parseInt(st.nextToken()); // 찾는 숫자 위치 
			
			String arr[] = br.readLine().split(""); // 한 글자씩 저장 
			
			TreeSet<String> inp = new TreeSet<>(Collections.reverseOrder()); // 내림차순.
			
			
			for (int i = 0; i < N; i++) { // 12번 회전 

				//한 칸씩 밀기 ( 0 -> 1 , 1 -> 2 ,,, N-1 -> 0 )
				String end = arr[N-1];
				for (int j = N-1; j > 0; j--) {
					arr[j] = arr[j-1];
				}
				arr[0] = end;

				//stringBuilder에 N/4만큼 떼서 저장 
				for (int j = 0; j < 4; j++) {
					StringBuilder sb = new StringBuilder();
					for (int k = 0+(j*N/4); k < N/4+(j*N/4); k++) {
						sb.append(arr[k]);
					}
					inp.add(sb.toString());
				}
				
			}
			
			// inp의 K번째 출력 
			String result[] =  inp.toArray(new String[inp.size()]);
			
			System.out.println("#" + t + " " +  Integer.parseInt(result[K-1], 16)  );
			
			
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