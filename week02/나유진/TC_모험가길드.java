package TC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class tc_explorer_guild {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		ArrayList<Integer> arr = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(arr);

		int ans, idx, fear, max = Integer.MIN_VALUE;

		for (int i = N - 1; i >= 0; i--) { // array 에 있는 사람들 수 N명 부터 1명까지임.
			ans = 0;
			idx = i; // index 
			fear = arr.get(idx) - 1; //해당 인덱스의 공포도
			while (idx >= 0) { //인덱스가 처음으로 갈때까지(i명이 갈때)
				if (fear == 0) { // fear가 0 이면
					ans++; // 그룹 한개 생성
					if(idx==0)break; 
					fear = arr.get(--idx) - 1; 
					continue;
				} else {
					fear--; // 한사람당 fear -- ;
				}
				idx--;
			}
			if (ans > max) // 현재 max와 계산 된 ans 비교
				max = ans;
		}

		System.out.println(max);

	}
}
