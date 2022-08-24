package algo_study._8월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class TC_카드정렬하기 {

	static PriorityQueue<Integer> card = new PriorityQueue<>(); //우선순위큐: 디폴트-오름차순으로 정렬
	static int N, ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			card.add(Integer.parseInt(br.readLine()));
		}
		getSum(1);
	}
	
	static void getSum(int cnt) {
		int sum = 0;
		if (cnt == N) {
			System.out.println(ans);
			return;
		}
		if (card.size() == 1) 
			sum = card.poll();
		else 
			sum = card.poll() + card.poll();
		ans += sum;
		card.add(sum);
		getSum(cnt+1);
	}
}
