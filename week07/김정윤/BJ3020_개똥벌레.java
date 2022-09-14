package week7.김정윤;

import java.io.*;
import java.util.*;

public class BJ3020_개똥벌레 {
	static int N, H;
	static int[] top, bottom;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		// 0 dummy
		top = new int[H+1];
		bottom = new int[H+1];
		
		for (int i = 0; i < N/2; i++) {
			bottom[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}
		for (int i = H-1; i > 0; i--) {
			bottom[i] += bottom[i+1];
			top[i] += top[i+1];
		}
		
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		for (int i = 1; i <= H; i++) {
			int conflict = bottom[i] + top[H-i+1];
			
			if (conflict < min) {
				min = conflict;
				cnt = 1;
			}
			else if (min == conflict) cnt++;
		}
		System.out.println(min + " " + cnt);
	}
}
