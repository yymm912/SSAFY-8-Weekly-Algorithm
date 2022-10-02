import java.io.*;
import java.util.*;

class Main{
	static int N;
	
	static int lower_bound(int[] arr, int s, int e, int val) {
		while(s < e) {
			int mid = (s+e)>>1;
			if(arr[mid] < val) s = mid+1;
			else if(arr[mid] >= val) e = mid;		
//			System.out.println(s + " " + e);
		}
		return e;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/**
		 * 마지막 숫자보다 크면 => 추가
		 * 마지막 숫자보다 작거나 같으면 => lower_bound(나보다 크거나 같은) 자리 찾음
		 * 		=> 그 숫자를 나로 바꿈.
		 * */
		N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N];
		int eIdx = 0;
		
		int[] arr = new int[N];
		int[] arrIdx = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		dp[eIdx++] = arr[0];
		for(int i=1; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			if(dp[eIdx-1] < num) {
				arrIdx[i] = eIdx; // 현재 숫자가 증가하는 부분수열에서 몇번째 원소인지 저장한다.
				dp[eIdx++] = num;
			} else {
				int findIdx = lower_bound(dp, 0, eIdx, num);
				dp[findIdx] = num;
				arrIdx[i] = findIdx; // 현재 숫자가 증가하는 부분수열에서 몇번째 원소인지 저장한다.
			}
		}
		System.out.println(eIdx); // 가장 긴 증가 부분수열 개수 
		
		/**
		 부분수열 원소 찾기
		 => 가장 큰 원소로부터, 역순으로 찾아간다.
		 => 앞에서 부분수열의 index를 저장했으니까 이걸 따라감.
		 * */
		int[] answer = new int[eIdx];
		int prevV = 987654321;
		for(int i=N-1; i>=0; i--) {
			if(prevV > arr[i] && arrIdx[i] == eIdx-1) {
				answer[eIdx-1] = arr[i];
				eIdx--;
				prevV = arr[i];
			}
			if(eIdx == -1) break;
		}
		
		for(int i : answer) {
			System.out.print(i + " ");
		}
	}
}