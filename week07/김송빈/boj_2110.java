package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2110 {
	static int n, c;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			arr[i] = x;
			sum += x;
		}
		Arrays.sort(arr);
		System.out.println(bst(1,arr[n-1]-arr[0]));
		
	}

	static int bst(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			int count = 1;
			int start = arr[0];
			for (int i = 0; i < n; i++) {
				if (mid <= arr[i] - start) {
					count++;
					start = arr[i];
				}
			}
			if (count >= c)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return left-1;
	}
}
