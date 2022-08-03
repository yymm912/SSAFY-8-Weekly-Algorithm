package baekjoon.주유소_13305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N; // 도시의 개수
	static long[] road;
	static long[] city;
	static long price;
	static long fee;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		road = new long[N-1];
		city = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N-1;i++){
			road[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			city[i] = Long.parseLong(st.nextToken());
		}
		
		fee = city[0];
		price = fee*road[0];
		for(int i=1;i<N-1;i++) {
			if(fee>city[i]) {
				fee = city[i];
			}
			price += fee * road[i];
			
		}
		
		System.out.println(price);
	}

}
