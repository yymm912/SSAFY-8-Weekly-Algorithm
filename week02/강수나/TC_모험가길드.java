package algo_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//인원 수로 접근.. 이해못함
public class TC_모험가길드2 {

	static int N, X, max, grpCnt;
	static int[] arr;
	static boolean[] selected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		selected = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); //여기까진 하겠는데
		
		int grpCnt = 0;
		int memCnt = 0;
		for (int fear : arr) {
			memCnt++;
			if (fear <= memCnt) { //자기 공포도 <= 멤버 수 일 때 그룹 성립
				grpCnt++;
				memCnt = 0; //
			}
		}
		
		System.out.println(grpCnt);
	}
	
}
