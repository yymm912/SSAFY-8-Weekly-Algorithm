package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9095_123더하기 {

	static int T,N;
	static int np[];
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		np = new int[12];
		np[1] = 1;
		np[2] = 2;
		np[3] = 4;
		
		for(int i=4;i<12;i++) {
			np[i] = np[i-1] + np[i-2] + np[i-3];
		}
		
		
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			System.out.println(np[N]);
			
		}
		
	}

}
