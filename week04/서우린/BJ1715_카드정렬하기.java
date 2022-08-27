package _4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class BJ1715_카드정렬하기 {
	static Queue<Integer> q;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		q = new PriorityQueue<>();
		for(int i = 0;i<N;i++) 
			q.add(Integer.parseInt(br.readLine()));
		
		long answer = 0;
		while(q.size() != 1) {
			int a = q.poll();
			int b = q.poll();
			answer += a+b;
			q.add(a+b);
		}
		System.out.println(answer);
		
	}

}
