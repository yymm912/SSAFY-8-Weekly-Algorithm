package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_모험가길드 {

	static int N, count;
	static int min = Integer.MAX_VALUE;


	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		count = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(arr[i], min);
		}
		
		for(int i = 0; i < N; i++) {
			if(min == arr[i]) count++;
			if(count == min) {
				min++;
				continue;
			}
		}
		System.out.println(min-1);
	}

}