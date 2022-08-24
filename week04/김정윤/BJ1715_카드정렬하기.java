package week4.김정윤;

import java.io.*;
import java.util.*;

public class BJ1715_카드정렬하기 {
	static int N, sum = 0;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());		

		for (int n = 0; n < N; n++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		while (pq.size() > 1) {
			int first = pq.poll();
			int second = pq.poll();
			
			sum += first + second;
			pq.add(first + second);
		}
		System.out.println(sum);
	}
}
