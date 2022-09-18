package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1920_수찾기 {

	static int N, M;
	static int[] nums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(findNum(num)).append("\n");
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	
	static int findNum(int num) {
		int s = 0;
		int e = nums.length;
		
		while(s < e) {
			int m = (s + e) / 2;
			
			if(nums[m] == num) return 1;
			else if(nums[m] < num) s = m + 1;
			else if(nums[m] > num) e = m;
		}
		
		return 0;
	}

}
