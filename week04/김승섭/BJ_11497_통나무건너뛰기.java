package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ_11497_통나무건너뛰기 {
	static int T, N;
	static int[] stump;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); 
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 그루터기의 숫자
			stump = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 정렬시켜 차례로 양쪽에 배치하기 위해 나무 기둥 배열을 선언
			for (int i = 0; i < N; i++) {
				stump[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(stump);
			
			int[] ans = new int[N];
			int start = 0;
			int end = N-1;
			// 양쪽 끝을 차례로 차곡차곡 담아 배열 가운데에 가장 높이가 큰 값이 담기도록 함
			for (int i = 0; i < N; i++) {
				 if(i%2 == 0) ans[start++] = stump[i];
				 else ans[end--] = stump[i];
			}
			int max = Integer.MIN_VALUE;
			// 왼쪽에서부터 다음 차례와 비교해가며 max 차이를 찾은 후 출력
			for (int i = 0; i < N-1; i++) {
				max = Math.max(max, Math.abs(ans[i] - ans[i+1]));
			}
			System.out.println(max);
		}
		
		/* 본래 만드려고 했던 방식은, ArrayList에 담아 큰 수를 왼쪽, 작은 수를 오른쪽으로 분류하여
		 * 가운데가 오목한 분포로 만들었지만,
		 * 추가 테스트 케이스를 성공하지 못해 ArrayList를 포기하고 일반 배열에 순서대로 담는 방법을 채택하였습니다.
		 * for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			stump = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				stump[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(stump);
			int index = 0;
			ArrayList<Integer> array = new ArrayList<>();
			if( N % 2 == 0) {
				for (int i = N-1; i > 0; i--) {
					if(stump[i] >= stump[i-1]) {
						array.add(index++, stump[i]);
						array.add(index, stump[i-1]);	
					}
				}
			}else {
				for (int i = N-1; i > 0; i -= 2) {
					if(stump[i] >= stump[i-1]) {
						array.add(index++, stump[i]);
						array.add(index, stump[i-1]);	
					}
				}
				array.add(array.size()/2, stump[0]);
			}
			int max = Integer.MIN_VALUE;

			max = Math.abs(array.get(0) -  array.get(array.size()-1));
				
			for (int i = 0; i < array.size()-1; i++) {
				max = Math.max(max, Math.abs((array.get(i)-array.get(i+1))));
			}
		 */
		
		
	}
}
