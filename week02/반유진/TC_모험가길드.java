// 이코테 - 모험가 길드 

package 이코테;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Adventurer_Guild {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬해줘야 함! (놓쳤던 부분.. 코드 리뷰를 통해 알게 됨)
		Arrays.sort(arr);	
		
		int person = 0;	
		int cnt = 0;
		// 2 3 1 2 2 
		// 1 2 2 2 3
		for (int i = 0; i < N; i++) {	
			person++;	
			if (person >= arr[i]) {
				// 한 그룹의 모험가의 수가 현재 모험가의 공포도를 넘어선다면
				// 팀 수 ++ / 세던 모험가 수 초기화
				cnt++;
				person = 0;
			}
		}
		System.out.println(cnt);
	}
}
