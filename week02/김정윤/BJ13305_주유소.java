package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ13305_주유소 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] price = new int[N-1];
		int[] road = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N-1; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			road[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		int min = price[0];
		
		for (int i = 0; i < N-1; i++) {
			if (price[i] < min) min = price[i];
			
			sum += (min * road[i]);
		}
		System.out.println(sum);

	}

}
