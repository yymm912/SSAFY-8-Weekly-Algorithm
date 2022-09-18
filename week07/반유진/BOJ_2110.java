// BOJ 2110번 공유기 설치

package BOJ.Binary_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_2110 {

	static int N, C, result;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int start = 1;	// 최소가 될 수 있는 거리 
		int end = arr[N - 1] - arr[0];	// 최대가 될 수 있는 거리 

		while (start <= end) {
			int mid = (start + end) / 2;	// 지정 거리 
			int cnt = 1;	// 공유기 개수 
			int house = arr[0];	// 두 집간의 거리를 구하기 위한 앞집   

			for (int i = 1; i < N; i++) {
				// 두 집의 거리가 현재 지정해둔 mid보다 크거나 같으면  
				if (arr[i] - house >= mid) {	 
					cnt++;
					house = arr[i];
				}
			}

			if (cnt >= C) 
				start = mid + 1;
			else
				end = mid - 1;
		}

		System.out.println(start - 1);
	}

}
