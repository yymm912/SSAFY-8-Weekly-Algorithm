package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 풀이시간: 1시간+
 * 참고: 백준 문제 분류
 * 
 * <풀이>
 * - 이분탐색, 포인터 두개
 * - 먼저 한 용액을 잡고 (나는 맨 왼쪽부터 순차적으로 함)
 * - 가장 특성합이 적은 다른 용액을 이분탐색으로 찾는다.
 * 
 * - 이분탐색은
 * - 양쪽 중 어느쪽으로 갈 지 알아내야함
 * - mid-1, mid+1 의 용액 중 특성값 작은 쪽으로 이동해 탐색을 계속한다.ㄴ
 * 
 * <삽질목록>
 * - 처음에 아무생각없이 min 갱신될 때 left = mid+1 했음
 * */
public class BJ2467_용액 {
	static int N, minResult = Integer.MAX_VALUE;
	static int[] liquid;
	static int[] resultArr = new int[2];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		liquid = new int[N];
		for (int i = 0; i < N; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N-1; i++) {
			int idx = binarySearch(i);	// liquid[i]와 섞었을 때 최소가 되고, 이전 최소값 갱신하는 용액의 index 찾아 리턴
			
			if(idx==-1) continue;	// 특성값의 최소값(minResult)이 갱신되지 않았을 경우 (더 작은 특성값 못찾음)
			
			// 특성값의 최소 갱신한 경우 용액 두개 저장
			resultArr[0] = liquid[i];
			resultArr[1] = liquid[idx];
		}
		
		System.out.println(resultArr[0] + " " + resultArr[1]);
	}
	private static int binarySearch(int i) {
		int liq1 = liquid[i];
		
		int left = i+1;
		int right = N-1;
		int mid = (left+right)/2;
		int result = -1;	// 결과 인덱스
		
		// liquid[i] 를 이용한 
		int minVal = Integer.MAX_VALUE;

		while(left<=right) {
			mid = (left+right)/2;
			int curr = Math.abs(liq1+liquid[mid]);
			if(curr<=minVal) {
				minVal = curr;
				result = mid;
			}
			
			if(left==right) break;
			
			int tmp1 = Math.abs(liq1+liquid[mid-1]);	// mid 왼쪽 용액 특성값
			int tmp2 = Math.abs(liq1+liquid[mid+1]);	// mid 오른쪽 용액 특성값
			
			// 둘 중 특성합이 작은 쪽을 선택해 이분탐색을 게속 진행한다.
			if(tmp1<tmp2) right = mid-1;	// 왼쪽 선택
			else left = mid+1;	// 오른쪽 선택
		}
		
		// 특성값의 최소값이 갱신되었을 때만 섞을 용액의 위치를 알려줌
		if(minVal < minResult) {
			minResult = minVal;
			return result;
		} else return -1;
	}
}
