package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2467 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 
			arr[i] = Integer.parseInt(stk.nextToken());
		
		
		int gap = Integer.MAX_VALUE;
		int iI = -1, jJ = -1;
		
		int i = 0, j = N - 1;
		while(i < j) {
			int now = Math.abs(arr[i] + arr[j]);
			
			if(now <= gap) {
				gap = now;
				iI = i;
				jJ = j;
			}
			
			// 양쪽 끝 용액을 합쳐서 음수면 왼쪽에서 >> 으로 이동
			// 양수면 오른쪽에서 << 으로 이동
			if(arr[i] + arr[j] < 0) i++;
			else j--;
		}
	
		
		System.out.println(arr[iI] + " " + arr[jJ]);
	}

}
