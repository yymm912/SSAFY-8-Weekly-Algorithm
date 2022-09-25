package dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11727 {

	static final int MOD=10007;
	static int N;
	static int[] cache;
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		cache=new int[1001];
		Arrays.fill(cache, -1);
		
		cache[0]=0;
		cache[1]=1;
		cache[2]=3;
		
		System.out.println(tiling(N));
	}
	
	private static int tiling (int n) {

		if (cache[n]!=-1) return cache[n];	
		return cache[n]=(tiling(n-2)*2%MOD + tiling(n-1)%MOD) %MOD;
	}
}
