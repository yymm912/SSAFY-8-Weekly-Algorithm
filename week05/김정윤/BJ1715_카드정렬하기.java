package week5.김정윤;

import java.io.*;
import java.util.*;

public class BJ1715_카드정렬하기 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 작은 숫자부터 정렬 => 최소 비교
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		int sum = 0;
		while (pq.size() > 1) { // 최종 결과 1개가 나올때까지 반복
			int first = pq.poll();
			int second = pq.poll();
			
			sum += first + second;
			pq.add(sum); // 이전 합 리스트에 다시 추가
		}
		System.out.println(sum);
	}


}
