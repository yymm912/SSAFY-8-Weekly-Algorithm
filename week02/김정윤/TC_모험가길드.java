package week2.김정윤;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TC_모험가길드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] adventurers = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			adventurers[i] = Integer.parseInt(st.nextToken());
		}
		
		// Sort -> 최대 그룹 수 산출을 위해
		Arrays.sort(adventurers);
		// [2, 3, 1, 2, 2] -> [1, 2, 2, 2, 3]
		
		// groups - 최대 그룹 수 | max - 그룹 내 최대 인원 수
		int groups = 0, max = 0;
		for (int j : adventurers) {
			max++;
			if (max >= j) {
				groups++;
				max = 0;
			}	
		}
		System.out.println(groups);
	}

}
