package _8주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2100_공유기설치 {
	static int N,C;
	static int [] x;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		x = new int [N];
		for(int i = 0;i<N;i++)
			x[i] = Integer.parseInt(br.readLine());
		Arrays.sort(x);
		int lo = 1;
		int hi = 1000000000;
		int answer = 0;
		while(lo<=hi) {
			int mid = (lo+hi)/2;
			if(parametric(mid)) {
				answer = mid;
				lo = mid + 1;
			}else {
				hi = mid - 1;
			}
		}
		System.out.println(answer);
	}
	
	static boolean parametric(int d) {
		
		int a = 1;
		int cur = 0;
		for(int i = 1;i<N;i++) {
			if(x[i] - x[cur] >= d) {
				a++;
				cur = i;
			}
			if(a >= C) return true;
		}
		return false;
	}

}
