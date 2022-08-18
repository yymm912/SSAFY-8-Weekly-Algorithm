import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;




/* ex ) 10 20 40 => (10+20) + 40 + 30
 * 큐에서 삭제한 것이 2개가 되면 answer에 더해주고 큐에 새로 삽입
 * */

public class BJ_1715_카드정렬하기 {

	static int N;
	static PriorityQueue<Integer> pQ = new PriorityQueue<>();
	static int answer;
	static int cnt;
	static int sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			pQ.offer(Integer.parseInt(br.readLine()));
		}

		while (!pQ.isEmpty()) {
			int a = pQ.poll();
			sum += a;
			cnt++;

			if (cnt == 2) {
				answer += sum;
				pQ.offer(sum);
				sum = 0;
				cnt = 0;
			}

		}

		System.out.println(answer);

	}

}
