package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


/*
 * [주의]
 * - 최소한 몇 번의 비교가 필요한지 
 * */
public class BJ1715_카드정렬하기 {
	static int N, ans;
	static int[] arr;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) pq.add(Integer.parseInt(br.readLine()));

		int cnt = 0;
		int sum = 0;
		while (true) {
			if (pq.isEmpty()) break;

			sum += pq.poll();
			cnt++;

			if (cnt == 2) {
				ans += sum;
				pq.add(sum);
				cnt = 0;
				sum = 0;
			}

		}

		System.out.println(ans);

	} // end main
} // end class
