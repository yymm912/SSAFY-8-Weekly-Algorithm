package BJ.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1463_1로만들기 {
	static int[] f;
	public static void main(String[] args) throws Exception{
		int n;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		f = new int[n+3];
		
		f[2]=1;
		f[3]=1;
		
		for (int i = 4; i <= n; i++) {
			if(i%6==0) {
				f[i] = Math.min(f[i-1], Math.min(f[i/2],f[i/3]))+1;
			}
			else if(i%3==0) {
				f[i] = Math.min(f[i-1], f[i/3])+1;
			}
			else if(i%2==0) {
				 f[i] = Math.min(f[i-1], f[i/2])+1;
			}
			else {
				f[i] = f[i-1]+1;
			}
		}
		
		System.out.println(f[n]);
	}
	
}
