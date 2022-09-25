

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_13702_이상한술집 {
	
	static int N, K;
	static long mid, left, right;
	static int[] sul;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //주전자 수
		K = Integer.parseInt(st.nextToken()); //사람 수
		sul = new int[N];
		for (int i = 0; i < N; i++) {
			sul[i] = Integer.parseInt(br.readLine());
		}
		//이분탐색
		Arrays.sort(sul);
		left = 1;
		right = sul[N-1];
		while (left<=right) {
			mid = (right + left)/2;
			int cnt = 0;
			for (int num : sul) {
				cnt += num/mid; //mid를 줄여야 cnt가 커진다
			}
			if (cnt >= K) left = mid + 1;
			else right = mid-1;
		}
		System.out.println(right);
	}
	
}
