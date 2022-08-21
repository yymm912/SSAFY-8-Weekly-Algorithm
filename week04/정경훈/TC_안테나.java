package baekjoon.안테나_18310;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] house;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		house = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(house);
		
		// 각 집까지의 거리...중 최소니까.. 그중 가장 작은 idx
		System.out.println(house[(N-1)/2]);
	}

}
