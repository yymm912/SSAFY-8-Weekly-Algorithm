package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BJ_1715 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static int Ateam, Bteam;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		
		for(int i = 0; i < N; i++) 
			pq.add(Integer.parseInt(br.readLine()));
		
		int ans = 0;
		
		while(!pq.isEmpty()) {
			int A = pq.poll();
			
			if(pq.isEmpty()) 
				break;
			
			
			int B = pq.poll();
			ans += A + B;
			pq.add(A + B);
		}
		
		System.out.println(ans);
		
	}
}
