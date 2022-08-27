package forStudy.week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BJ1715_카드정렬하기 {

	static int N, res;
	static PriorityQueue<Integer> cards;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		cards = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			cards.offer(Integer.parseInt(br.readLine()));
		}
		
		// 우선순위 큐에서 두개의 값을 꺼내 더한 후 다시 큐에 삽입
		// 마지막에는 하나만 남게 됨
		while(cards.size() > 1) {
			int sum = cards.poll() + cards.poll();
			res += sum;
			cards.offer(sum);
		}
		
		System.out.println(res);
	}
}
