package week4.김정윤;

import java.io.*;
import java.util.*;

public class BJ18310_안테나 {
	static int N;
	static int[] house;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		house = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			house[n] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(house);
		System.out.println(house[(N-1)/2]);

	}

}
