package week5.김정윤;

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
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			house[i] = h;
		}
		
		// 정렬
		Arrays.sort(house);
		
		// 가운데에 위치한 집 출
		System.out.println(house[(N-1)/2]);
		
	}
}
