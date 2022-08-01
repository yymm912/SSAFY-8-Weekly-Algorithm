package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m=0, temp=0,answer=0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(st.hasMoreTokens()) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		
		while(!pq.isEmpty()) {
			m = pq.peek();
			temp=0;
			while(temp!=m && !pq.isEmpty()) {
				pq.poll();
				temp++;
			}
			if(temp==m) answer++;
		}
		
		System.out.println(answer);
		
	}

}
