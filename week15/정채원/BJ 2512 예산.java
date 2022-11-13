import java.io.*;
import java.util.*;

class Main{
	static int N, M;
	static int[] req;
	static int reqSum;
	/*
	 * 1. 전체 합 <= M -> 그대로
	 * 2. 상한액을 정해야 함 -> 이분탐색
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		req = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			req[i] = Integer.parseInt(st.nextToken());
			reqSum += req[i];
		}
		M = Integer.parseInt(br.readLine());
		
		Arrays.sort(req);
		if(reqSum <= M) {
			System.out.println(req[N-1]);
		} else {
			int s=0, e=req[N-1]+1;
			while(s+1 < e) {
				int mid = (s+e) >> 1;
				if(cond(mid)){ // cond : 예산이 전체 커버 가능? -> 상한액 늘림
					s = mid;
				} else {
					e = mid;
				}
			}
			System.out.println(s);
		}
	}
	static boolean cond(int max) {
		int sum = 0;
		int i=1;
		for(; i<=N; i++) {
			if(req[i-1] <= max) sum += req[i-1];
			else break;
		}
		sum += (max * (N-i+1));
//		System.out.println(max + " " + sum);
		return sum <= M;
	}
}