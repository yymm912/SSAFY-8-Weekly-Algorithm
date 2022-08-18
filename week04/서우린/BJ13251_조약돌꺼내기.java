package _4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13251_조약돌꺼내기 {
	static int M,K;
	static int [] stone;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		M = Integer.parseInt(br.readLine());
		
		stone = new int[M];
		int N = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0;i<M;i++) {
			stone[i] = Integer.parseInt(st.nextToken());
			N += stone[i];
		}
		
		K = Integer.parseInt(br.readLine());
		
		double answer = 0;
		
		for(int i = 0;i<M;i++) {
			double a = 1;
			for(int k = 0;k<K;k++) {
				a *= ((double)stone[i]-k)/(N-k);
			}
			answer += a;
		}
		System.out.println(answer);
	}
}
