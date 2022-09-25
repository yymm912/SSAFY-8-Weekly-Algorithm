package algo.BJ.타일채우기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] arr = new int[31];
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		
		arr[0] = 1;
		arr[2] = 3;
		
		for(int i = 4; i <= 30; i+=2) {
			arr[i] = arr[i-2]*3;
			for(int j = i - 4; j >=0; j-=2) 
				arr[i] += arr[j] * 2;
		}
		
		System.out.println(arr[N]);
	}
}
