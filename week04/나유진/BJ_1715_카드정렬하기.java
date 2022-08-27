package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class bj_1715_카드정렬하기 {
	static int N, ans;
	static PriorityQueue<Integer> card = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			card.add(Integer.parseInt(br.readLine()));
		}
		while (card.size() != 1) {
			int temp = 0;

			temp += card.poll();
			temp += card.poll();

			card.add(temp);
			ans += temp;
		}
		
		System.out.println(ans);
	}
}
