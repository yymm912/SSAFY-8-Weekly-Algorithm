package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Guild {

	static int N;
	static int[] member;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		member = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			member[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(member);
		int cnt = 0;
		int sum = 0;
		for(int i=N-1;i >= 0;i--) {
			if(cnt == 0 && (i+1)>=member[i]) {
				cnt = member[i];
				sum++;
			}
			cnt--;
		}
		System.out.println(sum);
	}

}
