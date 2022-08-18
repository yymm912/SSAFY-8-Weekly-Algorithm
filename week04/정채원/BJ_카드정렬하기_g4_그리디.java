import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main{
	static int N;
	static PriorityQueue<Integer> card = new PriorityQueue<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			card.offer(Integer.parseInt(br.readLine()));
		}
		int accumSum = 0;
		while(card.size() > 1) {
			int cardSum = card.remove() + card.remove();
			accumSum += cardSum;
			card.offer(cardSum);
		}
		System.out.println(accumSum);
	}
}