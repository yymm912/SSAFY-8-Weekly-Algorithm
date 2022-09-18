package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2512_예산 {
	static int N, budget;
	static int[] demand;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		demand = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long low=0, mid=0, max=0;
		
		for(int i=0; i<N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			max = Math.max(max, tmp);
			demand[i] = tmp;
		}
		
		budget = Integer.parseInt(br.readLine());
		
		long sum = 0; 
		
		for(int i=0; i<N; i++) {
			sum+=demand[i];
		}
		
		if(sum<=budget) {
			System.out.println(max);
			return;
		}
		
		while(max>low) {
			mid = (low+max)/2;
			sum = 0; 
			for(int i=0; i<N; i++) {
				if(demand[i]>mid) sum+=mid;
				else sum+=demand[i];
			}
			if(sum<=budget) low = mid+1; 
			else max = mid;
		}
		
		
		System.out.println(low-1);
	}
}
